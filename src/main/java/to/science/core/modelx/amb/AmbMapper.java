/**
 * 
 */
package to.science.core.modelx.amb;

import org.json.JSONObject;

import to.science.core.model.model.AbstractAgent;
import to.science.core.model.model.AbstractSimpleArray;

/**
 * <p>
 * Interface for methods required for mapping from toscience to amb and vice versa
 * 
 * AMB: 
 * </p>
 * 
 * @author aquast
 *
 */
public interface AmbMapper {

  /**
   * <p>
   * Map toscience model into amb model
   * </p>
   * 
   * @return mapped object as JSONObject 
   */
  public JSONObject getAMBJSONObject();
  
  /**
   * <p>
   * Map amb JSONObject into according toscience object
   * </p>
   * @param ambJSONObject
   * @return
   */
  public AbstractAgent getAgentMappedFromAmbJSONObject(JSONObject ambJSONObject);
  
  /**
   * @param ambJSONObject
   * @return
   */
  public AbstractSimpleArray getSimpleArrayFromAmbJSONObject(JSONObject ambJSONObject);
  
  
  
}
