/*
 * TypeUtils.java
 *
 * Created on 12 November 2000, 14:22
 */

package com.isacat.iou.util;

import java.util.*;
import java.text.*;

/**
 * @author     Danny Ayers
 * @created    21 November 2000
 * @version
 */
public class TypeUtils {

  /**
   *  Creates new TypeUtils
   *
   * @param  ob  Description of Parameter
   * @return     Description of the Returned Value
   */
  public static int objectToInt(Object ob) {
    if (ob == null) {
      return -1;
    }
    if (!(ob instanceof java.lang.Integer)) {
      return -1;
    }
    Integer integer = (Integer) ob;
    return integer.intValue();
  }

  public static String arrayListToString(ArrayList al) {
    if (al == null) {
      return null;
    }
    String string = new String();
    for (Iterator it = al.iterator(); it.hasNext(); ) {
      string += (String) it.next() + " ";
    }
    return string;
  }

  public static ArrayList stringToArrayList(String s, String delim) {
    ArrayList al = new ArrayList();
    String word = null;
    StringTokenizer st = new StringTokenizer(s, delim);
    while (st.hasMoreTokens()) {
      word = st.nextToken();
      al.add(word);
    }
    return al;
  }

  /*
   * HashSet is an alternative to ArrayList where order isn't important
   * public static String hashSetToString(HashSet hs){
   * if (hs == null) 	return null;
   * String string = new String();
   * for(Iterator it = hs.iterator();it.hasNext();){
   * string += (String)it.next()+" ";
   * }
   * return string;
   * }
   *
   * public static HashSet stringToHashSet(String s){
   * HashSet hs = new HashSet();
   * String word = null;
   * StringTokenizer st = new StringTokenizer(s);
   * while (st.hasMoreTokens()) {
   * word = st.nextToken();
   * hs.add(word);
   * }
   * return hs;
   * }
   */
}
