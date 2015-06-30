/*
 * ParserFactory.java
 *
 * Created on 29 March 2001, 16:42
 */

package com.isacat.iou.parse;

/**
 * @author     danny
 * @created    29 March 2001
 * @version
 */
public class DataParserFactory {

  public static DataParser create(String name) {
    try {
      return (DataParser) Class.forName(name).newInstance();
    } catch (Exception e) {
      throw new IllegalArgumentException("Can't create : " + name);
    }
  }

}
