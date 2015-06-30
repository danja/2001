/*
 * HTMLParser.java
 *
 * Created on 25 March 2001, 09:37
 */

package com.isacat.iou.parse;

import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.xml.sax.*;
import javax.xml.parsers.*;

/**
 * @author     danny
 * @created    25 March 2001
 * @version
 */

public class XMLDataParser implements DataParser {
  ContentHandler handler = null;
  boolean validating = false;

  private final static String PARSER_NAME = "org.apache.xerces.parsers.SAXParser";

  /**
   *  Creates new HTMLParser
   */
  public XMLDataParser() {
  }

  public void setHandler(Object ob) {
    handler = (DefaultHandler) ob;
  }

  public void setValidating(boolean v) {
    validating = v;
  }


  public void parse(Reader in) {
    try {

      SAXParserFactory saxfactory = SAXParserFactory.newInstance();
      SAXParser saxparser = saxfactory.newSAXParser();
      XMLReader xmlreader = saxparser.getXMLReader();
      //  XMLReader xmlreader = (XMLReader)saxparser.getParser();

      xmlreader.setContentHandler(handler);
      xmlreader.setErrorHandler((ErrorHandler) handler);
      xmlreader.setFeature("http://xml.org/sax/features/validation", validating);

      xmlreader.parse(new InputSource(in));

      /*
       * XMLReader parser = (XMLReader) Class.forName(PARSER_NAME).newInstance();
       * parser.setContentHandler(handler);
       * parser.setErrorHandler((ErrorHandler) handler);
       * parser.setFeature("http://xml.org/sax/features/validation", validating);
       * parser.parse(new InputSource(in));
       */
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
