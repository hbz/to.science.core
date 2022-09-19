package to.science.core.model.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * An abstract model for different implementations of toscience ArrayList of String. 
 * Provides basic methods to create new instances and modify them.
 * </p>
 * <p>
 * As JSON model
 * <pre>
 * "arrayName" : [ "item 1", "item 2", "item 3", ...]
 * </pre>
 * </p>
 * 
 * @author aquast
 *
 */
public abstract class AbstractSimpleArray implements SimpleArray {

  final static Logger logger = LoggerFactory.getLogger(AbstractSimpleArray.class);
  public ArrayList<String> list = new ArrayList<String>();
  
  @Override
  public void addItem(String item) {
    list.add(item);

  }

  @Override
  public String getItem(int i) {
    return list.get(i);
  }
  
  @Override
  public int size() {
    return list.size();
  }
  
  public abstract String getJson();
  
  
  public abstract JSONArray getJSONArray();
 
  /**
   * <p>
   * Map toscience model into amb model
   * </p>
   * 
   * @return mapped object as JSONObject 
   */
  public abstract JSONObject getAmbJSONObject();
  
  /**
   * @param ambJSONObject
   * @return
   */
  public abstract AbstractSimpleArray getSimpleArrayFromAmbJSONObject(JSONObject ambJSONObject);

}
