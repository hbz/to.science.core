package to.science.core.model.implementation;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;

import to.science.core.model.model.AbstractSimpleArray;
import to.science.core.model.model.SimpleArray;
import to.science.core.model.model.ToScienceModel;

/**
 * <p>
 * An implementation for the description Array of toscience. 
 * Provides basic methods to create new instances and modify them.
 * </p>
 * <p>
 * As JSON model:
 * <pre>
 * "description" : [ "item 1", "item 2", "item 3", ...]
 * </pre>
 * </p>
 * 
 * @author aquast
 *
 */
public class Description extends AbstractSimpleArray implements SimpleArray, ToScienceModel {

  final static Logger logger = LogManager.getLogger(Description.class);
  public ArrayList<String> list = new ArrayList<String>();
  
  @Override
  public JSONObject getAmbJSONObject() {
    JSONObject description = new JSONObject();
    // amb supports one description only. So we decide to take the first always
    description.put("description", list.get(0));
    return description;
  }
  
  @Override
  public AbstractSimpleArray getSimpleArrayFromAmbJSONObject(JSONObject ambJSONObject) {
    Description description = new Description();
    description.addItem(ambJSONObject.get("description").toString());
    return description;
  }

  @Override
  public String getJson() {
    JSONArray jsonArr = this.getJSONArray();
    String json = jsonArr.toString(1);
    return json;
  }

  @Override
  public JSONArray getJSONArray() {
    JSONArray descriptArr = new JSONArray();
    for (int i=0; i < list.size(); i++) {
      descriptArr.put(list.get(i));
    }
    return descriptArr;
  }

  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    JSONObject descJSONObject = new JSONObject();
    JSONArray descJSONArray = new JSONArray();
    if (ambJSONObject.has("description")) {
      String value = ambJSONObject.getString("description");
      descJSONArray.put(value);
      descJSONObject.put("description",  descJSONArray);
    }
    return descJSONObject;
  }

  @Override
  public JSONObject getJSONObject() {
    JSONObject descJSONObject = new JSONObject();
    descJSONObject.put("description", getJSONArray());
    return descJSONObject;
  }

  @Override
  public ToScienceModel setById(String id) {
    logger.warn("Method not applicable for TOS Model Description");
    return null;
  }
 
  
  
}
