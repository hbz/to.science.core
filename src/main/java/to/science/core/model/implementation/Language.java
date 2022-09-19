/**
 * 
 */
package to.science.core.model.implementation;

import to.science.core.model.model.AbstractSimpleObject;
import to.science.core.model.model.SimpleObject;
import to.science.core.util.LanguageResolver;

/**
 * <p>
 * An implementation for toscience language:
 * </p>
 * 
 * <p>
 * As JSONObject:
 * <pre>
 * language : { "@id" : "http://id.loc.gov/vocabulary/iso639-2/XXX",
 *              "prefLabel" : "String",
 *            }
 * 
 * </pre>
 * </p>
 * <p>
 * @author aquast
 * </p>
 * 
 */
public class Language extends AbstractSimpleObject implements SimpleObject{
   
  private String uri1 = "http://id.loc.gov/vocabulary/iso639-1/";
  private String uri2 = "http://id.loc.gov/vocabulary/iso639-2/";
  // private ArrayList<HashMap<String,Object>> affiliation = new ArrayList<>();
  // private String languageList = "ResearchOrganizationsRegistry-de.properties";

  /**
   * <p>Set a complete language inferred from the Id expressed as 2 letter tag. 
   * Language prefLabel is resolved by using the Locale class. 
   * </p>
   *    
   * @param id
   */
  public void setBy2LetterTag(String id) {
    String iso638_1_id = uri1 + id;
    String iso638_2_id = uri2 + LanguageResolver.get3LetterTag(iso638_1_id);
    simpleObject.put("@id", iso638_2_id);
    simpleObject.put("prefLabel", new LanguageResolver().getLocalefrom2LetterTag(id).getDisplayLanguage());

  }
    
  /**
   * <p>Set a complete language inferred from the Language Id expressed as 3 letter Uri. 
   * Language prefLabel is resolved by using the Locale class. 
   * </p>
   *    
   * @param id
   */
  public Language setById(String id) {
    simpleObject.put("@id", id);
    String tag2 = LanguageResolver.get2LetterTag(id);
    simpleObject.put("prefLabel", new LanguageResolver().getLocalefrom2LetterTag(tag2).getDisplayLanguage());
    return this;
  }
}

