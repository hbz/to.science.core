package to.science.core.model.implementation;

import java.util.LinkedHashMap;

import to.science.core.model.model.AbstractAgent;
import to.science.core.model.model.Agent;
import to.science.core.model.model.SimpleObject;

/**
 * <p>
 * An implementation for contributors as one kind of agents (especially persons). Provides methods to create 
 * and manipulate contributors.
 * </p> 
 * <p>  
 * Model represented in JSON:
 * 
 * <pre>"contributor" : {
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
 * Class makes use of generic mappings from AbstractAgent.class. If required you can override these mappings.
 * </p>
 * <p>
 * @author aquast
 * </p>
 */
public class Contributor extends AbstractAgent implements Agent, SimpleObject{

  /**
   * public Constructor
   */
  public Contributor(){
    
    simpleObject.put("academicDegree", "https://d-nb.info/standards/elementset/gnd#academicDegree/unknown");
    simpleObject.put("type", "Person");
  }

  @Override
  public LinkedHashMap<String, String> getModel() {
    //LinkedHashMap<String,String> model = new LinkedHashMap<>(genPropLoad.loadVocabMap("ContributorModel.properties"));
    return model;
  }
  
  /**
   * <p>Set the affiliation for the Agent by using the id 
   * (as uri based on https://ror.org uris) and determine prefLabel 
   * via properties file
   * </p>
   *   
   * @param id the ROR-Id as complete ROR-URL 
   */
  public void setAffiliationById(String id) {
    affiliation.setById(id);
  }

  @Override
  public void setAcademicDegree(String academicDegreeId) {
    simpleObject.put("academicDegree", academicDegreeId);
  }

  @Override
  public String getAcademicDegree() {
    String acadDegree = simpleObject.get("academicDegree")
        .replace("https://d-nb.info/standards/elementset/gnd#academicDegree/", "");
    return acadDegree;
  }


  

  
}
