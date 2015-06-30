/*
 *
 * Adapter for
 */

package com.isacat.iou.parse;

import java.io.*;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import org.xml.sax.SAXException;
import com.isacat.iou.parse.rdf.*;
/**
 * @author     danny
 * @created    25 March 2001
 * @version
 */

public class RDFDataParser implements DataParser {
  ContentHandler handler = null;
  boolean validating = false;


  public RDFDataParser() {
  }

  public void setHandler(Object ob) {
    handler = (DefaultHandler) ob;
  }

  public void setValidating(boolean v) {
    validating = v;
  }


  public void parse(Reader in) {
    //   try {


    RDFReader rdf = new RDFReader();
    rdf.setRDFHandler((RDFHandler) handler);
    rdf.setContentHandler(handler);
    //   rdf.setErrorHandler(handler);

    //   } catch (Exception e) {
    //    e.printStackTrace();
    //    }

    try {
      rdf.readRDF(in);
      in.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
