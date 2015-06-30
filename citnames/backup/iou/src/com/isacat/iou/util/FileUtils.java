package com.isacat.iou.util;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    10 February 2001
 */
public class FileUtils {

//    HashSet words = new HashSet();
  public static String loadString(String filename) {
    String string = new String();
    FileInputStream in;
    int readInt;
    char c;
    File inputFile = new File(filename);
    try {
      in = new FileInputStream(inputFile);
      while ((readInt = in.read()) != -1) {
        c = (char) readInt;
        string += c;
      }
      in.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return string;
  }

  public static HashSet loadHashSet(String filename) {
    HashSet wordset = new HashSet();
    FileInputStream in;
    boolean stored = false;
    int readInt;
    char c;
    String word = new String();
    File inputFile = new File(filename);
    try {
      in = new FileInputStream(inputFile);
      while ((readInt = in.read()) != -1) {
        c = (char) readInt;
        if (isWhite(c)) {
          wordset.add(word);
          // System.out.println(word);
          word = "";
          while (isWhite(c)) {
            readInt = in.read();
            if (readInt == -1) {
              break;
            }
            c = (char) readInt;
          }
        }
        if (readInt != -1) {
          word += c;
        }
      }
      in.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    return wordset;
  }

  public static void saveHashSet(String filename, HashSet wordset) {

    File outputFile = new File(filename);

    TreeSet sorted = new TreeSet(wordset);
    int c;
    int length;
    String word;
    byte[] bytes;
    try {
      FileOutputStream out = new FileOutputStream(outputFile);
      for (Iterator it = sorted.iterator(); it.hasNext(); ) {
        word = (String) it.next();
        bytes = word.getBytes();
        length = word.length();
        for (int i = 0; i < length; i++) {
          out.write(bytes[i]);
        }

        out.write('\n');
      }
      out.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void saveString(String filename, String string) {

    File outputFile = new File(filename);
    int c;
    int length;
    byte[] bytes;
    try {
      FileOutputStream out = new FileOutputStream(outputFile);
      bytes = string.getBytes();
      length = string.length();
      for (int i = 0; i < length; i++) {
        out.write(bytes[i]);
      }

      out.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private static boolean isWhite(char c) {
    return (c == ' ') || (c == '\n') || (c == '\r');
  }

}
