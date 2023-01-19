package to.science.core.modelx.amb;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;

import to.science.core.model.implementation.Creator;
import to.science.core.model.implementation.Department;
import to.science.core.model.implementation.DescribedBy;
import to.science.core.model.implementation.Description;
import to.science.core.model.implementation.Funder;
import to.science.core.model.implementation.Language;
import to.science.core.model.implementation.Affiliation;
import to.science.core.model.implementation.Contributor;
import to.science.core.model.implementation.License;
import to.science.core.model.implementation.Medium;
import to.science.core.model.implementation.Part;
import to.science.core.model.implementation.Subject;
import to.science.core.model.implementation.Title;
import to.science.core.model.implementation.YearOfCopyright;
import to.science.core.model.model.AbstractToScienceModel;

/**
 * <p>
 * A generic mapper class providing mapping from ToScience Model to AMB Model
 * and vice versa
 * </p>
 * 
 * @author aquast
 *
 */
public abstract class AbstractAmbMapper implements AmbMapper {
  
  private Hashtable<String, AbstractToScienceModel> tosClasses = new Hashtable<>();
  private Hashtable<String, String> mappingNames = new Hashtable<>();
  private LinkedHashMap<String, String> describedByMap = new LinkedHashMap<>();
  private String namespace = "orca";
  private int debugCounter = 0;
  
  final static Logger logger = LogManager.getLogger(AbstractAmbMapper.class);

  public AbstractAmbMapper() {
    setTosModels();
    setMappings();
  }


  /**
   * <p>
   * Set up a Hashtable with keys/values, where keys are the to.science model
   * target names and values instances of the appropriate Classes. 
   * E.g. key "department" maps to new Instance of Department.class
   * </p>
   */
private void setTosModels() {

    // create an Hashtable with all parts of to.science.model represented as
    // JSONObject
    logger.info("Initialize Hashtable with new TosModels for the " + ++debugCounter + ". time");
    tosClasses.put("affiliation", new Affiliation());
    tosClasses.put("creator", new Creator());
    tosClasses.put("contributor", new Contributor());
    tosClasses.put("department", new Department());
    tosClasses.put("language", new Language());
    tosClasses.put("license", new License());
    tosClasses.put("medium", new Medium());
    tosClasses.put("subject", new Subject());
    tosClasses.put("description", new Description());
    tosClasses.put("title", new Title());
    tosClasses.put("yearOfCopyright", new YearOfCopyright());
    tosClasses.put("funder", new Funder());
    tosClasses.put("license", new License());
    tosClasses.put("hasPart", new Part());
    tosClasses.put("isDescribedBy", new DescribedBy(describedByMap));

  }

  /**
   * <p>
   * Set up a Hashtable with keys/values, where keys are the to.science model
   * target names and values the AMB source names. E.g. "department" is the target
   * for all "learningResource" sources in AMB json
   * </p>
   */
  private void setMappings() {

    mappingNames.put("department", "learningResourceType");
    mappingNames.put("medium", "about");
    mappingNames.put("creator", "creator");
    mappingNames.put("contributor", "contributor");
    mappingNames.put("description", "description");
    mappingNames.put("title", "name");
    mappingNames.put("yearOfCopyright", "datePublished");
    mappingNames.put("funder", "funder");
    mappingNames.put("license", "license");
    mappingNames.put("subject", "keywords");
    mappingNames.put("hasPart", "encoding");
    mappingNames.put("isDescribedBy", "isDescribedBy");
    
  }

  /**
   * Map metadata completely describing a resource in the AMB metadata schema into a complete 
   * description provided as to.science schema
   * 
   * AMB schema is defined by 
   * https://dini-ag-kim.github.io/amb/draft/
   * 
   * 
   * @param ambJSONObj AMB metadata for a resource as JSON 
   * @return to.science modeled metadata for the whole resource, also as JSON
   */
  public JSONObject getTosJSONObject(JSONObject ambJSONObj) {

    JSONObject tosJSONObj = new JSONObject();
    Enumeration<String> simpleArrayEnum = mappingNames.keys();

    if (ambJSONObj.has("id")) {
      String[] uriPart = ambJSONObj.get("id").toString().split(namespace + ":");
      tosJSONObj.put("@id", namespace + ":" + uriPart[1]);
      tosJSONObj.put("isPrimaryTopic",  namespace + ":" + uriPart[1]);
      tosJSONObj.put("@context", uriPart[0].replace("resource/", "context.json"));
      
      describedByMap.put("@id", namespace + ":" + uriPart[1]);
      describedByMap.put("describes", namespace + ":" + uriPart[1]);
    }
      
    try {
      while (simpleArrayEnum.hasMoreElements()) {
        String key = simpleArrayEnum.nextElement();
        if (ambJSONObj.has(mappingNames.get(key))) {
          JSONArray ambArr = ambJSONObj.optJSONArray(mappingNames.get(key));
          if (ambArr != null && !mappingNames.get(key).equals("keywords")) {
            JSONArray arr = new JSONArray();
            for (int i = 0; i < ambArr.length(); i++) {
              JSONObject obj = tosClasses.get(key).getFromAmbJSONObject(ambArr.optJSONObject(i));
              logger.debug(obj.toString(1));
              arr.put(obj);
            }
            tosJSONObj.put(key, arr);
          }

          // TODO find a logic to circumvent this block especially created for keywords   
          else if (mappingNames.get(key).equals("keywords")) {
            JSONObject obj = tosClasses.get(key).getFromAmbJSONObject(ambJSONObj);
            JSONArray arr = obj.getJSONArray(key);
            tosJSONObj.put(key, arr);
            logger.debug(obj.toString(1));
          }
          
          else if (ambJSONObj.optJSONObject(mappingNames.get(key)) != null) {
            JSONObject obj = tosClasses.get(key).getFromAmbJSONObject(ambJSONObj.getJSONObject(mappingNames.get(key)));
            tosJSONObj.put(key, obj);
            logger.debug(obj.toString(1));
          }

          else if (ambJSONObj.optString(mappingNames.get(key)) != null) {
            JSONObject obj = tosClasses.get(key).getFromAmbJSONObject(ambJSONObj);
            JSONArray arr = obj.getJSONArray(key);
            tosJSONObj.put(key, arr);
          }
         
        }
      }
    } catch (Exception e) {
      logger.error(e.toString());
    }
    
    if(tosClasses.containsKey("isDescribedBy")) {
      AbstractToScienceModel described = tosClasses.get("isDescribedBy");
      JSONObject obj = described.getFromAmbJSONObject(ambJSONObj);
      tosJSONObj.put("isDescribedBy", obj);
    }
    
    tosJSONObj.put("contentType", "researchData");

    return tosJSONObj;
  };
  
  /**
   * Set Map with key/values of DescribedBy Instance
   * @param isDescribedByMap
   */
  public void setIsDescribedByMap(LinkedHashMap<String,String> isDescribedByMap) {
    this.describedByMap = isDescribedByMap;
    tosClasses.put("isDescribedBy", new DescribedBy(isDescribedByMap));
  }
  
  /**
   * Set namespace of resource-Id, other than default namespace "orca" 
   * @param namespace
   */
  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

}
