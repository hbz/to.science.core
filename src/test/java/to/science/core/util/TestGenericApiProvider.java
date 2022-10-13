/**
 * 
 */
package to.science.core.util;

import static org.junit.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;

import to.science.net.util.GenericLookupProvider;

/**
 * @author aquast
 *
 */
public class TestGenericApiProvider {

  final static Logger logger = LogManager.getLogger(TestGenericApiProvider.class);

  @Test
  public void checkForResponse() {

    GenericLookupProvider gen = new GenericLookupProvider("https://api.ror.org/organizations/", "03jqp6d56");
    
    String result = null;
    String wanted = "Fachhochschule Oberösterreich";
    JSONObject jsonObj = null;
    JSONObject lookupObj = gen.getResponseAsJSONObject();
    if(lookupObj.has("labels")) {
      JSONArray arr = lookupObj.getJSONArray("labels");
      jsonObj = arr.getJSONObject(0);
      result = jsonObj.get("label").toString();
    }
    assertEquals("Failure - Expected Name " + wanted + "not matched", result, wanted);
  }
}
