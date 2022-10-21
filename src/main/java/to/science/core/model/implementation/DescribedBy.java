/**
 * 
 */
package to.science.core.model.implementation;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.JSONObject;

import to.science.core.model.model.AbstractSimpleObject;
import to.science.core.model.model.SimpleObject;

/**
 * <p>
 * An implementation for toscience DescribedBy:
 * </p>
 * 
 * <p>
 * As JSONObject:
 * <pre>
 * isDescribedBy : { "@id" : "orca:XXXX-XXXX-XXX.rdf :the pure id without URI part but with .rdf ending",
 *                   "createdBy" : "an intern counter for a specific to.science account",
 *                   "submittedBy": "Name of the Person that submitted the resource",
 *                   "submittedByEmail": "E-mail of the Person that submitted the resource",
 *                   "modified": "timestamp from Fedora",
 *                   "objectTimestamp": "timestamp from Fedora",
 *                   "created": "timestamp from Fedora",
 *                   "describes": "orca:XXXX-XXXX-XXX :the pure id without URI part"
 *                 }
 * </pre>
 * </p>
 * <p>
 * @author aquast
 * </p>
 * 
 */
public class DescribedBy extends AbstractSimpleObject implements SimpleObject{
  
  LinkedHashMap<String, String> decribedByMap = null;
  
  public DescribedBy(LinkedHashMap<String, String> describedByMap) {
    this.decribedByMap = describedByMap;
  }
  
  
  @Override
  public JSONObject getAmbJSONObject() {
    JSONObject ambJSONObject = new JSONObject();;
    // TODO implement
    return ambJSONObject;
  }

  @Override
  public DescribedBy setById(String id) {
    simpleObject.put("@id", id + ".rdf");
    simpleObject.put("describes", id);
    Iterator<String> describedByIt = decribedByMap.keySet().iterator();
    
    while(describedByIt.hasNext()) {
      String key = describedByIt.next();
      simpleObject.put(key, decribedByMap.get(key));
    }
    return this;
  }

  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    this.setById(ambJSONObject.get("id").toString());
    return getJSONObject(); 
  }
}

