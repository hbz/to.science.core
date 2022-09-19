/**
 * 
 */
package to.science.core.model.model;

/**
 *
 * A model for lobid affiliation:
 * 
 * As JSONObject:
 * 
 * affiliation : { "@id" : "String",
 *                 "prefLabel" : "String",
 *                 "type" : "Organization"
 *                 }
 * 
 * @author aquast
 */
public class BasicObject extends AbstractSimpleObject implements SimpleObject{
  
  public void put(String key, String value) {
    simpleObject.put(key, value);
  }

  @Override
  public ToScienceModel setById(String id) {
    // TODO Auto-generated method stub
    return null;
  }
  
}

