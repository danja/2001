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
public class LogPrinter extends PrintStream {

  FileOutputStream out;
  Date date;
  // formatting objects for date/time
  DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
  DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);
  boolean echo = false;
  boolean debug = false;

  /**
   *  Constructor for the LogPrinter object
   *
   * @param  fos  Description of Parameter
   */

  //        LogPrinter(){
  //       super(System.out);
  //     }

  LogPrinter(OutputStream fos) {

    super(fos);
    out = (FileOutputStream) fos;
  }

  LogPrinter(OutputStream fos, boolean ec, boolean de) {
    this(fos);
    debug = de;
    echo = ec;
  }


  /**
   *  Logs String and moves to new line
   *
   * @param  s  String to be logged
   */
  public void println(String s) {
    print(s, true);
  }


  /**
   *  Logs String and stays on same line
   *
   * @param  s  String to be logged
   */
  public void print(String s) {
    print(s, false);
  }

  public void print(String s, boolean newline) {

    if (echo) {
      System.out.print(s);
      if (newline) {
        System.out.println("");
      }
    }

    date = new Date(System.currentTimeMillis());
    // writes the formatted date/time and the log string to file

    try {
      if (newline) {
        out.write(("\n" + df.format(date) + " " + tf.format(date) + " " + s + "\n").getBytes());
      } else {
        out.write(s.getBytes());
      }
    } catch (Exception e) {
      System.out.println(e);
    }

  }

  public void debug(String s) {
    if (debug) {
      print(s);
    }
  }

  public void debugln(String s) {
    if (debug) {
      println(s);
    }
  }
}
