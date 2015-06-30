/*
 * StringHash.java
 *
 * Created on 04 December 2000, 20:23
 */

package com.isacat.iou.util;

/**
 * @author     Danny Ayers
 * @created    10 February 2001
 * @version
 */
public class StringHash {

  static int TABLESIZE = 10007;

  public static int hash(String s) {
    int value = 0;
    int length = s.length();
    for (int i = 0; i < length; i++) {
      value = 37 * value + s.charAt(i);
    }
    value %= TABLESIZE;
    if (value < 0) {
      value += TABLESIZE;
    }
    return value;
  }
}
