package to.science.core.modelx.amb;

import java.util.Enumeration;
import java.util.Hashtable;

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

/**
 * @author aquast
 *
 */
public abstract class AbstractAmbMapper {

  public AbstractAmbMapper() {
    tosClasses.put("Affiliation", new Affiliation());
    tosClasses.put("Creator", new Creator());
    tosClasses.put("Contributor", new Contributor());
    tosClasses.put("Department", new Department());
    tosClasses.put("Description", new Description());
    tosClasses.put("Funder", new Funder());
    tosClasses.put("Language", new Language());
    tosClasses.put("License", new License());
    tosClasses.put("Medium", new Medium());
    tosClasses.put("Subject", new Subject());
    tosClasses.put("Title", new Title());
    
  }
  
  private Hashtable<String, Object> tosClasses = new Hashtable<>();
  
  public String getAmbJson() {
    String ambJson = null;
    
    Enumeration<String> tosEnum = tosClasses.keys();
    
    while(tosEnum.hasMoreElements()) {
     String tosKey = tosEnum.nextElement();
     
     
    }
    
    
    
    return null;
  }

  
}
