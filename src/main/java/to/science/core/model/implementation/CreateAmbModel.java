/**
 * 
 */
package to.science.core.model.implementation;

import java.util.Enumeration;
import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import to.science.core.model.implementation.Affiliation;
import to.science.core.model.implementation.Contributor;
import to.science.core.model.implementation.Creator;
import to.science.core.model.implementation.Department;
import to.science.core.model.implementation.Description;
import to.science.core.model.implementation.Funder;
import to.science.core.model.implementation.Language;
import to.science.core.model.implementation.License;
import to.science.core.model.implementation.Medium;
import to.science.core.model.implementation.Subject;
import to.science.core.model.implementation.Title;
import to.science.core.model.model.AbstractAgent;
import to.science.core.model.model.ToScienceModel;

/**
 * @author aquast
 *
 */
public class CreateAmbModel {
  
  public CreateAmbModel(){
    createTsmStub();
    setExampleModel();
    
    tosObj.put("Affiliation", affiliation);
    tosObj.put("Creator", creator);
    tosObj.put("Contributor", contributor);
    tosObj.put("Department", department);
    // tosObj.put("Description", new Description());
    tosObj.put("Funder", funder);
    tosObj.put("Language", language);
    tosObj.put("License", license);
    tosObj.put("Medium", medium);
    tosObj.put("Subject", subject);
    // tosObj.put("Title", title);

  
  }

  private JSONObject tsmObj = new JSONObject();
  private Hashtable<String, ToScienceModel> tosObj = new Hashtable<>();

  private Affiliation affiliation = new Affiliation();
  private Creator creator = new Creator();
  private Contributor contributor = new Contributor();
  private Department department = new Department();
  private Description description = new Description();
  private Funder funder = new Funder();
  private Language language = new Language();
  private License license = new License();
  private Medium medium = new Medium();
  private Subject subject = new Subject();
  private Title title = new Title();
  

  final static Logger logger = LoggerFactory.getLogger(CreateAmbModel.class);


  /**
   * @param args
   */
  public static void main(String[] args) {
    
    CreateAmbModel tsm = new CreateAmbModel();
    
    
    Enumeration<String> tosKeys = tsm.tosObj.keys();
    
    while(tosKeys.hasMoreElements()) {
      String key = tosKeys.nextElement();
      ToScienceModel tos = tsm.tosObj.get(key);
      logger.info(key);
      logger.info(tos.getAmbJSONObject().toString(1));
      
   
    }

  }
  
  public void setExampleModel() {
    
    description = new Description();
    description.addItem("1. Ein neues Model für ToScience");
    description.addItem("2. Inklusive einer umfangreichen API-Dokumentation");
    description.addItem("3. Ohne PlayFramework - garantiert :-)");
    JSONObject tsmObj = this.getTSMStub();
      for(int i=0; i < description.size(); i++) {
        tsmObj.append("description", description.getItem(i));
      }
    
      tsmObj.put("title", "Ein neuer Titel");
      

      affiliation = new Affiliation();
      affiliation.setById("https://ror.org/03y02pg02");

      creator = new Creator();
      creator.setAcademicDegree("https://d-nb.info/standards/elementset/gnd#academicDegree/Dr.");
      creator.setPrefLabel("Andres Quast");
      creator.setId("https://orcid.org/0000-0002-7662-8211");
      creator.setAffiliation(affiliation);
      creator.setAcademicLabel();
      JSONArray creatorList = new JSONArray();
      creatorList.put(creator.getJSONObject());
      tsmObj.put("creator", creatorList);
      
      department = new Department();
      department.setById("https://w3id.org/kim/hochschulfaechersystematik/n072");
      tsmObj.append("department", department.getJSONObject());
      
      department = new Department();
      department.setById("https://w3id.org/kim/hochschulfaechersystematik/n59");
      tsmObj.append("department", department.getJSONObject());

      medium = new Medium();
      medium.setById("https://w3id.org/kim/hcrt/course");
      tsmObj.append("medium", medium.getJSONObject());

      medium = new Medium();
      medium.setById("https://w3id.org/kim/hcrt/slide");
      tsmObj.append("medium", medium.getJSONObject());

      subject = new Subject();
      subject.setSubjectByName("1. Optische Fernerkundung");
      tsmObj.append("subject", subject.getJSONObject());

      funder = new Funder();
      funder.setById("https://www.dh.nrw/kooperationen/InDigO");
      tsmObj.append("funder", funder.getJSONObject());

      subject = new Subject();
      subject.setSubjectByName("2. Kartierung");
      tsmObj.append("subject", subject.getJSONObject());

      subject = new Subject();
      subject.setSubjectByName("3. Bodenradar");
      tsmObj.append("subject", subject.getJSONObject());
      
      license = new License();
      license.setById("https://creativecommons.org/publicdomain/zero/1.0/");
      tsmObj.put("license", license.getJSONObject());

      language = new Language();
      language.setBy2LetterTag("de");
      tsmObj.append("language", language.getJSONObject());

      language = new Language();
      language.setById("http://id.loc.gov/vocabulary/iso639-2/fra");
      tsmObj.append("language", language.getJSONObject());

      language = new Language();
      language.setById("http://id.loc.gov/vocabulary/iso639-2/ita");
      tsmObj.append("language", language.getJSONObject());

      language = new Language();
      language.setBy2LetterTag("no");
      tsmObj.append("language", language.getJSONObject());

      //System.out.println(tsmObj.toString(1));
  }
  
  /**
   * Initialize all parts required for the toscience model as json representation
   * 
   */
  private void createTsmStub() {
    
    tsmObj.put("@id", new JSONObject());
    tsmObj.put("accessScheme", new JSONObject());
    tsmObj.put("contentType", "researchData");
    tsmObj.put("title", new Title().getJSONArray());
    tsmObj.put("description", new Description().getJSONArray());
    tsmObj.put("creator", new JSONArray());
    tsmObj.put("contributor", new JSONArray());
    tsmObj.put("department", new JSONArray());
    tsmObj.put("funder", new JSONArray());
    tsmObj.put("itemID", new JSONArray());
    tsmObj.put("language", new JSONArray());
    tsmObj.put("license", new JSONArray());
    tsmObj.put("medium", new JSONArray());
    tsmObj.put("subject", new JSONArray());
    
    

  }
  
  public JSONObject getTSMStub() {
    return tsmObj;
  }

}
