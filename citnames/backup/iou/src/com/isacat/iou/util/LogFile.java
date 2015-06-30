package com.isacat.iou.util;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 *  LogFile Logging utility create a text file for logging with timestamped
 *  entries usage : 1. create instance LogFile lf = new LogFile(); 2. (optional)
 *  set echoing to System.out : lf.setEcho(true); 3. obtain PrintStream
 *  providing filename : PrintStream log =
 *  lf.getPrintStream("C:\\poollog.txt")); 4. print to log file :
 *  log.println("whatever"); log.print("something else");
 *
 * @author     Danny Ayers
 * @created    10 November 2000
 */
public class LogFile {

  /**
   *  Description of the Field
   */
  public int logLevel = NORMAL;

  FileOutputStream fos = null;
  private boolean echo = false;
  private boolean debug = false;
  /**
   *  Description of the Field
   */
  public final static int QUIET = 0;
  /**
   *  Description of the Field
   */
  public final static int NORMAL = 1;
  /**
   *  Description of the Field
   */
  public final static int VERBOSE = 2;
  public final static int DEBUG = 3;

  /**
   *  Sets the Echo attribute of the LogFile object i.e whether or not the
   *  logged entries are also sent to System.out
   *
   * @param  i  The new LogLevel value
   */

  /**
   *  Sets the Echo attribute of the LogFile object i.e whether or not the
   *  logged entries are also sent to System.out
   *
   *  Sets the Echo attribute of the LogFile object i.e whether or not the
   *  logged entries are also sent to System.out Sets the Echo attribute of the
   *  LogFile object i.e whether or not the logged entries are also sent to
   *  System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out Sets the Echo attribute of the LogFile object i.e whether or
   *  not the logged entries are also sent to System.out Sets the Echo attribute
   *  of the LogFile object i.e whether or not the logged entries are also sent
   *  to System.out
   *
   * @param  i  The new LogLevel value
   */
  public void setLogLevel(int i) {
    logLevel = i;
    if ((logLevel == VERBOSE) || (logLevel == DEBUG)) {
      echo = true;
    }
    if (logLevel == DEBUG) {
      debug = true;
    }
  }


  /**
   *  Creates the PrintStream
   *
   * @param  filename  Name of log file, e.g. "C:\log.txt"
   * @param  append    Description of Parameter
   * @return           The PrintStream value
   */

  public LogPrinter getPrintStream(String filename, boolean append) {

    File file = new File(filename);
    try {
      //	if (!file.exists() || !append) {
      fos = new FileOutputStream(file);
      // make new
      //	}
      //	else {
      fos = new FileOutputStream(filename, append);
      // append to existing
      //	}
    } catch (Exception e) {
      System.out.println(e);
    }
    LogPrinter lp = new LogPrinter(fos, echo, debug);

    return lp;
  }


  /**
   * @return
   */

  public int getLogLevel() {
    return logLevel;
  }


  /**
   *  Closes the log file should always be called before closing application
   */
  public void close() {
    try {
      fos.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}

