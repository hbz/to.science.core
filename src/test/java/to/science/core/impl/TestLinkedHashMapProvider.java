/**
 * 
 */
package to.science.core.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import to.science.core.modelx.representer.MapProviderImpl;

/**
 * @author aquast
 *
 */
public class TestLinkedHashMapProvider {

  final static Logger logger = LogManager.getLogger(TestLinkedHashMapProvider.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    MapProviderImpl mapProvider = new MapProviderImpl();
    TestLinkedHashMapProvider tLHMP = new TestLinkedHashMapProvider();
    LinkedHashMap<String,Object> tosMap = mapProvider.getLinkedHashMap(tLHMP.loadExampleSource());
    JSONArray creator = (JSONArray) tosMap.get("creator");
    for(int i=0; i < creator.length(); i++) {
      JSONObject jet = (JSONObject) creator.get(i);
      System.out.println(jet.get("prefLabel"));
    }
    
    
    
  }

  public JSONObject loadExampleSource() {

    JSONObject anyJSONObj = new JSONObject();

    try {
      InputStream anyJsonStream = this.getClass().getClassLoader().getResourceAsStream("exampleTOSModel.json");
      InputStreamReader anyStreamReader = new InputStreamReader(anyJsonStream, "UTF-8");
      BufferedReader bReader = new BufferedReader(anyStreamReader);
      StringBuilder jsonStringBuilder = new StringBuilder();

      String inputStr;
      while ((inputStr = bReader.readLine()) != null) {
        jsonStringBuilder.append(inputStr);
      }
      anyJSONObj = new JSONObject(jsonStringBuilder.toString());
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    return anyJSONObj;
  }

}
