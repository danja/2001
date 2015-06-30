/*
 * StringUtils.java
 *
 * Created on 01 December 2000, 11:00
 */

package com.isacat.iou.util;

import java.text.*;
/**
 * @author     Danny Ayers
 * @created    10 February 2001
 * @version
 */
public class StringUtils {

  /**
   *  Creates new StringUtils
   */
  public StringUtils() {
  }

  public static String trim(String string, int cut) {
    // System.out.println("trim : "+string);
    if (string == null) {
      return "";
    }
    if ("".equals(string)) {
      return "";
    }
    if (string.length() < cut) {
      return string;
    }

    if (string.substring(cut, cut).equals(" ")) {
      return string.substring(0, cut);
    }
    if (string.length() < cut - 1) {
      if (string.substring(cut + 1, cut + 1).equals(" ")) {
        return string.substring(0, cut);
      }
    }
    string = string.substring(0, cut);
    int lastSpace = string.lastIndexOf(" ");
    if (lastSpace == -1) {
      return string;
    }
    return string.substring(0, lastSpace);
  }


  public static String depunct(String string) {
    String depunct = new String();
    boolean period = false;
    StringCharacterIterator iter = new StringCharacterIterator(string);
    char c = iter.first();
    depunct += Character.toLowerCase(c);
    while (c != StringCharacterIterator.DONE) {
      c = iter.next();
      if (Character.isLetterOrDigit(c)) {
        depunct += c;
        //  System.out.print(c);
      } else {
        depunct += ' ';
        period = (c == '.');
        while ((!Character.isLetterOrDigit(c = iter.next())) && (c != StringCharacterIterator.DONE)) {
          ;
        }
        if (c != StringCharacterIterator.DONE) {
          if (period) {
            depunct += Character.toLowerCase(c);
          } else {
            depunct += c;
          }
        }
      }
    }
    return depunct;
  }
}
