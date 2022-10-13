/**
 * 
 */
package to.science.core.model.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;


/**
 * <p>
 * An abstract model for different kind of Toscience model implementations. 
 * Provides basic methods to create new instances and operate with them.
 * </p>
 * <p>
 * As JSON model:
 * <pre>
 * "objectName" : {
 *           "prefLabel" : "label",
 *           "@id" : "objectUri",
 *           "type" : "isOfType"
 *           } 
 * </pre>
 * </p>
 * 
 * @author aquast
 */
public abstract class AbstractSimpleObject extends AbstractToScienceModel implements SimpleObject {
  
  final static Logger logger = LogManager.getLogger(AbstractSimpleObject.class);

  // private ArrayList<HashMap<String,Object>> simpleObject = new ArrayList<>();
  public HashMap<String,String> simpleObject = new HashMap<>();
  
  /**
   * @return the @id of the simpleObject
   */
  public String getId() {
    return simpleObject.get("@id");
  }

  /**
   * @return the prefLabel of the simpleObject
   */
  public String getPrefLabel() {
    return simpleObject.get("prefLabel");
  }

  /**
   * @return the default simpleObject type 
   */
  public String getType() {
    return simpleObject.get("type");
  }

  /**
   * sets the preflabel (aka name) of simpleObject 
   * @param prefLabel
   */
  public void setPrefLabel(String prefLabel) {
    simpleObject.put("prefLabel", prefLabel);
  }
  
  /**
   * sets the @id of simpleObject
   * @param id
   */
  public void setId(String id) {
    simpleObject.put("@id", id);
  }

  /**
   *
   */
  public void setType(String type) {
    simpleObject.put("type", type);    
  }
  
  /**
   * @return
   */
  public int size() {
    return simpleObject.size();
  }
  
  /**
   * @return
   */
  public Iterator<String> getIterator() {
    Set<String> set =simpleObject.keySet();
    Iterator<String> setIterator = set.iterator();
    return setIterator;
  }
  
  /**
   * @param key
   * @return
   */
  public String get(String key) {
    return simpleObject.get(key);
  }
  
  /**
   * @return
   */
  public String getJson() {
    String json = null;
    JSONObject jsonObj = new JSONObject();
    
    jsonObj = this.getJSONObject();
    json = jsonObj.toString(1);
    logger.info(json);
    return json;
  }

  @Override
  public JSONObject getJSONObject() {
    JSONObject jsonObj = new JSONObject();
    
    Iterator<String> iterator = this.getIterator();
    while(iterator.hasNext()) {
      String key = iterator.next();
      jsonObj.put(key, this.get(key));
    }
    return jsonObj;
  }
    
  @Override
  public JSONObject getAmbJSONObject() {
    JSONObject ambJsonObj = new JSONObject();
    JSONObject jsonObj = this.getJSONObject();
    Iterator<String> iterator = jsonObj.keys();

    while(iterator.hasNext()) {
      String key = iterator.next();
      String ambKey = key;
      ambKey = key.replace("prefLabel", "name");
      // ambKey = ambKey.replace("prefLabel", "name");
      ambJsonObj.put(ambKey, jsonObj.get(key));
      
    }
    return ambJsonObj;
  }
  
  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    JSONObject jsonObj = this.getJSONObject();
    Iterator<String> iterator = ambJSONObject.keys();

    while(iterator.hasNext()) {
      String ambKey = iterator.next();
      String key = ambKey;
      key = ambKey.replace("name", "prefLabel");
      // key = key.replace("", "");
      jsonObj.put(key, ambJSONObject.get(ambKey));
      
    }
    return jsonObj;
  }
  

  
  
    

  
}

