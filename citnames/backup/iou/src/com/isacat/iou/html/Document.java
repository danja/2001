/*
 * Document.java
 *
 * Created on November 30, 2000, 7:26 PM
 */

package com.isacat.iou.html;

import java.beans.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import java.net.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

import com.isacat.iou.filter.*;
import com.isacat.iou.parse.*;
import com.isacat.iou.io.*;
/**
 * @author     root
 * @created    09 February 2001
 * @version
 */
public class Document extends HashMap {
  // -1;
  /**
   *  Description of the Field
   */
  public boolean loaded = false;
  /**
   *  Description of the Field
   */
  public boolean html = false;

  private int id = -1;

  private HyperLink hyperlink = null;
  private URL url = null;
  private long pagedate = -1;
  private long expiration = -1;
  private long scandate = -1;
  private int httpResponseCode = 0;
  private String pagetype = null;
  private String httpResponseMessage = null;
  private String content = null;
  private int length = 0;
  private ArrayList links = null;
  private ArrayList linkslist = null;

  private PropertyChangeSupport change;

  /////
  private DataReader reader = null;
  private DataParser parser = null;
  private boolean isHttp = false;
  private String filename;
  private static int MAXLENGTH = 50000;

  private static PrintStream log = System.out;

  public Document() {
//    change = new PropertyChangeSupport(this);
  }


  public void setLog(PrintStream ps) {
    log = ps;
  }


  public void setID(int i) {
    id = i;
  }


  public void setAddress(String s) {
    hyperlink = new HyperLink();
    hyperlink.setAbsolute(s);
  }

  public void setLink(HyperLink h) {
    hyperlink = h;
    url = hyperlink.getURL();
  }

  public void setType(String s) {
    pagetype = s;
  }


  public void setField(String name, Object field) {
    put(name, field);
  }

  public void setFilter(Filter f) {
  }

  public void setHttpResponseCode(int httpResponseCode) {
    this.httpResponseCode = httpResponseCode;
  }

  public int getID() {
    return id;
  }

  public String getAddress() {
    return hyperlink.getURL().toString();
  }

  public HyperLink getLink() {
    return hyperlink;
  }

  public String getType() {
    return pagetype;
  }

  public Object getField(String name) {
    return get(name);
  }


  public java.sql.Date getPageDate() {
    return new java.sql.Date(pagedate);
  }

  public java.sql.Date getExpiryDate() {
    return new java.sql.Date(expiration);
  }


  public java.sql.Date getScanDate() {
    return new java.sql.Date(scandate);
  }

  public int getHttpResponseCode() {
    return httpResponseCode;
  }

  public String getHttpResponseMessage() {
    return httpResponseMessage;
  }

  public String getContent() {
    return content;
  }

  public boolean isAcceptable() {
    return getType().startsWith("text") || getType().startsWith("xml");
  }

  public ArrayList getLinks() {
    return linkslist;
  }

  public boolean read(boolean contentRequired) {
    // load
    scandate = System.currentTimeMillis();
    clear();

    //currenturl = // new URL(urlstring);
    // urlstring = currenturl.toString();
    // removes /../ from URL
    System.out.println(hyperlink);
    System.out.println(hyperlink.getURL());
    if (hyperlink.getURL().getProtocol().equals("http")) {

      reader = (DataReader) DataReaderFactory.create("com.isacat.iou.io.HTTPDataReader");

      parser = (DataParser) DataParserFactory.create("com.isacat.iou.parse.HTMLDataParser");

      reader.setParser(parser);
      System.out.print("readHTML : ");
      HTMLParserCallback parsercallback = new HTMLParserCallback();
      parser.setHandler(parsercallback);
      read(true, true);
      setType(((HTTPDataReader) reader).getType());
      setHttpResponseCode(((HTTPDataReader) reader).getHttpResponseCode());
      System.out.println("TYPE : " + getType());

      putAll(parsercallback.getFields());
    }
    return true;
  }

  public boolean read(boolean readRaw, boolean parse) {
    boolean ok = false;
    System.out.println("\nreading " + hyperlink.getURL());
    try {
      ok = ((HTTPDataReader) reader).read(hyperlink.getURL(), readRaw, parse);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return ok;
  }


  public void resolveFields() {
  }

  public void resolveLinks() {
    linkslist = new ArrayList();

    Object linksArray = getField("links");

    String linkpath = getLink().getAbsolutePath();
    HyperLink inlink;
    // a link found in this doc
    linkslist.clear();
    if (linksArray != null) {
      links = (ArrayList) linksArray;
      for (int i = 0; i < links.size(); i++) {
        inlink = (HyperLink) links.get(i);
        if (inlink.getAbsolutePath() == null) {
          // the href must have been relative
          inlink.setAbsolutePath(linkpath);
        }
        // resolve the address with that of the containing doc

        if ((inlink != null)) {
          // && (linktext != null)
          linkslist.add(i, inlink);
        }
      }
    }
  }
}
