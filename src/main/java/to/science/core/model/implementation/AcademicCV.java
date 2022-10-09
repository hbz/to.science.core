/**
 * 
 */
package to.science.core.model.implementation;

import org.json.JSONArray;
import org.json.JSONObject;

import to.science.core.model.model.AbstractAgent;
import to.science.core.model.model.AbstractSimpleObject;
import to.science.core.model.model.SimpleObject;
import to.science.core.util.GenericPropertiesLoader;
import to.science.net.util.GenericLookupProvider;

/**
 * <p>
 * An implementation for ToScience AcademicCV. ToScience AcademicCV is a helper model for 
 * representing an Agents academic state and affiliations. 
 * It is an optional part of the Agent model as it provides some metadata redundancy and complexity 
 * and is thought to be almost a future part of the to.science model.
 * </p>
 * <p>
 * AcademicCV has this structure as JSONObject:
 * <pre>
 * academicCV : { 
 *                "latestAcademicDegree" : {
 *                   "@id" : "String",
 *                   "prefLabel" : "String"
 *                 },
 *                 "academicDegree" : [
 *                     {
 *                     "@id" : "String",
 *                     "prefLabel" : "String"
 *                     }
 *                 ],
 *                 "latestAffiliation" : {
 *                     "prefLabel": "Name of Organization",
 *                     "@id": "https://ror.org/XXXXX",
 *                     "type": "Organization"
 *                 },
 *                 "affiliation" : [
 *                     {
 *                     "prefLabel": "Name of Organization",
 *                     "@id": "https://ror.org/XXXXX",
 *                     "type": "Organization"
 *                     }
 *                ],                   
 *                // combined from latestAcademicDegree prefLabel + Agents prefLabel and latestAffiliation prefLabel
 *                "academicRepresentation" : "String", 
 *              }
 * 
 * </pre>
 * </p>
 * <p>
 * @author aquast
 * </p>
 * 
 */
public class AcademicCV extends AbstractSimpleObject implements SimpleObject{

  // TODO Implement class
  private String affiliationList = "resources/affiliation-de.properties";

  
  /**
   * <p>Set a academicCV inferred from the Agents Id expressed as complete Id-URI.
   * </p>
   *    
   * @param id URI of an Agent as String
   */
  @Override
  public AcademicCV setById(String id) {
    // TODO Implement method
    
    return null;
  }
  
  @Override
  public JSONObject getFromAmbJSONObject(JSONObject ambJSONObject) {
    // TODO Implement method

    return null;
  }

  
}

