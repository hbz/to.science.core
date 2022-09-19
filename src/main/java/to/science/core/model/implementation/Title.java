package to.science.core.model.implementation;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import to.science.core.model.model.AbstractSimpleArray;
import to.science.core.model.model.SimpleArray;

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
public class Title extends AbstractSimpleArray implements SimpleArray {

  final static Logger logger = LoggerFactory.getLogger(Title.class);
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
    JSONArray descriptArr = new JSONArray();
    for (int i=0; i < list.size(); i++) {
      descriptArr.put(list.get(i));
    }
    return descriptArr;
  }
 
  
  
}
