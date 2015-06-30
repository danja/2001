/*
 * HTMLParser.java
 *
 * Created on 25 March 2001, 09:37
 */

package com.isacat.iou.parse;

import java.io.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.HTMLEditorKit.*;
/**
 * @author     danny
 * @created    25 March 2001
 * @version
 */

public class HTMLDataParser implements DataParser {

  ParserCallback parsercallback = null;

  /**
   *  Creates new HTMLParser
   */
  public HTMLDataParser() {
  }

  public void setHandler(Object ob) {
    parsercallback = (ParserCallback) ob;
  }

  public void parse(Reader in) {

    ParserDelegator pd = new ParserDelegator();
    try {
      pd.parse(in, parsercallback, true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
