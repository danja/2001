// RDFReader.java - read RDF statements from a document.
// Written by David Megginson, david@megginson.com
// NO WARRANTY!  This class is in the public domain.

// $Id: RDFReader.java,v 1.1 2000/03/13 16:44:13 david Exp $

/*
 * TODO:
 * - include option to return XML content as a string
 */

package com.isacat.iou.parse.rdf;

import java.io.IOException;
import java.io.Reader;

import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.xml.sax.Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import org.xml.sax.helpers.ParserAdapter;
import org.xml.sax.helpers.ParserFactory;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.xml.sax.helpers.*;
/**
 *  Simplified front-end point for RDF processing. <blockquote><em>This module,
 *  both source code and documentation, is in the Public Domain, and comes with
 *  <strong>NO WARRANTY</strong> .</em></blockquote> <p>
 *
 *  This class takes care of setting up a SAX2 filter chain for RDF processing,
 *  and it provides convenience methods for setting and querying the current RDF
 *  event handler. Typical usage is something like this:</p> <pre>
 *
 *  RDFReader r = new RDFReader(new SomeSAX2Driver()); RDFHandler h = new
 *  MyRDFHandler(); r.setRDFHandler(h); r.readRDF("http://www.foo.com/something.rdf");
 *  </pre><p>
 *
 *  "SomeSAX2Driver" stands for any SAX2 driver (you'll need a specific one),
 *  and "MyRDFHandler" stands for a class you've created implementing the four
 *  RDFHandler callbacks, which the RDFReader will use to report RDF events.
 *  </p><p>
 *
 *  If you have the org.xml.sax.driver property set to a SAX2 driver, or if you
 *  have the SAX1 driver for AElfred already on your classpath, you can simplify
 *  the above example to</p> <pre>
 *
 *  RDFReader r = new RDFReader(); RDFHandler h = new MyRDFHandler();
 *  r.setRDFHandler(h); r.readRDF("http://www.foo.com/something.rdf");</pre> <p>
 *
 *  By default, this front-end dumps warnings to standard error output and
 *  throws exceptions for errors and fatal errors, but you can override that
 *  behaviour by supplying your own error handler.</p> <p>
 *
 *  If you wish to retrieve the literal XML content of RDF properties with
 *  parseType="Literal", or if you wish to access non-RDF XML outside of rdf:RDF
 *  blocks, you must also provide a standard SAX2 ContentHandler.</p>
 *
 * @author     David Megginson, david@megginson.com
 * @created    26 March 2001
 * @version    1.0alpha
 * @since      1.0alpha
 * @see        org.xml.sax.ContentHandler
 * @see        org.xml.sax.ErrorHandler
 * @see        com.megginson.sax.rdf.RDFHandler
 * @see        com.megginson.sax.rdf.RDFFilter
 */
public class RDFReader implements ErrorHandler {

  ////////////////////////////////////////////////////////////////////
  // Internal state.
  ////////////////////////////////////////////////////////////////////

  StringBuffer xmlBuffer;
  XMLReader rdfReader;
  RDFHandler rdfHandler;
  ContentHandler contentHandler;
  ErrorHandler errorHandler;


  ////////////////////////////////////////////////////////////////////
  // Constructors.
  ////////////////////////////////////////////////////////////////////


  /**
   *  Create a new RDF reader. <p>
   *
   *  The reader will choose an embedded SAX parser based on the value of the
   *  "org.xml.sax.driver" property. If that is not set, it will look at the
   *  "org.xml.sax.parser" property for a SAX1 driver to adapt, and if that is
   *  not set, it will attempt to use the SAX1 AElfred driver, or throw a
   *  runtime exception if all else fails.</p>
   */
  public RDFReader() {
    String driverClass = System.getProperty("org.xml.sax.driver");

    if (driverClass == null) {
      //    String parserClass =
      //     System.getProperty("org.xml.sax.parser",
      //     "org.apache.xerces.parsers.SAXParser");
      try {
        SAXParserFactory saxfactory = SAXParserFactory.newInstance();
        SAXParser saxparser = saxfactory.newSAXParser();
        XMLReader xmlreader = saxparser.getXMLReader();
        //   XMLReader xmlreader = (XMLReader) saxparser.getParser();

        init(xmlreader);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }


  /**
   *  Create a new RDF reader with the specified SAX2 driver.
   *
   * @param  r  Description of Parameter
   */
  public RDFReader(XMLReader r) {
    init(r);
  }


  /**
   *  Create a new RDF reader with the specified SAX1 driver.
   *
   * @param  p  Description of Parameter
   */
  public RDFReader(Parser p) {
    init(new ParserAdapter(p));
  }


  ////////////////////////////////////////////////////////////////////
  // Accessors and setters.
  ////////////////////////////////////////////////////////////////////


  /**
   *  Register a new handler for RDF events.
   *
   * @param  handler  The new RDF handler.
   */
  public void setRDFHandler(RDFHandler handler) {
    rdfHandler = handler;
  }


  /**
   *  Register a new handler for non-RDF XML events.
   *
   * @param  handler  The new content handler.
   */
  public void setContentHandler(ContentHandler handler) {
    contentHandler = handler;
  }


  /**
   *  Register a new handler for error events. <p>
   *
   *  If no handler is registered, warnings will result in messages to standard
   *  error and errors and fatal errors will result in exceptions.</p>
   *
   * @param  handler  The new error handler.
   */
  public void setErrorHandler(ErrorHandler handler) {
    errorHandler = handler;
  }


  /**
   *  Query the current handler for RDF events.
   *
   * @return    The current RDF event handler, or null.
   */
  public RDFHandler getRDFHandler() {
    return rdfHandler;
  }


  /**
   *  Query the current handler for non-RDF XML events.
   *
   * @return    The current content handler or null.
   */
  public ContentHandler getContentHandler() {
    return contentHandler;
  }


  /**
   *  Query the current handler for error events.
   *
   * @return    The current error handler or null.
   */
  public ErrorHandler getErrorHandler() {
    return errorHandler;
  }


  ////////////////////////////////////////////////////////////////////
  // Actions.
  ////////////////////////////////////////////////////////////////////


  /**
   *  Read an RDF document from a URI.
   *
   * @param  systemId          Description of Parameter
   * @exception  IOException   Description of Exception
   * @exception  SAXException  Description of Exception
   */
  public void readRDF(String systemId)
     throws IOException, SAXException {
    setupParse();
    rdfReader.parse(systemId);
  }


  /**
   *  Read an RDF document from a character stream.
   *
   * @param  reader            Description of Parameter
   * @exception  IOException   Description of Exception
   * @exception  SAXException  Description of Exception
   */
  public void readRDF(Reader reader)
     throws IOException, SAXException {
    setupParse();
    rdfReader.parse(new InputSource(reader));
  }


  ////////////////////////////////////////////////////////////////////
  // Implementation of org.xml.sax.ErrorHandler.
  ////////////////////////////////////////////////////////////////////


  /**
   *  Default warning handler. <p>
   *
   *  This method will be invoked only when the client has not set an error
   *  handler.</p>
   *
   * @param  e                 The warning information as an exception.
   * @exception  SAXException  Description of Exception
   */
  public void warning(SAXParseException e)
     throws SAXException {
    if (errorHandler != null) {
      errorHandler.warning(e);
    } else {
      System.err.println("*** WARNING: " + e.getMessage());
    }
  }


  /**
   *  Default error handler. <p>
   *
   *  This method will be invoked only when the client has not set an error
   *  handler.</p>
   *
   * @param  e                 The error information as an exception.
   * @exception  SAXException  Description of Exception
   */
  public void error(SAXParseException e)
     throws SAXException {
    if (errorHandler != null) {
      errorHandler.error(e);
    } else {
      throw e;
    }
  }


  /**
   *  Default fatal error handler. <p>
   *
   *  This method will be invoked only when the client has not set an error
   *  handler.</p>
   *
   * @param  e                 The error information as an exception.
   * @exception  SAXException  Description of Exception
   */
  public void fatalError(SAXParseException e)
     throws SAXException {
    if (errorHandler != null) {
      errorHandler.error(e);
    } else {
      throw e;
    }
  }


  /**
   *  Initialize an RDF reading session.
   *
   * @exception  SAXException  Description of Exception
   */
  private void setupParse()
     throws SAXException {
    if (rdfHandler != null) {
      rdfReader.setProperty(RDFHandler.propertyName, rdfHandler);
    }
    if (contentHandler != null) {
      rdfReader.setContentHandler(contentHandler);
    }
    rdfReader.setErrorHandler(this);
  }


  /**
   *  Internal initialization.
   *
   * @param  r  Description of Parameter
   */
  private void init(XMLReader r) {
    rdfReader = r;
    try {
      rdfReader.setFeature(RDFHandler.featureName, true);
    } catch (SAXException e) {
      rdfReader = new RDFFilter(r);
    }
    contentHandler = null;
    errorHandler = null;
    rdfHandler = null;
  }
}

// end of RDFReader.java
