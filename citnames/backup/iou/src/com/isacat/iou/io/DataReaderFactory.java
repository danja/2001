/*
 * DataReaderFactory.java
 *
 * Created on 29 March 2001, 16:11
 */

package com.isacat.iou.io;

/**
 * @author     danny
 * @created    29 March 2001
 * @version
 */
public class DataReaderFactory {

  public static DataReader create(String name) {
    try {
      return (DataReader) Class.forName(name).newInstance();
    } catch (Exception e) {
      throw new IllegalArgumentException("Can't create : " + name);
    }
  }

}
