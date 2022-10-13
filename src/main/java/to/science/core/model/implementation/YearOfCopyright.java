package to.science.core.model.implementation;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.log4j.Logger;

import to.science.core.model.model.AbstractSimpleArray;
import to.science.core.model.model.SimpleArray;
import to.science.core.model.model.ToScienceModel;

/**
 * <p>
 * An implementation for the yearOfCopyright Array of toscience. 
 * Provides basic methods to create new instances and modify them.
 * </p>
 * <p>
 * As JSON model:
 * <pre>
 * "yearOfCopyright" : [ "item 1", "item 2", "item 3", ...]
 * </pre>
 * </p>
 * 
 * @author aquast
 *
 */
public class YearOfCopyright extends AbstractSimpleArray implements SimpleArray, ToScienceModel {

  final static Logger logger = Logger.getLogger(YearOfCopyright.class);
  public ArrayList<String> list = new ArrayList<String>();
  
  @Override
  public JSONObject getAmbJSONObject() {
    JSONObject yearOfCopyright = new JSONObject();
    // amb supports one description only. So we decide to take the first always
    yearOfCopyright.put("yearOfCopyright", list.get(0).substring(0, 4));
    return yearOfCopyright;
  }
  
  @Override
  public AbstractSimpleArray getSimpleArrayFromAmbJSONObject(JSONObject ambJSONObject) {
    YearOfCopyright yearOfCopyright = new YearOfCopyright();
    yearOfCopyright.addItem(ambJSONObject.get("datePublished").toString());
    return yearOfCopyright;
  }

  @Override
  public String getJson() {
    JSONArray jsonArr = this.getJSONArray();
    String json = jsonArr.toString(1);
    return json;
  }

  @Override
  public JSONArray getJSONArray() {
    JSONArray yearArr = new JSONArray();
    for (int i=0; i < list.size(); i++) {
      yearArr.put(list.get(i));
    }
    return yearArr;
  }

  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    JSONObject yearJSONObject = new JSONObject();
    JSONArray yearJSONArray = new JSONArray();
    if (ambJSONObject.has("datePublished")) {
      String value = ambJSONObject.getString("datePublished");
      yearJSONArray.put(value.substring(0, 4));
      yearJSONObject.put("yearOfCopyright",  yearJSONArray);
    }
    return yearJSONObject;
  }

  @Override
  public JSONObject getJSONObject() {
    JSONObject yearJSONObject = new JSONObject();
    yearJSONObject.put("yearOfCopyright", getJSONArray());
    return yearJSONObject;
  }

  @Override
  public ToScienceModel setById(String id) {
    logger.warn("Method not applicable for TOS Model YearOfCopyright");
    return null;
  }
 
  
  
}
