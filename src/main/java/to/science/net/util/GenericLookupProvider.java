/**
 * 
 */
package to.science.net.util;

import java.io.BufferedReader;
import java.io.StringReader;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author aquast
 * A generic class for running lookups from different JSON-based APIs. 
 *
 */
public class GenericLookupProvider {

  final static Logger logger = LogManager.getLogger(GenericLookupProvider.class);

  private String lookupUri = null;
  private String id = null;
   

/**
 * Constructor for applying uri of LookupService and the id 
 * @param lookupUri
 * @param id
 */
public GenericLookupProvider(String lookupUri, String id) {
  setLookupUri(lookupUri);
  setId(id);
}

  /**
   * @return an entire JSONOBject as response of a lookup-API request. 
   * It's up to the using classes to fetch the desired information from this object. 
   */
  public JSONObject getResponseAsJSONObject() {

    JSONObject lookupJSONObj = null;
    
    try {
      WebTarget target = ClientBuilder.newClient().target( this.lookupUri );
      WebTarget resourceTarget = target.path( this.id );        
      Invocation.Builder request = resourceTarget.request(MediaType.APPLICATION_JSON);
      Response response = request.get();
      StringReader inputReader = new StringReader(response.readEntity( String.class ));
      
      StringBuilder jsonStringBuilder = new StringBuilder();
      BufferedReader bReader = new BufferedReader(inputReader);

      String inputStr = null;
      while ((inputStr = bReader.readLine()) != null) {
        jsonStringBuilder.append(inputStr);
      }
      lookupJSONObj = new JSONObject(jsonStringBuilder.toString());        
      
    } catch (Exception e) {
      logger.warn("Found no JSON-applicable result");
      //e.printStackTrace();
    }
    //logger.debug(lookupJSONObj.toString(1));
   return lookupJSONObj;
  }
  
  public void setId(String id) {
    this.id = id;
  }

  public void setLookupUri(String lookupUri) {
    this.lookupUri = lookupUri;
  }
  
}

