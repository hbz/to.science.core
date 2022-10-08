/**
 * 
 */
package to.science.core.util;

import static org.junit.Assert.assertNotNull;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;

/**
 * @author aquast
 *
 */
public class TestGenericFileLoader {
  
  GenericPropertiesLoader GenPropLoad = new GenericPropertiesLoader();
  
  @Test
  public void testGenericPropertiesLoader() {
    Properties prop = GenPropLoad.loadProperties("Test.properties");
    assertNotNull(prop);
    Map<String, String> map  = GenPropLoad.loadVocabMap("Test.properties");
    assertNotNull(map);
    
  }

}
