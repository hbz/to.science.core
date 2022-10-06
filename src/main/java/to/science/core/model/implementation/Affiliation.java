/**
 * 
 */
package to.science.core.model.implementation;

import org.json.JSONArray;
import org.json.JSONObject;

import to.science.core.model.model.AbstractSimpleObject;
import to.science.core.model.model.SimpleObject;
import to.science.core.util.GenericPropertiesLoader;
import to.science.net.util.GenericLookupProvider;

/**
 * <p>
 * An implementation for ToScience Affiliation. ToScience Affiliation is always part of an Agent model.
 * </p>
 * <p>
 * Affiliation has this structure as JSONObject:
 * <pre>
 * affiliation : { "@id" : "String",
 *                 "prefLabel" : "String",
 *                 "type" : "Organization"
 *                 }
 * 
 * </pre>
 * </p>
 * <p>
 * @author aquast
 * </p>
 * 
 */
public class Affiliation extends AbstractSimpleObject implements SimpleObject{
  
  public Affiliation() {
    simpleObject.put("type", "Organization");
    //AbstractToScienceModel.registerModel("Affiliation", new Affiliation());

  }
  // private ArrayList<HashMap<String,Object>> affiliation = new ArrayList<>();
  private String affiliationList = "resources/affiliation-de.properties";

  /**
   * <p>Set a complete affiliation inferred from the Id expressed as complete Id-URI. Affiliations prefLabel is resolved by using the ROR-Id. 
   * Method currently used the ResearchOrganizationsRegistry-de.properties file for this mapping. Using the ROR-API is planned
   * </p>
   *    
   * @param id URI of ROR-Id as String
   */
  @Override
  public Affiliation setById(String id) {
    simpleObject.put("id", id);
    simpleObject.put("prefLabel", new GenericPropertiesLoader().loadVocabMap(affiliationList).get(id));
    
    // Affiliation is not in our NRW affiliation list. So we look at ror.org
    if(simpleObject.get("prefLabel") == null) {
      simpleObject.put("prefLabel", lookUpRorId(id.replace("https://ror.org/", "")));
    }
    return this;
  }
  
  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    JSONObject tosJSONObject = new JSONObject();
    tosJSONObject.put("@id", ambJSONObject.get("id"));
    Affiliation affil = this.setById(ambJSONObject.get("id").toString());
    tosJSONObject.put("prefLabel", affil.get("prefLabel"));
    tosJSONObject.put("type", "Organization");
    return tosJSONObject; 
  }

  private String lookUpRorId(String id) {
    String label = null;
    
    GenericLookupProvider genApiProv = new GenericLookupProvider("https://api.ror.org/organizations/", id);
    JSONObject lookupObject = genApiProv.getResponseAsJSONObject();
    if (lookupObject.has("labels")) {
        JSONArray arr = lookupObject.getJSONArray("labels");
        JSONObject jsonObj = arr.getJSONObject(0);
        label = jsonObj.get("label").toString();
      }
    
    return label;
  }
  
}

