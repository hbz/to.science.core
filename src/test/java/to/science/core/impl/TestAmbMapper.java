/**
 * 
 */
package to.science.core.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;

import to.science.core.modelx.mapper.AmbMapperImpl;

/**
 * @author aquast
 *
 */
public class TestAmbMapper {

  final static Logger logger = LogManager.getLogger(TestAmbMapper.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
	  String ambContent = "{\"funder\":{\"name\":\"Moodle.nrw\",\"type\":\"FundingScheme\",\"url\":\"https://www.dh.nrw/kooperationen/moodle.nrw-70\"},\"creator\":[{\"honoricPrefix\":\"Prof.\",\"affiliation\":{\"name\":\"Hochschule Bochum\",\"id\":\"https://ror.org/04x02q560\",\"type\":\"Organization\"},\"name\":\"Autor1\",\"id\":\"https://api.hoerkaen.hbz-nrw.de/adhoc/uri/QXV0b3Ix\",\"type\":\"Person\"},{\"honoricPrefix\":\"Prof.\",\"affiliation\":{\"name\":\"FH Münster\",\"id\":\"https://ror.org/00pv45a02\",\"type\":\"Organization\"},\"name\":\"Autor2\",\"id\":\"https://api.hoerkaen.hbz-nrw.de/adhoc/uri/QXV0b3Iy\",\"type\":\"Person\"},{\"honoricPrefix\":\"Dr.\",\"affiliation\":{\"name\":\"FH Aachen\",\"id\":\"https://ror.org/04tqgg260\",\"type\":\"Organization\"},\"name\":\"Autor3\",\"id\":\"https://api.hoerkaen.hbz-nrw.de/adhoc/uri/QXV0b3Iz\",\"type\":\"Person\"},{\"honoricPrefix\":\"Dr.\",\"affiliation\":{\"name\":\"Universität zu Köln\",\"id\":\"https://ror.org/00rcxh774\",\"type\":\"Organization\"},\"name\":\"Autor4\",\"id\":\"https://api.hoerkaen.hbz-nrw.de/adhoc/uri/QXV0b3I0\",\"type\":\"Person\"},{\"affiliation\":{\"name\":\"Hochschule Düsseldorf\",\"id\":\"https://ror.org/00ftx0026\",\"type\":\"Organization\"},\"name\":\"Autor5\",\"id\":\"https://api.hoerkaen.hbz-nrw.de/adhoc/uri/QXV0b3I1\",\"type\":\"Person\"}],\"about\":[{\"inScheme\":{\"id\":\"https://w3id.org/kim/hochschulfaechersystematik/scheme\"},\"prefLabel\":{\"de\":\"Byzantinistik\",\"uk\":\"Комп'ютерна лінгвістика\",\"en\":\"Byzantine Studies\"},\"id\":\"https://w3id.org/kim/hochschulfaechersystematik/n031\",\"type\":\"Concept\"}],\"inLanguage\":[\"fr\"],\"type\":[\"LearningResource\"],\"mainEntityOfPage\":[{\"dateCreated\":\"2023-01-27\",\"id\":\"https://api.hoerkaen.hbz-nrw.de/resource/orca:65f8b38a-e797-49b6-b07a-33bd8473def1\"}],\"@context\":[\"https://w3id.org/kim/lrmi-profile/draft/context.jsonld\",{\"@language\":\"de\"}],\"datePublished\":\"2023-01-27\",\"license\":{\"id\":\"https://creativecommons.org/licenses/by/4.0/\"},\"dateCreated\":\"2023-01-27\",\"name\":\"UploadFormulaTestMapping4Metadata2\",\"learningResourceType\":[{\"inScheme\":{\"id\":\"https://w3id.org/kim/hcrt/scheme\"},\"prefLabel\":{\"de\":\"Diagramm\",\"uk\":\"Діаграма\",\"en\":\"Diagram\"},\"id\":\"https://w3id.org/kim/hcrt/diagram\",\"type\":\"Concept\"}],\"id\":\"https://api.hoerkaen.hbz-nrw.de/resource/orca:65f8b38a-e797-49b6-b07a-33bd8473def1\"}";

    // get new instance of this class
    logger.info("call for new TestAMBMapper");
    TestAmbMapper amb = new TestAmbMapper();

    LinkedHashMap<String,String> isDescribedByMap = new LinkedHashMap<>();
    isDescribedByMap.put("submittedBy", "Andres Quast");
    isDescribedByMap.put("submitterByEmail", "quast@hbz-nrw.de");
    
    AmbMapperImpl ambMapper = new AmbMapperImpl();
    ambMapper.setIsDescribedByMap(isDescribedByMap);
    
    JSONObject tosJSONObj = ambMapper.getTosJSONObject(amb.loadExampleAmbSource());
  //  JSONObject tosJSONObj = ambMapper.getTosJSONObject(new JSONObject(ambContent));
    System.out.println(tosJSONObj.toString(1));

  }

  public JSONObject loadExampleAmbSource() {

    JSONObject ambJSONObj = new JSONObject();

    try {
      InputStream ambJsonStream = this.getClass().getClassLoader().getResourceAsStream("exampleAmb.json");
      InputStreamReader ambStreamReader = new InputStreamReader(ambJsonStream, "UTF-8");
      BufferedReader bReader = new BufferedReader(ambStreamReader);
      StringBuilder jsonStringBuilder = new StringBuilder();

      String inputStr;
      while ((inputStr = bReader.readLine()) != null) {
        jsonStringBuilder.append(inputStr);
      }
      ambJSONObj = new JSONObject(jsonStringBuilder.toString());

    } catch (Exception e) {
      e.printStackTrace();
      // logger.error(e.getMessage());
    }

    return ambJSONObj;
  }

}
