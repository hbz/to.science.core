/**
 * 
 */
package to.science.core.model.implementation;

import org.json.JSONObject;

import to.science.core.model.model.AbstractSimpleObject;
import to.science.core.model.model.SimpleObject;
import to.science.core.util.GenericPropertiesLoader;

/**
 * <p>
 * An implementation for toscience license. Id is a complete Id-URI. 
 * </p>
 * 
 * <p>
 * As JSONObject:
 * <pre>
 * subject : { "@id" : "String",
 *             "prefLabel" : "String",
 *           }
 * 
 * </pre>
 * </p>
 * <p>
 * @author aquast
 * </p>
 * 
 */
public class License extends AbstractSimpleObject implements SimpleObject{
  
  private String licensesList = "Licenses-de.properties";

  /**
   * <p>Set a complete license inferred from the Id expressed as complete Id-URI. Licenses prefLabel is resolved by using the License-Id. 
   * Method currently used the Licenses-de.properties file for this mapping.
   * </p>
   *    
   * @param id
   */
  public License setById(String id) {
    simpleObject.put("@id", id);
    simpleObject.put("prefLabel", new GenericPropertiesLoader().loadVocabMap(licensesList).get(id));
    return this;

  }
  
  public JSONObject getAmbJSONObject() {
    JSONObject ambJSONObject = new JSONObject();
    ambJSONObject.put("id",  simpleObject.get("@id"));
    return ambJSONObject;
  }


  
  
}

