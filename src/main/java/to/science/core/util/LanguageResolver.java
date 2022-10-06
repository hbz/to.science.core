/**
 * 
 */
package to.science.core.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

 /**
 * <p> 
 * This Extractor converts a three letter ISO639-2 uri into two letter
 * ISO639-1 tag on the base of java.util.Locale.
 * </p>
 * <p>
 * For Instance, the given Uri http://id.loc.gov/vocabulary/iso639-2/eng will
 * be converted in en
 * </p>
 * <p>
 *
 * @author aquast
 * </p>
 */
public class LanguageResolver {

  private static Locale locale = null;
  private Locale impLocale = null;
  
  public Locale getLocale() {
    return impLocale;
  }
  
  public Locale getLocalefrom2LetterTag(String tag2) {
    impLocale = Locale.forLanguageTag(tag2);
    return impLocale;
  }
  
  public static String get2LetterTag(String iso639_2Uri){
    String tag2 = iso639_1TagExtractor(iso639_2Uri);
    return tag2;
  };
  
  public static String get3LetterTag(String iso639_1Uri){
    String tag3 = iso639_2TagExtractor(iso639_1Uri);
    return tag3;
  };
  
  private static String iso639_1TagExtractor(String iso639_2Uri) {
    String result = "unknown";
    Locale loc = Locale.forLanguageTag(
        iso639_2Uri.replace("http://id.loc.gov/vocabulary/iso639-2/", "")
            .replace("ger", "deu"));
    Map<String, Locale> localeMap = new HashMap<String, Locale>();
    String[] iso639_1Tags = Locale.getISOLanguages();
    for (int i = 0; i < iso639_1Tags.length; i++) {
      Locale locale = new Locale(iso639_1Tags[i]);
      localeMap.put(locale.getISO3Language(), locale);
    }
    if (localeMap.get(loc.getISO3Language()) != null) {
      result = localeMap.get(loc.getISO3Language()).getLanguage();
    }
    return result;
  }

  private static String iso639_2TagExtractor(String iso639_1Uri) {
    String result = "unknown";
    Locale loc = Locale.forLanguageTag(
        iso639_1Uri.replace("http://id.loc.gov/vocabulary/iso639-1/", ""));
    Map<String, Locale> localeMap = new HashMap<String, Locale>();
    String[] iso639_1Tags = Locale.getISOLanguages();
    for (int i = 0; i < iso639_1Tags.length; i++) {
      Locale locale = new Locale(iso639_1Tags[i]);
      localeMap.put(locale.getISO3Language(), locale);
    }
    if (localeMap.get(loc.getISO3Language()) != null) {
      result = localeMap.get(loc.getISO3Language()).getISO3Language();
    }
    return result;
  }

}
