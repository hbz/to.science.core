package to.science.core.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import to.science.core.modelx.mapper.AmbMapperImpl;

public class ConsoleMapper {

  final static Logger logger = LogManager.getLogger(ConsoleMapper.class);
  
  private Hashtable<String,String> scannedMD = new Hashtable<>();
  private Scanner scanner = null;
  private InputStream ambStream = null;

  public ConsoleMapper() {
    scannedMD.put("submitter", "Jane Doe");
    scannedMD.put("submitterEmail", "doe.example.org");
    scannedMD.put("loadFile", "yes");
    
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    

    System.out.println("***---------------------------ConsoleMapper-----------------------------***");
    System.out.println("***     ConsoleMapper is part of the to.science.core library by hbz     ***");
    System.out.println("***   Maps your AMB file to the to.science json based data model (TOS)  ***");
    System.out.println("***---------------------------------------------------------------------***");
    System.out.println("");
    // get new instance of this class
    logger.info("Call for new ConsoleMapper using AmbMapperImpl");
    ConsoleMapper amb = new ConsoleMapper();

    //amb.loadFileFromScanner();
    
    amb.setMDFromScanner("submitter", "Please set submitters name (Jane Doe)>");
    amb.setMDFromScanner("submitterEmail", "Please set submitters mail address (doe@eexample.org)>");
    amb.setMDFromScanner("loadFile", "Load our own AMB file for mapping? ((y)es)>", "[^yYjJ]+");

    if(amb.getLoadFile()) {
      amb.setFileFromScanner();
      amb.loadExampleAmbSource();
    } else {
      amb.loadExampleAmbSource();
    }

    LinkedHashMap<String,String> isDescribedByMap = new LinkedHashMap<>();
    isDescribedByMap.put("submittedBy", amb.getSubmitter());
    isDescribedByMap.put("submitterByEmail", amb.getSubmitterEmail());
    
    System.out.println("");

    AmbMapperImpl ambMapper = new AmbMapperImpl();
    ambMapper.setIsDescribedByMap(isDescribedByMap);
    
    JSONObject tosJSONObj = ambMapper.getTosJSONObject(amb.mapAmbSource());
    System.out.println(tosJSONObj.toString(1));
    
    logger.info("Application finished");

  }

  public JSONObject mapAmbSource() {

    JSONObject ambJSONObj = new JSONObject();

    try {
      InputStreamReader ambStreamReader = new InputStreamReader(this.ambStream, "UTF-8");
      BufferedReader bReader = new BufferedReader(ambStreamReader);
      StringBuilder jsonStringBuilder = new StringBuilder();

      String inputStr;
      while ((inputStr = bReader.readLine()) != null) {
        jsonStringBuilder.append(inputStr);
      }
      ambJSONObj = new JSONObject(jsonStringBuilder.toString());

    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }

    return ambJSONObj;
  }
  
  public void loadExampleAmbSource() {

    try {
      ambStream = this.getClass().getClassLoader().getResourceAsStream("exampleAmb.json");

    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }
  }

  public void loadAmbSource(File ambFile) {

    try {
      this.ambStream = new FileInputStream(ambFile);

    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e.getMessage());
    }
  }

  /**
   * Prepare loading File from Console input via Scanner
   */
  private void setFileFromScanner() {

    System.out.println("Please provide your AMB File relative to our current directory or with an absolute path");
    System.out.println("your current directory is "+ System.getProperty("user.dir"));
    System.out.println("");
    System.out.println("");
    
    this.scanner = new Scanner(System.in);
    System.out.print("AMB File location >");
    
    File ambFile =  new File(this.scanner.next());
    
    while(!ambFile.isFile()) {
      System.out.println("Sorry, no applicable file found at: " + ambFile.getAbsolutePath());

      this.scanner = new Scanner(System.in);
      System.out.print("AMB File location >");
      ambFile =  new File(this.scanner.next());
    };
    
  }

  /**
   * Load Metadata from console input via Scanner 
   */
  private void setMDFromScanner(String key, String message) {
    String scanResult = null;
    this.scanner = new Scanner(System.in);
    System.out.print(message);
    Pattern pattern = Pattern.compile("(.)+");
    scanResult = this.scanner.findInLine(pattern);
    if(scanResult != null) {
      this.scannedMD.put(key, scanResult);
    }
  }

    /**
     * Load Metadata from console input via Scanner 
     */
    private void setMDFromScanner(String key, String message, String patternString) {
      String scanResult = null;
      this.scanner = new Scanner(System.in);
      System.out.print(message);
      Pattern pattern = Pattern.compile(patternString);
      scanResult = this.scanner.findInLine(pattern);
      if(scanResult != null) {
        this.scannedMD.put(key, scanResult);
      }

  }
  
  public String getSubmitter() {
    return scannedMD.get("submitter");
  }

  public String getSubmitterEmail() {
    return scannedMD.get("submitterEmail");
  }

  public boolean getLoadFile() {
    boolean checkLoadFile = false;
    if(scannedMD.get("loadFile").equals("yes")){
      checkLoadFile = true;
    }
    return checkLoadFile;
  }

}
