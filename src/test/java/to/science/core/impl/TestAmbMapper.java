/**
 * 
 */
package to.science.core.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONObject;

import to.science.core.model.implementation.Affiliation;
import to.science.core.model.implementation.AmbMapperImpl;
import to.science.core.model.implementation.Contributor;
import to.science.core.model.implementation.Creator;
import to.science.core.model.implementation.Department;
import to.science.core.model.implementation.Description;
import to.science.core.model.implementation.Funder;
import to.science.core.model.implementation.Language;
import to.science.core.model.implementation.License;
import to.science.core.model.implementation.Medium;
import to.science.core.model.implementation.Subject;
import to.science.core.model.implementation.Title;
import to.science.core.model.implementation.YearOfCopyright;
import to.science.core.model.model.AbstractToScienceModel;
import to.science.core.model.model.ToScienceModel;

/**
 * @author aquast
 *
 */
public class TestAmbMapper {

  /**
   * @param args
   */
  public static void main(String[] args) {

    // get new instance of this class
    TestAmbMapper amb = new TestAmbMapper();

    // create an Hashtable with all parts of to.science.model represented as JSONObject 
    Hashtable<String, AbstractToScienceModel> tosClasses = new Hashtable<>();
    tosClasses.put("affiliation", new Affiliation());
    tosClasses.put("creator", new Creator());
    tosClasses.put("contributor", new Contributor());
    tosClasses.put("department", new Department());
    tosClasses.put("language", new Language());
    tosClasses.put("license", new License());
    tosClasses.put("medium", new Medium());
    tosClasses.put("subject", new Subject());
    tosClasses.put("description", new Description());
    tosClasses.put("title", new Title());
    tosClasses.put("yearOfCopyright", new YearOfCopyright());
    tosClasses.put("funder", new Funder());
    tosClasses.put("license", new License());

    Hashtable<String, String> simpleArrayMap = new Hashtable<>();
    simpleArrayMap.put("department", "learningResourceType");
    simpleArrayMap.put("medium", "about");
    simpleArrayMap.put("creator", "creator");
    simpleArrayMap.put("contributor", "contributor");
    simpleArrayMap.put("description", "description");
    simpleArrayMap.put("title", "name");
    simpleArrayMap.put("yearOfCopyright", "datePublished");
    simpleArrayMap.put("funder", "funder");
    simpleArrayMap.put("license", "license");
    simpleArrayMap.put("subject", "keywords");

    AmbMapperImpl ambMapper = new AmbMapperImpl();
    JSONObject ambJSONObj = ambMapper.getAmbJSONObject(); // the mapping amb source
    JSONObject tosJSONObj = new JSONObject(); // the mapping toscience target
 
  
    
    Enumeration<String> simpleArrayEnum = simpleArrayMap.keys();

    try {
    while (simpleArrayEnum.hasMoreElements()) {
      String key = simpleArrayEnum.nextElement();
      if (ambJSONObj.has(simpleArrayMap.get(key))) {
        JSONArray ambArr = ambJSONObj.optJSONArray(simpleArrayMap.get(key));
        if(ambArr != null && !simpleArrayMap.get(key).equals("keywords")) {
          JSONArray arr = new JSONArray();
          for(int i=0; i < ambArr.length(); i++) {
            JSONObject obj = tosClasses.get(key).getFromAmbJSONObject(ambArr.optJSONObject(i));
            // System.out.println(obj.toString(1));
            arr.put(obj);
          }
          tosJSONObj.put(key, arr);          
        }
       
        else if(simpleArrayMap.get(key).equals("keywords")) {
          System.out.println(simpleArrayMap.get(key));
          JSONObject obj = tosClasses.get(key).getFromAmbJSONObject(ambJSONObj);
          JSONArray arr = obj.getJSONArray(key);
          tosJSONObj.put(key,  arr);
          //System.out.println(obj.toString(1));
        }
        
        else if(ambJSONObj.optJSONObject(simpleArrayMap.get(key)) != null) {
          JSONObject obj = tosClasses.get(key).getFromAmbJSONObject(ambJSONObj.getJSONObject(simpleArrayMap.get(key)));
          tosJSONObj.put(key, obj);
          //System.out.println(obj.toString(1));
        }
        
        else if (ambJSONObj.optString(simpleArrayMap.get(key)) != null){
          JSONObject obj = tosClasses.get(key).getFromAmbJSONObject(ambJSONObj);
          JSONArray arr = obj.getJSONArray(key);
          tosJSONObj.put(key, arr);          
        }
 
        // System.out.println(key);
          //JSONObject obj = tosClasses.get(key).getFromAmbJSONObject(ambJSONObj);
          //tosJSONObj.putOpt(key, obj);
          // tosJSONObj.put(key, new String[] {ambJSONObj.optString(simpleArrayMap.get(key))});
     
        
        // arr.put(new );
      }
      }
    } catch (Exception e) {
      System.out.println("ERROR: " + e.toString());
    }


    if (ambJSONObj.has("id")) {
      tosJSONObj.put("@id", ambJSONObj.get("id"));
      // TODO implement the other mappings that require resource id
    }

    tosJSONObj.put("contentType", "researchData");

    System.out.println(tosJSONObj.toString(1));

  }

}
