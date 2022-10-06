/**
 * 
 */
package to.science.core.model.model;

import java.util.Hashtable;

import to.science.core.modelx.amb.AmbMapper;


/**
 * An abstract Class representing the complete TosScienceModel 
 * @author aquast
 *
 */
public abstract class AbstractToScienceModel implements ToScienceModel, AmbMapper {

  public static Hashtable<String, ToScienceModel> registeredModels = new Hashtable<>();
 

  
 }
