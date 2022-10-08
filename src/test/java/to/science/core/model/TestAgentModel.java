package to.science.core.model;

import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.Test;

import to.science.core.model.implementation.Contributor;
import to.science.core.model.implementation.Creator;


/**
 * 
 */

/**
 * @author aquast
 *
 */
public class TestAgentModel {

  @Test
  public void createContributor() {
    Contributor contributor = new Contributor();
    contributor.setAffiliationById("https://ror.org/00n3mcd10");
    contributor.setId("https://orcid.org/0000-0002-7662-8211");
    contributor.setAcademicDegree("https://d-nb.info/standards/elementset/gnd#academicDegree/Prof.");
    contributor.setPrefLabel("Andres Quast");
    assertNotNull("contributor not null: ", contributor.getJson());
    
  }

  @Test
  public void createAnotherContributor() {
    Contributor contributor = new Contributor();
    contributor.setAffiliationById("https://ror.org/00wz4b049");
    contributor.setId("https://orcid.org/0000-0002-7662-8211");
    contributor.setAcademicDegree("https://d-nb.info/standards/elementset/gnd#academicDegree/Prof.");
    contributor.setPrefLabel("Andres Quast");
    assertNotNull("contributor not null: ", contributor.getJson());
  }

  @Test
  public void mapCreatorToAmb() {
    Creator creator = new Creator();
    creator.setAffiliationById("https://ror.org/00n3mcd10");
    creator.setId("https://orcid.org/0000-0002-7662-8211");
    creator.setAcademicDegree("https://d-nb.info/standards/elementset/gnd#academicDegree/Prof.");
    creator.setPrefLabel("Andres Quast");
    JSONObject amb = creator.getAmbJSONObject();
    JSONObject toScience = creator.getFromAmbJSONObject(amb);
    assertNotNull("toscience not null: ", toScience);
  }
  
}
