/**
 * 
 */
package to.science.core.model.model;

import java.util.LinkedHashMap;
import java.util.Iterator;

import org.json.JSONObject;
import org.apache.log4j.Logger;

import to.science.core.model.implementation.Affiliation;
import to.science.core.util.GenericPropertiesLoader;

/**
 * <p>
 * An abstract model for different kind of agents (especially persons). Provides basic methods to create 
 * new agents and operate with them.
 * </p> 
 * <p>  
 * Model represented in JSON:
 * 
 * <pre>agent : {
 *         "affiliation": {
 *             "prefLabel": "Name of Organization",
 *             "@id": "https://ror.org/XXXXX",
 *             "type": "Organization"
 *             },
 *          "academicDegree": "https://d-nb.info/standards/elementset/gnd#academicDegree/XXXX",
 *          "prefLabel": "Firstname Givenname",
 *          "@id": "https://orcid.org/0000-XXXX-XXXX-XXXX",
 *          "type": "Person"
 *          }
 * </pre>
 * </p>
 * <p>
 * Class also provides generic mappings for the agents. If required you can override mappings for specific agents
 * </p>
 * <p>
 * @author aquast
 * </p>
 */
public abstract class AbstractAgent extends AbstractSimpleObject implements Agent, SimpleObject{

  final static Logger logger = Logger.getLogger(AbstractAgent.class);

  protected GenericPropertiesLoader genPropLoad = new GenericPropertiesLoader();
  protected LinkedHashMap<String,String> model = new LinkedHashMap<>();
  protected Affiliation affiliation = new Affiliation();
  
  protected StringBuffer academicLabel = new StringBuffer();
  
  /**
   * @return the affiliation
   */
  public Affiliation getAffiliation() {
    return affiliation;
  }
  
  public void setAcademicLabel(){
    
    simpleObject.put("academicLabel", this.getAcademicLabel());
  }

  /**
   * @return the id
   */
  public String getId() {
    return simpleObject.get("@id");
  }

  /**
   * <p>Set the academic degree of an agent. Because it is a String, 
   * the Id of academicDegree is used
   * </p>
   * @param academicDegreeId the Id of an academic degree as complete DNB-Id: 
   * https://d-nb.info/standards/elementset/gnd#academicDegree/
   * 
   */
  public abstract void setAcademicDegree(String academicDegreeId);

  public abstract String getAcademicDegree();

  /**
   * @param affiliation the affiliation to set
   */
  public void setAffiliation(Affiliation affiliation) {
    this.affiliation = affiliation;
  }

  @Override
  public void setId(String id) {
    simpleObject.put("@id", id);   
  }

  @Override
  public void setPrefLabel(String prefLabel) {
    simpleObject.put("prefLabel", prefLabel);
  }

  @Override
  public void setType(String Type) {
    simpleObject.put("type", Type);
    
  }
  
  @Override
  public JSONObject getJSONObject() {
    JSONObject jsonObj = new JSONObject();
    
    Iterator<String> iterator = this.getIterator();
    while(iterator.hasNext()) {
      String key = iterator.next();
      jsonObj.put(key, simpleObject.get(key));
      jsonObj.put("affiliation", affiliation.getJSONObject());
    }
    return jsonObj;
  }
  
  @Override
  public String getJson() {
    JSONObject jsonObj = this.getJSONObject();
    String json = jsonObj.toString(1);
    logger.debug(json);
    return json;
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
      ambKey = ambKey.replace("@id", "id");
      ambKey = ambKey.replace("academicDegree", "honorificDegree");
      ambJsonObj.put(ambKey, jsonObj.get(key));
      
    }
    ambJsonObj.put("affiliation", affiliation.getAmbJSONObject());
    String json = ambJsonObj.toString(1);
    logger.debug("AMB: "  + json);
    
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
      key = key.replace("honorificPrefix", "academicDegree");
      key = key.replace("honoricPrefix", "academicDegree");
      key = key.replace("id", "@id");
      jsonObj.put(key, ambJSONObject.get(ambKey));
      
    }
    jsonObj.put("affiliation", affiliation.getFromAmbJSONObject(ambJSONObject.getJSONObject("affiliation")));
    String json = jsonObj.toString(1);
    logger.debug("toscience: "  + json);
    return jsonObj;
    
  }
  
  public String getAcademicLabel() {
    academicLabel.append(this.getAcademicDegree() + " ");
    academicLabel.append(this.getPrefLabel() + " (");
    academicLabel.append(affiliation.getPrefLabel() + ")");
    return academicLabel.toString();
  }

  @Override
  public AbstractAgent setById(String id) {
    if (id.startsWith("https://orcid")) {
      
    }
    // TODO Implement method
    return this;
  }
  
  

}
