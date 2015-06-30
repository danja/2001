/*
 * DataReader.java
 *
 * Created on 29 March 2001, 14:25
 */

package com.isacat.iou.io;

import java.io.*;
// import java.net.*;
import com.isacat.iou.parse.*;

/**
 * @author     danny
 * @created    29 March 2001
 * @version
 */
public abstract class DataReader implements DataStreamReader {
//abstract
  private int maxLength = 0;
  private int contentLength = 0;
  private DataParser parser = null;
  private long date = -1;
  private String warning = "Unknown Error";
  private char[] contentChars = null;
  private String contentString = new String();

  public final static int DEFAULT_MAXLENGTH = 65530;
  public final static String[] DEFAULT_MIMETYPES = {"text/plain", "text/html", "text/xml"};
  protected static PrintStream log = System.out;


  /**
   *  Creates new DataReader
   */
  public DataReader() {
    maxLength = DEFAULT_MAXLENGTH;
  }


  public void setMaxLength(int maxLength) {
    this.maxLength = maxLength;
  }

  public void setWarning(String warning) {
    this.warning = warning;
  }

  public void setDate(long date) {
    this.date = date;
  }

  public void setContentChars(char[] contentChars) {
    this.contentChars = contentChars;
  }


  public void setParser(DataParser p) {
    parser = p;
  }

  public int getMaxLength() {
    return maxLength;
  }

  public int getContentLength() {
    return contentLength;
  }

  public String getWarning() {
    return warning;
  }

  public long getDate() {
    return date;
  }


  public char[] getContentAsChars() {
    return contentChars;
  }

  public String getContentAsString() {
    //  if (contentChars == null) {
    //   return null;
    //  }
    //  return new String(content);
    return contentString;
  }

  public boolean read(InputStream stream) {
    return read(stream, true, false);
  }

  public boolean read(InputStream is, boolean readRaw, boolean parse) {
    return read(is, readRaw, parse, false);
  }

  public boolean read(InputStream is, boolean readRaw, boolean parse, boolean unzip) {
    // FileReader fileReader = new FileReader(file);
    warning = "Unknown Error";

    //  try {
    readContent(is, readRaw, parse);
    return true;
  }

  protected void setContentLength(int contentLength) {
    this.contentLength = contentLength;
  }


  protected boolean readContent(InputStream is, boolean readRaw, boolean parse) {
    boolean ok = true;
    InputStreamReader isr = null;
    BufferedReader in = null;
    Reader parseReader = null;
    StringBuffer contentBuffer = new StringBuffer();
    int readInt;
    try {
      isr = new InputStreamReader(is);
      in = new BufferedReader(isr);
      //  in.mark(length);
      if (readRaw) {

        while ((readInt = in.read()) != -1) {
          contentBuffer.append((char) readInt);
        }
        contentString = contentBuffer.toString();
// contentBuffer.toString().getChars();
        contentLength = contentString.length();
        contentChars = new char[contentLength];
        contentString.getChars(0, contentLength, contentChars, 0);
        parseReader = new CharArrayReader(contentChars);
      } else {
        parseReader = in;
      }
      // in.reset();
      if (parse && (parser != null)) {

        parser.parse(parseReader);
      }
      parseReader.close();
      in.close();
      isr.close();
    } catch (IOException ioe) {
      warning = ioe.getMessage();
      log.println(ioe);
      ok = false;
    } finally {
      in = null;
      isr = null;
      return ok;
    }
  }
}
