/*
 * TestHTMLParsing.java
 *
 * Created on 25 March 2001, 10:28
 */

package com.isacat.iou.parse.test;

import java.io.*;
import java.net.*;
import javax.swing.text.html.HTMLEditorKit.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.isacat.iou.io.*;
import com.isacat.iou.parse.*;
import com.isacat.iou.html.*;
import com.isacat.iou.parse.rdf.*;

/**
 * @author     danny
 * @created    26 March 2001
 * @version
 */
public class TestParsing {

  static DataReader reader = null;
  static DataParser parser = null;
  static boolean isHttp = false;
  static String filename;


  public static String getSource(String filename) {
    if (filename.startsWith("http:")) {
      return "HTTP";
    }
    return "File";
  }

  public static String getFormat(String filename) {
    if (filename.endsWith("xml")) {
      return "XML";
    }
    if (filename.endsWith("htm") || filename.endsWith("html")) {
      return "HTML";
    }
    if (filename.endsWith("rdf")) {
      return "RDF";
    }
    if (filename.endsWith("gz")) {
      return "DMOZ";
    }
    return "RAW";
  }

  public static void main(String[] args) {

    filename = args[0];
    String type = getFormat(filename);
    String source = getSource(filename);
    if (source.equals("HTTP")) {
      isHttp = true;
    }

    reader = (DataReader) DataReaderFactory.create("com.isacat.iou.io." + source + "DataReader");
    if (!type.equals("RAW")) {
      parser = (DataParser) DataParserFactory.create("com.isacat.iou.parse." + type + "DataParser");
    }

    reader.setParser(parser);
    boolean ok = false;
    if (type.equals("HTML")) {
      ok = readHTML();
    }
    if (type.equals("XML")) {
      ok = readXML();
    }
    if (type.equals("RDF")) {
      ok = readRDF();
    }
    if (type.equals("DMOZ")) {
      ok = readDMOZ();
    }
    if (type.equals("RAW")) {
      ok = readRaw();
      System.out.println("Content = " + reader.getContentAsString());
    }
    if (!ok) {
      System.out.println("Warning : " + reader.getWarning());
    }
  }

  public static boolean readRaw() {
    System.out.print("readRaw : ");
    return read(true, false);
  }

  public static boolean readHTML() {
    System.out.print("readHTML : ");
    ParserCallback parsercallback = new EchoHTMLParserCallback();
    parser.setHandler(parsercallback);
    return read(false, true);
  }

  public static boolean readXML() {
    EchoXMLParserCallback parsercallback = new EchoXMLParserCallback();
    parser.setHandler((DefaultHandler) parsercallback);
    return read(true, true);
  }

  public static boolean readRDF() {
    RDFParserCallback parsercallback = new RDFParserCallback();

    parser.setHandler((DefaultHandler) parsercallback);
    return read(true, true);
  }

  public static boolean read(boolean readRaw, boolean parse) {
    boolean ok = false;
    if (isHttp) {
      try {
        ok = ((HTTPDataReader) reader).read(new URL(filename), readRaw, parse);
      } catch (MalformedURLException mue) {
        mue.printStackTrace();
      }
    } else {
      System.out.println("file");
      ok = ((FileDataReader) reader).read(new File(filename), readRaw, parse);
    }
    return ok;
  }

  public static boolean readDMOZ() {

    return true;
  }
}
