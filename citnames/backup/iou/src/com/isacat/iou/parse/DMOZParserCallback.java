package com.isacat.iou.parse;

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

public class DMOZParserCallback extends DefaultHandler {

  // DocumentHandler methods
  /**
   *  Called when the start of the XML document is encountered
   */
  public void startDocument() {
    System.out.println("start document");
  }


  /**
   *  Called when the end of the XML document is encountered
   */
  public void endDocument() {
    System.out.println("end document");
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
    System.out.println("startElement : " + local + " " + attrs);
  }


  /**
   *  Description of the Method
   *
   * @param  uri    Description of Parameter
   * @param  local  Description of Parameter
   * @param  qname  Description of Parameter
   */
  public void endElement(String uri, String local, String qname) {
    System.out.println("endElement : " + local);
  }


  /**
   *  Description of the Method
   *
   * @param  ch      Description of Parameter
   * @param  start   Description of Parameter
   * @param  length  Description of Parameter
   */
  public void characters(char ch[], int start, int length) {
    System.out.println("characters : " + new String(ch, start, length));
  }


  // ErrorHandling
  /**
   *  Description of the Method
   *
   * @param  ex  Description of Parameter
   */
  public void warning(SAXParseException ex) {
    System.out.println("warning : " + ex.getMessage());
  }


  /**
   *  Description of the Method
   *
   * @param  ex  Description of Parameter
   */
  public void error(SAXParseException ex) {
    System.out.println("error : " + ex.getMessage());
    ;

  }


  /**
   *  Description of the Method
   *
   * @param  ex  Description of Parameter
   */
  public void fatalError(SAXParseException ex) {
    System.out.println("fatal error : " + ex.getMessage());
  }
}

