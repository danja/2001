package com.isacat.iou.parse;

import javax.swing.text.html.HTMLEditorKit.*;
import javax.swing.text.html.HTMLDocument.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import java.util.*;
import java.text.*;

import com.isacat.iou.html.*;
/**
 *  Parses HTML Pages
 *
 * @author     Danny Ayers
 * @created    29 October 2000
 */
public class HTMLParserCallback extends ParserCallback {

  boolean debug = false;
  String errors = new String();
  String title = new String();
  String description = new String();
  String keywords = new String();
  String pagetext = new String();

  ArrayList links = new ArrayList();

  ArrayList[] headings = new ArrayList[6];
  int hlevel = -1;

  String elementString = null;
  HyperLink hyperlink = null;

  HashMap fields = new HashMap();
  int state = 0;
  final static int NONE = 0;
  final static int GENERALTAG = 1;
  final static int TITLE = 2;
  final static int HREF = 3;
  final static int HEADING = 4;

  final static int SCRIPT = 5;
  final static int OBJECT = 6;
  final static String ABS_MARKER = ":////";

  /**
   *  Constructor - initializes string variables
   */
  public HTMLParserCallback() {
    for (int i = 0; i < 6; i++) {
      headings[i] = new ArrayList();
    }
  }

  public HashMap getFields() {
    // System.out.println("LINKSSSSSSSSS"+links);
    fields.put("links", links);
    fields.put("pagetext", pagetext);
    for (int i = 0; i < 6; i++) {
      fields.put("heading" + i, headings[i]);
    }
    return fields;
  }

  public String getErrors() {
    return errors;
  }

  public void handleError(String error, int pos) {
    errors += "Position " + pos;
    errors += error;
  }

  public void handleComment(char[] data, int pos) {
  }


  public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attribs, int pos) {
    if (debug) {
      System.out.println("simple tag = " + tag);
    }
    if (tag.equals(HTML.Tag.META)) {
      handleMeta(attribs);

    }
  }

  public void handleStartTag(HTML.Tag tag, MutableAttributeSet attribs, int pos) {
    if (debug) {
      System.out.println("start tag = " + tag);
    }

    if (tag.equals(HTML.Tag.TITLE)) {
      state = TITLE;
      return;
    }

    if (tag.equals(HTML.Tag.A)) {

      handleAnchor(attribs);
      return;
    }
    if (tag.equals(HTML.Tag.H1)) {
      state = HEADING;
      hlevel = 0;
      return;
    }
    if (tag.equals(HTML.Tag.H2)) {
      state = HEADING;
      hlevel = 1;
      return;
    }
    if (tag.equals(HTML.Tag.H3)) {
      state = HEADING;
      hlevel = 2;
      return;
    }
    if (tag.equals(HTML.Tag.H3)) {
      state = HEADING;
      hlevel = 3;
      return;
    }
    if (tag.equals(HTML.Tag.H4)) {
      state = HEADING;
      hlevel = 4;
      return;
    }
    if (tag.equals(HTML.Tag.H5)) {
      state = HEADING;
      hlevel = 5;
      return;
    }
    state = NONE;
  }


  public void handleEndTag(HTML.Tag tag, int pos) {
    if (debug) {
      System.out.println("end tag = " + tag);
    }
    //  state = NONE;/////////////
    if (tag.equals(HTML.Tag.BODY)) {
      return;
    }

    switch (state) {

      case NONE:
        return;
      case TITLE:
        if (tag.equals(HTML.Tag.TITLE)) {
        }
        fields.put("title", elementString);
        state = NONE;
        return;
      case HREF:
        if (tag.equals(HTML.Tag.A)) {
          hyperlink.appendLinkText(elementString);

          links.add(hyperlink);
          //      System.out.println("links in htmlparsercallback = "+links);
        }
        state = NONE;
        return;
      case HEADING:
        headings[hlevel].add(elementString);
        state = NONE;
        return;
    }
  }


  /**
   *  Handle page text
   *
   * @param  text  Description of Parameter
   * @param  pos   Description of Parameter
   */
  public void handleText(char[] text, int pos) {
    if (debug) {
      System.out.println("handle text : " + new String(text));
    }
    // if(text.length == 0) return;
    elementString = new String(text);
    if (elementString == null) {
      elementString = "";
    }
    if (state == NONE) {
      pagetext += elementString + " ";
    }
  }


  /**
   *  Handle META tags
   *
   * @param  attribs  Description of Parameter
   */
  public void handleMeta(MutableAttributeSet attribs) {
    String name = new String();
    String content = new String();
    name = (String) attribs.getAttribute(HTML.Attribute.NAME);
    content = (String) attribs.getAttribute(HTML.Attribute.CONTENT);
    if (name == null || content == null) {
      return;
    }
    name = name.toUpperCase();
    if (name.equals("DESCRIPTION")) {
      fields.put("description", content);

      return;
    }
    if (name.equals("KEYWORDS")) {
      fields.put("keywords", content);
      return;
    }
  }


  /**
   *  Handle Anchor <A HREF="~">tags
   *
   * @param  attribs  Description of Parameter
   */
  public void handleAnchor(MutableAttributeSet attribs) {
    String href = new String();
    href = (String) attribs.getAttribute(HTML.Attribute.HREF);
// System.out.println("HREF = "+href);
    if (href == null) {
      return;
    }

    hyperlink = new HyperLink();

    if (href.regionMatches(3, ABS_MARKER, 0, 3) || href.regionMatches(4, ABS_MARKER, 0, 3)) {

      hyperlink.setAbsolute(href);
    } else {
      hyperlink.setRelative(href);
    }
    state = HREF;
  }
}
