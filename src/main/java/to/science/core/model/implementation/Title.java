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
 * An implementation for the title Array of toscience. 
 * Provides basic methods to create new instances and modify them.
 * </p>
 * <p>
 * As JSON model:
 * <pre>
 * "title" : [ "item 1", "item 2", "item 3", ...]
 * </pre>
 * </p>
 * 
 * @author aquast
 *
 */
public class Title extends AbstractSimpleArray implements SimpleArray, ToScienceModel {

  final static Logger logger = LogManager.getLogger(Title.class);
  public ArrayList<String> list = new ArrayList<String>();
  
  @Override
  public JSONObject getAmbJSONObject() {
    JSONObject description = new JSONObject();
    // amb supports one title only. So we decide to take the first always
    description.put("name", list.get(0));
    return description;
  }
  
  @Override
  public AbstractSimpleArray getSimpleArrayFromAmbJSONObject(JSONObject ambJSONObject) {
    Title title = new Title();
    title.addItem(ambJSONObject.get("name").toString());
    return title;
  }

  @Override
  public String getJson() {
    JSONArray jsonArr = this.getJSONArray();
    String json = jsonArr.toString(1);
    return json;
  }

  @Override
  public JSONArray getJSONArray() {
    JSONArray titleArr = new JSONArray();
    for (int i=0; i < list.size(); i++) {
      titleArr.put(list.get(i));
    }
    return titleArr;
  }

  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    JSONObject titleJSONObject = new JSONObject();
    JSONArray titleJSONArray = new JSONArray();
    if (ambJSONObject.has("name")) {
      String value = ambJSONObject.getString("name");
      titleJSONArray.put(value);
      titleJSONObject.put("title",  titleJSONArray);
    }
    return titleJSONObject;
  }

  @Override
  public JSONObject getJSONObject() {
    JSONObject titleJSONObject = new JSONObject();
    titleJSONObject.put("title", getJSONArray());
    return titleJSONObject;
  }

  @Override
  public ToScienceModel setById(String id) {
    logger.warn("Method not applicable for TOS Model Description");
    return null;
  }
 
  
  
}
