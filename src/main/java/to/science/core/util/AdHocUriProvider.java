/**
 * 
 */
package to.science.core.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <p><em>Recently a not functional class, only a dummy for something we have to implement</em><p>  
 * <p>Provide a Fake AdHocUri with no real URI!
 * </p>        
 *         
 * @author aquast
 */
public class AdHocUriProvider {

	private String label = null;
	private String baseUri = "https://hbz-nrw.de/adhocuri/";


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

		adHocUri = generateNewAdHocUri();

		return adHocUri;
	}

	private String generateNewAdHocUri() {
	  String adHocUri = null;
		try {
			adHocUri = baseUri +
					URLEncoder.encode(label, StandardCharsets.UTF_8.toString())
							.replaceAll("\\+", "%20").replaceAll("%21", "!")
							.replaceAll("%27", "'").replaceAll("%28", "(")
							.replaceAll("%29", ")").replaceAll("%7E", "~");
	} catch(Exception e) {
	  
	}
		
    return adHocUri;

	}
}
