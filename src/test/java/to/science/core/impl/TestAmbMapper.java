/**
 * 
 */
package to.science.core.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import to.science.core.model.implementation.Affiliation;
import to.science.core.model.implementation.AmbMapperImpl;
import to.science.core.model.implementation.Contributor;
import to.science.core.model.implementation.Creator;
import to.science.core.model.implementation.Department;
import to.science.core.model.implementation.Description;
import to.science.core.model.implementation.Funder;
import to.science.core.model.implementation.Language;
import to.science.core.model.implementation.License;
import to.science.core.model.implementation.Medium;
import to.science.core.model.implementation.Subject;
import to.science.core.model.implementation.Title;
import to.science.core.model.implementation.YearOfCopyright;
import to.science.core.model.model.AbstractToScienceModel;
import to.science.core.model.model.ToScienceModel;
import to.science.core.modelx.amb.AbstractAmbMapper;

/**
 * @author aquast
 *
 */
public class TestAmbMapper {

  final static Logger logger = LoggerFactory.getLogger(TestAmbMapper.class);

  /**
   * @param args
   */
  public static void main(String[] args) {

    // get new instance of this class
    TestAmbMapper amb = new TestAmbMapper();

    AmbMapperImpl ambMapper = new AmbMapperImpl();
    JSONObject tosJSONObj = ambMapper.getTosJSONObject(amb.loadExampleAmbSource());
    System.out.println(tosJSONObj.toString(1));

  }

  public JSONObject loadExampleAmbSource() {
    
    JSONObject ambJSONObj = new JSONObject();

    try {
    InputStream ambJsonStream = this.getClass().getClassLoader().getResourceAsStream("exampleAmb.json");
    InputStreamReader ambStreamReader = new InputStreamReader(ambJsonStream, "UTF-8");
    BufferedReader bReader = new BufferedReader(ambStreamReader);
    StringBuilder jsonStringBuilder = new StringBuilder();
    
    String inputStr;
    while ((inputStr = bReader.readLine()) != null)
      jsonStringBuilder.append(inputStr);
      ambJSONObj = new JSONObject(jsonStringBuilder.toString());
    }catch (Exception e) {
      logger.error(e.getMessage());
    }

    
    return ambJSONObj;
  }

}
