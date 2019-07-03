package org.sunbird.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is used to read properties from properties file.
 *
 * @author amit kumar
 */
public class PropertiesCache {

  private final String[] fileName = {"externalResource.properties"};
  private final Properties configProp = new Properties();
  private static PropertiesCache propertiesCache = null;

  /** private default constructor */
  private PropertiesCache() {
    for (String file : fileName) {
      InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
      try {
        configProp.load(in);
        ProjectLogger.log(
            String.format(
                "%s:%s propertiesCache loaded", this.getClass().getSimpleName(), "PropertiesCache"),
            LoggerEnum.INFO.name());
      } catch (IOException e) {
        ProjectLogger.log(
            String.format(
                "%s:%s error in loading propertiescache %s ",
                this.getClass().getSimpleName(), "PropertiesCache", e),
            LoggerEnum.ERROR.name());
      }
    }
  }

  public static PropertiesCache getInstance() {

    // change the lazy holder implementation to simple singleton implementation ...
    if (null == propertiesCache) {
      synchronized (PropertiesCache.class) {
        if (null == propertiesCache) {
          propertiesCache = new PropertiesCache();
        }
      }
    }

    return propertiesCache;
  }

  public void saveConfigProperty(String key, String value) {
    configProp.setProperty(key, value);
  }

  /**
   * Method to read value from environment variable or either resources file .
   *
   * @param key
   * @return string
   */
  public String readProperty(String key) {
    String value = System.getenv(key);
    return value != null && !value.isEmpty() ? value : configProp.getProperty(key);
  }
}
