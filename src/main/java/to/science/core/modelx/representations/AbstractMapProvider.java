/**
 * 
 */
package to.science.core.modelx.representations;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.json.JSONObject;

/**
 * Provides a Map representation of JSON objects respectively the to.science Model
 * 
 * @author aquast
 *
 */
public class AbstractMapProvider implements MapProvider {

  @Override
  public LinkedHashMap<String, Object> getLinkedHashMap(JSONObject anyJSONObject) {
    LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<>();
    // TODO Auto-generated method stub
    Iterator<String> jSONIterator = anyJSONObject.keys();
    while (jSONIterator.hasNext()) {
      String key = jSONIterator.next();
      linkedHashMap.put(key, anyJSONObject.get(key));
    }
    
    
    //linkedHashMap = (LinkedHashMap<String, Object>) anyJSONObject.toMap();
    return linkedHashMap;
  }

}
