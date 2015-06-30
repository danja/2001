/*
 * PropertiesMinder.java
 *
 * Created on 19 May 2001, 20:36
 */

package old;

import java.util.*;
import java.io.*;
/**
 * @author     danny
 * @created    19 May 2001
 * @version
 */
public class ConfigLoader {

  /**
   *  Creates new PropertiesMinder
   */
  public ConfigLoader() {
  }

  public static Properties load(String filename) {
    Properties properties = new Properties();
    try {
      FileInputStream in = new FileInputStream(filename);
      properties.load(in);
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return properties;
  }

  public static void save(Properties properties, String filename, String comment) {

    try {
      FileOutputStream out = new FileOutputStream(filename);
      properties.store(out, comment);
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
