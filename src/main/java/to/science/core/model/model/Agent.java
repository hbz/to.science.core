/**
 * 
 */
package to.science.core.model.model;

import java.util.LinkedHashMap;

import org.json.JSONObject;

import to.science.core.model.implementation.Affiliation;

/**
 * <p>Interface defines basic methods required for the toscience agent model 
 * representing data structure of
 * </p>
 * <p>Model represented in JSON: 
 * <pre>"agent" : {
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
 * 
 * @author aquast
 *
 */
public interface Agent extends ToScienceModel{
  
  
  /**
   * @return an instance of affiliation
   */
  public Affiliation getAffiliation();

  /**
   * <p>
   * @return the agents Id as complete Id-URL. Id can have different base URL, e.g. https://orcid.org 
   * or an AdHocUri generated from appropriate toscience class.</p>
   */
  public String getId();  
  
  /**
   * <p>Method is not in use currently - therefore no descritpion is provided </p>
   * @return null
   */
  public LinkedHashMap<String,String> getModel();

  /**
   * @return the agents Name in the format firstname givenname for persons
   */
  public String getPrefLabel();
  
  /**
   * @param affiliation set affiliation object
   */
  public void setAffiliation(Affiliation affiliation);
  
  /**
   * @param prefLabel the agents name in the format firstname givenname for persons
   */
  public void setPrefLabel(String prefLabel);
  
  /**
   * @param id the agents Id as complete Id-URL. Id can have different base URL, e.g. https://orcid.org 
   * or an AdHocUri generated from appropriate toscience class. 
   */
  public void setId(String id);
  
  
}
