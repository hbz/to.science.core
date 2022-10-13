/**
 * 
 */
package to.science.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;

/**
 * @author aquast Class is to facilitate and standardize loading Vocabularies
 *         form local files - If remote loading is not adviced
 */
public class GenericPropertiesLoader {

  final static Logger logger = LogManager.getLogger(GenericPropertiesLoader.class);
  
  
	public Map<String, String> loadVocabMap(String PropertiesFileName) {
		Map<String, String> vocabMap = new LinkedHashMap<>();
		String propertiesFileName = PropertiesFileName;
		try {
			vocabMap = loadMapFromFile(propertiesFileName);
			return vocabMap;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	 public Properties loadProperties(String PropertiesFileName) {
	    Properties properties = new Properties();
	    String propertiesFileName = PropertiesFileName;
	    try {
	      properties = loadPropertiesFromFile(propertiesFileName);
	      return properties;
	    } catch (Exception e) {
	      logger.error(e.getMessage());
	    }
	    return null;
	  }

	private Properties loadPropertiesFromFile(String propertiesFileName) {
		Properties prop = new Properties();
		try {
		  InputStream propStream = this.getClass().getClassLoader().getResourceAsStream(propertiesFileName);
		  prop.load(propStream);
			return prop;
		} catch (IOException e) {
      logger.error(e.getMessage());
		}
		return null;
	};

	private LinkedHashMap<String, String> loadMapFromFile(String propertiesFileName) {
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		try {
		  InputStreamReader propStreamReader = new InputStreamReader(new GenericPropertiesLoader().getClass().getClassLoader().getResourceAsStream(propertiesFileName));
		 	BufferedReader br = new BufferedReader(propStreamReader);
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] keyValue = line.split("=");
				map.put(keyValue[0], keyValue[1]);
			}
			br.close();
			return map;
		} catch (IOException e) {
      logger.error(new File(propertiesFileName).getAbsolutePath());
			e.printStackTrace();
		}
		return null;
	};
}
