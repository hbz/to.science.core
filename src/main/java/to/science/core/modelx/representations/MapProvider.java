/**
 * 
 */
package to.science.core.modelx.representations;

import java.util.LinkedHashMap;

import org.json.JSONObject;

/**
 * @author aquast
 *
 */
public interface MapProvider {

  /**
   * <p>
   * Generates a <em>sorted</em> LinkedHashMap from any JSONObject fragment provided to this method. 
   * Be aware that this is true for the first level of child nodes only. Therefore you need to use 
   * this method for each fragment separately if a whole JSONObject should be represented in 
   * a LinkedHashMap  
   * 
   * </p>
   * 
   * @param anyJSONObject
   * @return a Map with predictable iteration order that equals the order in which the objects exists in a JSON
   */
  public LinkedHashMap<String, Object> getLinkedHashMap(JSONObject anyJSONObject);
}
