package com.isacat.iou.parse.test;

import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

/**
 *  Parses HTML Pages
 *
 * @author     Danny Ayers
 * @created    29 October 2000
 */
public class EchoHTMLParserCallback extends ParserCallback {

  public void handleError(String error, int pos) {
    System.out.println("Error : " + error + "\nPosition : " + pos);
  }

  public void handleComment(char[] data, int pos) {
    System.out.println("Comment : " + new String(data));
  }


  public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attribs, int pos) {
    System.out.println("SimpleTag : " + attribs);
  }

  public void handleStartTag(HTML.Tag tag, MutableAttributeSet attribs, int pos) {
    System.out.println("StartTag : " + attribs);
  }


  public void handleEndTag(HTML.Tag tag, int pos) {
    System.out.println("EndTag");
  }

  public void handleText(char[] text, int pos) {
    System.out.println("Text : " + new String(text));
  }
}
