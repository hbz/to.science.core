/**
 * 
 */
package to.science.core.model.implementation;

import org.json.JSONObject;

import to.science.core.model.model.AbstractSimpleObject;
import to.science.core.model.model.SimpleObject;

/**
 * <p>
 * An implementation for toscience Part:
 * </p>
 * 
 * <p>
 * As JSONObject:
 * <pre>
 * hasPart : { "@id" : "String",
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
public class Part extends AbstractSimpleObject implements SimpleObject{
  
  private String tosNamespace = "orca";
  
  @Override
  public JSONObject getAmbJSONObject() {
    JSONObject ambJSONObject = new JSONObject();
    ambJSONObject.put("contentUrl", simpleObject.get("@id"));
    ambJSONObject.put("type", "MediaObject");
    return ambJSONObject;
  }

  @Override
  public Part setById(String id) {
    simpleObject.put("@id", id);
    //simpleObject.put("prefLabel", new GenericPropertiesLoader().loadVocabMap(departmentList).get(id));
    return this;
  }

  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    JSONObject tosJSONObject = new JSONObject();
    String pid = ambJSONObject.get("contentUrl").toString().replace("/data" , "");
    pid = pid.substring(ambJSONObject.get("contentUrl").toString().indexOf(tosNamespace + ":"));
    tosJSONObject.put("@id", pid);
    // TODO implement request for the Title of the child object
    tosJSONObject.put("prefLabel", "No Title");
    return tosJSONObject; 
  }
}

