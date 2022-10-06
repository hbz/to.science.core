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
   * Get a AMBmodel object mapped from any ToScience model object
   * </p>
   * <p>
   * For Schema Details see: <a href="https://dini-ag-kim.github.io/amb/draft/" target="_blank">AMB Schema</a>
   * </p>
   * <p>
   * @return JSONObject with object data mapped to AMBmodel (formely known as LRMI)
   * </p>
   */
  public JSONObject getAmbJSONObject();

  /**
   * <P>
   * Get a specific ToScience model object by mapping an AMBmodel object into ToScience model object
   * </p>
   * <p>
   * @return JSONObject with object data mapped from AMB (formely known as LRMI)
   * </p>
   */
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject);
  
}
