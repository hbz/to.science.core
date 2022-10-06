package to.science.core.model.implementation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import to.science.core.modelx.amb.AbstractAmbMapper;
import to.science.core.util.GenericPropertiesLoader;

public class AmbMapperImpl extends AbstractAmbMapper {
  
  final static Logger logger = LoggerFactory.getLogger(AmbMapperImpl.class);

  public JSONObject getAmbJSONObject() {
    
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
      logger.warn(e.getMessage());
    }

    
    return ambJSONObj;
  }

}
