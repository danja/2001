/*
 * TestHTMLParsing.java
 *
 * Created on 25 March 2001, 10:28
 */

// package com.isacat.iou.html;

import java.io.*;
import java.net.*;
import javax.swing.text.html.HTMLEditorKit.*;

// import com.isacat.iou.io.*;

/**
 *
 * @author  danny
 * @version 
 */
public class TestHTMLParsing {

  public static void main(String[] args) {
    HTTPReader reader = new HTTPReader();
    GenericParser parser = new HTMLParser();
    reader.setParser(parser);
    ParserCallback parsercallback = new EchoHTMLParserCallback();
    parser.setHandler(parsercallback);
    boolean ok = true;
    try {

      ok = reader.read(new URL("http://localhost"), true);
    } catch (MalformedURLException mue) {

    }
    if (!ok) {
      System.out.println("Warning : "+reader.getWarning());
    }
    System.out.println(reader.getContentAsString());
  }

}
