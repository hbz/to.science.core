/**
 * 
 */
package to.science.core.util;

import org.junit.Test;

/**
 * @author aquast
 *
 */
public class TestGenericFileLoader {
  
  GenericPropertiesLoader GenPropLoad = new GenericPropertiesLoader();
  
  @Test
  public void testGenericPropertiesLoader() {
    GenPropLoad.loadProperties("Test.properties");
    GenPropLoad.loadVocabMap("Test.properties");
  }

}
