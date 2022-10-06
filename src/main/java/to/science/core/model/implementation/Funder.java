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
 * An implementation for toscience funder:
 * </p>
 * 
 * <p>
 * As JSONObject:
 * <pre>
 * funder : { "@id" : "String",
 *                 "prefLabel" : "String",
 *                 "type" : "FundingScheme"
 *                 }
 * 
 * </pre>
 * </p>
 * <p>
 * @author aquast
 * </p>
 * 
 */
public class Funder extends AbstractSimpleObject implements SimpleObject{
  
  private String funderList = "resources/funder-de.properties";

  /**
   * <p>Set a complete funder inferred from the Id expressed as complete Id-URI. Funders prefLabel is resolved by using the Funder-Id. 
   * Method currently used the Funder.properties file for this mapping.
   * </p>
   *    
   * @param id
   */
  public Funder setById(String id) {
    simpleObject.put("@id", id);
    simpleObject.put("prefLabel", new GenericPropertiesLoader().loadVocabMap(funderList).get(id));
    simpleObject.put("type", "FundingScheme");
    //System.out.println(simpleObject.get("@id"));
    return this;
  }
  
  public JSONObject getAmbJSONObject() {
    JSONObject ambJSONObject = new JSONObject();
    ambJSONObject.put("inScheme", "https://w3id.org/kim/hcrt");
    JSONObject prefLabelObj = new JSONObject();
    prefLabelObj.put("de",simpleObject.get("prefLabel"));
    ambJSONObject.put("prefLabel", prefLabelObj);
    System.out.println("Id: " + simpleObject.get("@id"));
    ambJSONObject.put("id", simpleObject.get("@id"));
    ambJSONObject.put("type", "FundingScheme");
    return ambJSONObject;
  }
  
  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    JSONObject tosJSONObject = new JSONObject();
    tosJSONObject.put("@id", ambJSONObject.get("url"));
    tosJSONObject.put("prefLabel", ambJSONObject.get("name"));
    return tosJSONObject; 
  }

}

