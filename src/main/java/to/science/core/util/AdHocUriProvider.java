/**
 * 
 */
package to.science.core.util;

import java.util.Base64;

/**
 * <p><em>Recently a not functional class, only a dummy for something we have to implement</em><p>  
 * <p>Provide a Fake AdHocUri with no real URI!
 * </p>        
 *         
 * @author aquast
 */
public class AdHocUriProvider {

	private String label = null;
	private String baseUri = "https://api.toscience.hbz-nrw.de";
	private String adhocPath = "adhoc/uri/";
	private String encodedLabel = null;

public AdHocUriProvider() {
  
}
	
/**
 * Initialize a new AdHocProvider
 * @param baseUri
 */
public AdHocUriProvider(String baseUri) {
  this.baseUri = baseUri;
}
	
	/**
	 * Replace default base uri with a server specific uri
	 * @param baseUri the uri as String
	 */
	public void setBaseUri(String baseUri) {
	  this.baseUri = baseUri;
	}
	/**
	 * @param Value the value for which we like to create an AdHocUri
	 * @return a AdHocUri representing a newly created to.science.label
	 */
	public String getAdhocUri(String Value) {

		String adHocUri = null;
		this.label = Value;

		adHocUri = generateAdHocUri();

		return adHocUri;
	}

	private String generateAdHocUri() {
	  encodedLabel = base64encode(label);
	  String adHocUri = null;
		adHocUri = baseUri + "/" + adhocPath + encodedLabel;
		
    return adHocUri;

	}
	
	public String getLabel() {
	  return base64decode();
	}
	
	/**
	 * Encode label by base64
	 * @param label
	 * @return
	 */
	private String base64encode(String label) {
	   return Base64.getEncoder().encodeToString(label.getBytes())
	        .replaceAll("/", "-").replaceAll("\\+", "_");
	  }

  /**
   * Encode label by base64
   * @param label
   * @return
   */
  private String base64decode() {
    String decodedLabel =  Base64.getDecoder().decode(encodedLabel.getBytes()).toString();
    return decodedLabel.replaceAll("-", "/").replaceAll("_", "\\+");
    }


	
	
}
