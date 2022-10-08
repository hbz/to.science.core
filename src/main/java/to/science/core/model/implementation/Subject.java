/**
 * 
 */
package to.science.core.model.implementation;

import org.json.JSONArray;
import org.json.JSONObject;

import to.science.core.model.model.AbstractSimpleObject;
import to.science.core.model.model.SimpleObject;
import to.science.core.util.AdHocUriProvider;
import to.science.net.util.GenericLookupProvider;

/**
 * <p>
 * An implementation for toscience subject. Id is a complete Id-URI. Can be from
 * GND or an toscience AdHocUri
 * </p>
 * 
 * <p>
 * As JSONObject:
 * 
 * <pre>
 * subject : { "@id" : "String",
 *             "prefLabel" : "String",
 *           }
 * 
 * </pre>
 * </p>
 * <p>
 * 
 * @author aquast
 *         </p>
 * 
 */
public class Subject extends AbstractSimpleObject implements SimpleObject {

  /**
   * <p>
   * Set a subject inferred from the prefLabel
   * </p>
   * 
   * @param prefLabel
   */
  public void setSubjectByName(String prefLabel) {
    // simpleObject.put("@id", id);
    simpleObject.put("prefLabel", prefLabel);
    String id = lookUpGndId(prefLabel);
    if(id != null) {
      simpleObject.put("@id", id);
    }
    if(simpleObject.get("@id") == null) {
      simpleObject.put("@id", new AdHocUriProvider().getAdhocUri(prefLabel));      
    }
  }

  @Override
  public Subject setById(String id) {
    // TODO Auto-generated method stub
    return this;
  }
  
  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    JSONObject tosJSONObject = new JSONObject();
    JSONArray ambArr = ambJSONObject.getJSONArray("keywords");
    JSONArray arr = new JSONArray();
    for (int i = 0; i < ambArr.length(); i++) {
      JSONObject obj = new JSONObject();
      setSubjectByName(ambArr.get(i).toString());
      obj.put("prefLabel", simpleObject.get("prefLabel").toString());
      obj.put("@id", simpleObject.get("@id").toString());
      arr.put(obj);
    }
    tosJSONObject.put("subject", arr);
    return tosJSONObject;
  }


  private String lookUpGndId(String prefLabel) {
    String gndSubjectId = null;

    GenericLookupProvider genApiProv = new GenericLookupProvider(
        "https://lobid.org/gnd/search?filter=type:SubjectHeadingSensoStricto&format=json&q=preferredName:", prefLabel);
    JSONObject lookupObject = genApiProv.getResponseAsJSONObject();
    if (lookupObject != null && lookupObject.has("member")) {
      JSONArray arr = lookupObject.getJSONArray("member");
      if (arr.length() == 1) {
        JSONObject jsonObj = arr.getJSONObject(0);
        gndSubjectId = jsonObj.get("id").toString();
      }
    }

    return gndSubjectId;
  }
}
