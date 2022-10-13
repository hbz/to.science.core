/**
 * 
 */
package to.science.core.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

import org.json.JSONObject;

import to.science.core.modelx.mapper.AmbMapperImpl;

/**
 * @author aquast
 *
 */
public class TestAmbMapper {

  final static Logger logger = Logger.getLogger(TestAmbMapper.class);

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
