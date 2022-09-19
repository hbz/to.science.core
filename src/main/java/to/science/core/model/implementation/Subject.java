/**
 * 
 */
package to.science.core.model.implementation;

import to.science.core.model.model.AbstractSimpleObject;
import to.science.core.model.model.SimpleObject;
import to.science.core.model.model.ToScienceModel;
import to.science.core.util.AdHocUriProvider;

/**
 * <p>
 * An implementation for toscience subject. Id is a complete Id-URI. 
 * Can be from GND or an toscience AdHocUri 
 * </p>
 * 
 * <p>
 * As JSONObject:
 * <pre>
 * subject : { "@id" : "String",
 *             "prefLabel" : "String",
 *           }
 * 
 * </pre>
 * </p>
 * <p>
 * @author aquast
 * </p>
 * 
 */
public class Subject extends AbstractSimpleObject implements SimpleObject{
  

  /**
   * <p>Set a subject inferred from the prefLabel
   * </p>
   *    
   * @param prefLabel
   */
  public void setSubjectByName(String prefLabel) {
    // simpleObject.put("@id", id);
    simpleObject.put("prefLabel", prefLabel);
    simpleObject.put("@id", new AdHocUriProvider().getAdhocUri(prefLabel));
  }

  @Override
  public Subject setById(String id) {
    // TODO Auto-generated method stub
    return this;
  }

  
  
}

