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
 * An implementation for toscience medium:
 * </p>
 * 
 * <p>
 * As JSONObject:
 * <pre>
 * medium : { "@id" : "String",
 *            "prefLabel" : "String",
 *            }
 * 
 * </pre>
 * </p>
 * <p>
 * @author aquast
 * </p>
 * 
 */
public class Medium extends AbstractSimpleObject implements SimpleObject{
  
  private String mediaList = "OrcaMedientypen-de.properties";

  /**
   * <p>Set a complete medium inferred from the Id expressed as complete Id-URI. Mediums prefLabel is resolved by using the KIM/DINI-Id. 
   * Method currently used the OrcaMedientypen-de.properties file for this mapping.
   * </p>
   *    
   * @param id
   * @return
   */
  @Override
  public Medium setById(String id) {
    simpleObject.put("@id", id);
    simpleObject.put("prefLabel", new GenericPropertiesLoader().loadVocabMap(mediaList).get(id));
    return this;
  }
  
  public JSONObject getAmbJSONObject() {
    JSONObject ambJSONObject = new JSONObject();
    ambJSONObject.put("inScheme", "https://w3id.org/kim/hcrt");
    JSONObject prefLabelObj = new JSONObject();
    prefLabelObj.put("de",simpleObject.get("prefLabel"));
    ambJSONObject.put("prefLabel", prefLabelObj);
    ambJSONObject.put("id", simpleObject.get("@id"));
    ambJSONObject.put("type", "Concept");
    return ambJSONObject;
  }
}

