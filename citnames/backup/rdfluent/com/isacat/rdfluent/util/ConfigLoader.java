package com.isacat.rdfluent.util;

import java.util.*;
import java.io.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 *  When an XML file containing a set of Properties is parsed, SAX events call
 *  the methods of this class, and each Properties object is placed into a
 *  HashMap (referenced by name). Uses SAX2 - in this version the Xerces
 *  implementation.
 *
 * @author     Danny Ayers
 * @created    21 November 2000
 */

public class ConfigLoader extends DefaultHandler {

  private Properties properties;
  private String propvalue = null;
  private String listname = null;
  private String propname = null;

  private final static String PARSER_NAME = "org.apache.xerces.parsers.SAXParser";

  private static PrintStream log = System.out;
  private static HashMap prophash = new HashMap();


  /**
   *  Constructor for the XMLProperties object
   */
  public ConfigLoader() {
    super();
  }


  /**
   *  Sets the Log attribute of the XMLProperties object a PrintStream to which
   *  error/log messages can be passed. Default is System.out.
   *
   * @param  ps  The new Log value
   */
  public void setLog(PrintStream ps) {
    log = ps;
  }


  // print(String,String)
  /**
   *  Gets the AllProperties attribute of the XMLProperties object
   *
   * @return    The AllProperties value - a HashMap of the Properties objects
   */
  public HashMap getAllProperties() {
    return prophash;
  }


  /**
   *  Gets an individual Properties object (by name)
   *
   * @param  listname  Description of Parameter
   * @return           The Properties value
   */
  public Properties getProperties(String listname) {
    System.out.println("X" + listname);
    Object ob = prophash.get(listname);
    if (ob == null) {
      return null;
    }
    return (Properties) ob;
  }


  /**
   *  Loads an XML file and parses it
   *
   * @param  uri  Description of Parameter
   */
  public void load(String uri) {
    try {
      XMLReader parser = (XMLReader) Class.forName(PARSER_NAME).newInstance();
      parser.setContentHandler(this);
      parser.setErrorHandler(this);
      parser.setFeature("http://xml.org/sax/features/validation", true);
      log.println(uri);
      parser.parse(uri);
    } catch (Exception e) {
      log.println(e);
    }
  }


  // DocumentHandler methods
  /**
   *  Called when the start of the XML document is encountered
   */
  public void startDocument() {
    log.println("start properties load");
  }


  /**
   *  Called when the end of the XML document is encountered
   */
  public void endDocument() {
    log.println("end properties load");
  }


  /**
   *  Called when an element is first encountered
   *
   * @param  uri    Description of Parameter
   * @param  local  Description of Parameter
   * @param  qname  Description of Parameter
   * @param  attrs  Description of Parameter
   */
  public void startElement(String uri, String local, String qname, Attributes attrs) {
    if (local.equals("propertylist")) {
      listname = attrs.getValue(uri, "name");
      properties = new Properties();
    }
    if (local.equals("property")) {
      propname = attrs.getValue(uri, "name");
    }
  }


  /**
   *  Description of the Method
   *
   * @param  uri    Description of Parameter
   * @param  local  Description of Parameter
   * @param  qname  Description of Parameter
   */
  public void endElement(String uri, String local, String qname) {
    if (local.equals("propertylist")) {
      prophash.put(listname, properties);

      listname = null;
    }
    if (local.equals("property")) {
      properties.setProperty(propname, propvalue);
      //listname + "." +

      propname = null;
    }
  }


  /**
   *  Description of the Method
   *
   * @param  ch      Description of Parameter
   * @param  start   Description of Parameter
   * @param  length  Description of Parameter
   */
  public void characters(char ch[], int start, int length) {
    propvalue = new String(ch, start, length);
    if ((propname != null) && (length < 1)) {
      error(new SAXParseException("missing value " + propname, null));
    }
  }


  // ErrorHandling
  /**
   *  Description of the Method
   *
   * @param  ex  Description of Parameter
   */
  public void warning(SAXParseException ex) {
    log.println("warning : " + ex.getMessage() + detailMessage());
  }


  /**
   *  Description of the Method
   *
   * @param  ex  Description of Parameter
   */
  public void error(SAXParseException ex) {
    log.println("error : " + ex.getMessage() + detailMessage());
    ;

  }


  /**
   *  Description of the Method
   *
   * @param  ex  Description of Parameter
   */
  public void fatalError(SAXParseException ex) {
    log.println("fatal error : " + ex.getMessage() + detailMessage());
  }


  /**
   *  Description of the Method
   *
   * @return    Description of the Returned Value
   */
  public String detailMessage() {
    return "\nProperyList = " + listname
       + "\nPropertyName = " + propname
       + "\nPropertyValue = " + propvalue;
  }
}

