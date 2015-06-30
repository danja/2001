package com.isacat.iou.filter;

import java.util.*;
import java.text.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    12 February 2001
 */
public class Stopper {

  HashSet stopwords = null;

  public Stopper() {
  }

  public void setStopSet(HashSet hs) {
    stopwords = hs;
  }

  public boolean isStopWord(String s) {
    return stopwords.contains(s);
  }

  public String process(String s, boolean dedupe, boolean cutpunctwords, int minlength) {
    String stopstring = null;
    ArrayList array = new ArrayList();
    //   HashMap stopmap = null;
    String lcword = null;
    String word = null;
    // if(hashmap) stopmap = new HashMap();
    stopstring = new String();

    StringTokenizer st = new StringTokenizer(s);
    while (st.hasMoreTokens()) {
      word = st.nextToken().toLowerCase();
      //     System.out.println(word+word.length());
      if (word.length() >= minlength) {
        // check word length
        if (!isStopWord(word)) {
          if (!cutpunctwords) {
            // if we don't care about punctuation
            if (dedupe) {
              //     stopmap = addToMap(stopmap, word);
              if (!array.contains(word)) {
                array.add(word);
              }
            } else {
              stopstring += word + " ";
            }
          } else {
            if (!containsPunct(word)) {
              // if the word doesn't contain punct
              if (dedupe) {
                if (!array.contains(word)) {
                  array.add(word);
                }
              } else {
                stopstring += word + " ";
              }
            }
          }
        }
      }
    }
    if (dedupe) {
      for (int i = 0; i < array.size(); i++) {
        stopstring += (String) array.get(i) + " ";
      }
    }
    return stopstring;
  }

  /*
   * private HashMap addToMap(HashMap hm, String s){
   * Object value = hm.get(s);
   * if(value == null){
   * hm.put(s, new Integer(1));
   * return hm;
   * }
   * int current = ((Integer)value).intValue();
   * hm.put(s, new Integer(++current));
   * return hm;
   * }
   */
  public boolean containsPunct(String s) {
    StringCharacterIterator iter = new StringCharacterIterator(s);
    for (char c = iter.first(); c != StringCharacterIterator.DONE; c = iter.next()) {
      if (!Character.isLetterOrDigit(c)) {
        //    System.out.println(s);
        return true;
      }
    }
    return false;
  }

  /*
   * public void stop(String filename){
   * FileInputStream in;
   * boolean stored = false;
   * int readInt;
   * char c;
   * String word = new String();
   * File inputFile = new File(filename);
   * try{
   * in = new FileInputStream(inputFile);
   * while((readInt = in.read()) != -1){
   * c = (char)readInt;
   * if(isWhite(c)){
   * words.add(word);
   * System.out.println(word);
   * word = "";
   * while(isWhite(c)){
   * readInt = in.read();
   * if(readInt == -1) break;
   * c = (char)readInt;
   * }
   * }
   * if(readInt != -1)word+= c;
   * }
   * in.close();
   * }catch(Exception e){
   * System.out.println(e);
   * }
   * }
   */
}

