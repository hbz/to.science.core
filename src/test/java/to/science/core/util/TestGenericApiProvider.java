/**
 * 
 */
package to.science.core.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import to.science.core.model.model.AbstractSimpleArray;
import to.science.net.util.GenericLookupProvider;

/**
 * @author aquast
 *
 */
public class TestGenericApiProvider {

  final static Logger logger = LoggerFactory.getLogger(TestGenericApiProvider.class);

  @Test
  public void checkForResponse() {

    GenericLookupProvider gen = new GenericLookupProvider("https://api.ror.org/organizations/", "03jqp6d56");
    JSONObject lookupObj = gen.getResponseAsJSONObject();
    //System.out.println(lookupObj.toString(1));
    if(lookupObj.has("labels")) {
      JSONArray arr = lookupObj.getJSONArray("labels");
      JSONObject jsonObj = arr.getJSONObject(0);
      logger.debug(jsonObj.get("label").toString());
    }
  }
}
