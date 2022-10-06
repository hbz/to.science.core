package to.science.core.modelx.amb;

import java.util.Enumeration;
import java.util.Hashtable;

import org.json.JSONObject;

import to.science.core.model.implementation.Creator;
import to.science.core.model.implementation.Department;
import to.science.core.model.implementation.Description;
import to.science.core.model.implementation.Funder;
import to.science.core.model.implementation.Language;
import to.science.core.model.implementation.Affiliation;
import to.science.core.model.implementation.Contributor;
import to.science.core.model.implementation.License;
import to.science.core.model.implementation.Medium;
import to.science.core.model.implementation.Subject;
import to.science.core.model.implementation.Title;
import to.science.core.model.model.ToScienceModel;

/**
 * <p>
 * A generic mapper class providing mapping from ToScience Model to AMB Model and vice versa
 * </p>
 * @author aquast
 *
 */
public abstract class AbstractAmbMapper {

  public AbstractAmbMapper() {
    setTosObjects();
  }
  
  private Hashtable<String, ToScienceModel> tosObjectClasses = new Hashtable<>();
  private Hashtable<String, ToScienceModel> tosArrayClasses = new Hashtable<>();
  
  
  private void setTosObjects() {
    
    // create a Hashtable with all parts of to.science.model represented as JSONObject 
    tosObjectClasses.put("affiliation", new Affiliation());
    tosObjectClasses.put("creator", new Creator());
    tosObjectClasses.put("contributor", new Contributor());
    tosObjectClasses.put("department", new Department());
    tosObjectClasses.put("funder", new Funder());
    tosObjectClasses.put("language", new Language());
    tosObjectClasses.put("license", new License());
    tosObjectClasses.put("medium", new Medium());
    tosObjectClasses.put("subject", new Subject());
  
  }
  
  private void setTosArrays() {
    
    // create a Hashtable with all parts of to.science.model represented as JSONArray of literals      
    Hashtable<String, String> literal2ArrayMap = new Hashtable<>();
    literal2ArrayMap.put("yearOfCopyright", "datePublished");
    literal2ArrayMap.put("description", "description");
    literal2ArrayMap.put("title", "name");

  }
    
  public String getAmbJson() {
    String ambJson = null;
    
    Enumeration<String> tosEnum = tosObjectClasses.keys();
    
    while(tosEnum.hasMoreElements()) {
     String tosKey = tosEnum.nextElement();
     
     
     
    }
    
    
    
    return null;
  }
  
  public JSONObject getTosJSONObject(JSONObject ambJSONObj) {
    
    JSONObject tosJSONObj = new JSONObject();
    
    License license = new License();
    license.getFromAmbJSONObject(ambJSONObj.getJSONObject("license"));
    
    return tosJSONObj;
  };

  
}
