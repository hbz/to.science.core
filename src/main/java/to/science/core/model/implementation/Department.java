/**
 * 
 */
package to.science.core.model.implementation;

import org.json.JSONObject;

import to.science.core.model.model.AbstractSimpleObject;
import to.science.core.model.model.SimpleObject;
import to.science.core.model.model.ToScienceModel;
import to.science.core.util.GenericPropertiesLoader;

/**
 * <p>
 * An implementation for toscience Department:
 * </p>
 * 
 * <p>
 * As JSONObject:
 * <pre>
 * department : { "@id" : "String",
 *                 "prefLabel" : "String",
 *                 }
 * 
 * </pre>
 * </p>
 * <p>
 * @author aquast
 * </p>
 * 
 */
public class Department extends AbstractSimpleObject implements SimpleObject{
  
  private String departmentList = "resources/department-de.properties";

  
  @Override
  public JSONObject getAmbJSONObject() {
    JSONObject ambJSONObject = new JSONObject();
    ambJSONObject.put("inScheme", "https://w3id.org/kim/hochschulfaechersystematik");
    JSONObject prefLabelObj = new JSONObject();
    prefLabelObj.put("de",simpleObject.get("prefLabel"));
    ambJSONObject.put("prefLabel", prefLabelObj);
    ambJSONObject.put("id", simpleObject.get("@id"));
    ambJSONObject.put("type", "Concept");
    return ambJSONObject;
  }

  @Override
  public Department setById(String id) {
    simpleObject.put("@id", id);
    simpleObject.put("prefLabel", new GenericPropertiesLoader().loadVocabMap(departmentList).get(id));
    return this;
  }

  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    JSONObject tosJSONObject = new JSONObject();
    tosJSONObject.put("@id", ambJSONObject.get("id"));
    tosJSONObject.put("prefLabel", ambJSONObject.getJSONObject("prefLabel").get("de"));
    return tosJSONObject; 
  }
}

