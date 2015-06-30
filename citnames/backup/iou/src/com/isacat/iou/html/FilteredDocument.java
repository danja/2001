package com.isacat.iou.html;

import java.util.*;
import com.isacat.iou.filter.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    12 February 2001
 */
public class FilteredDocument extends Document {

  String rawwords = null;
  String corewords = null;
  Filter filter = null;
  String description = new String();
  String title = new String();
  private final String[] requiredWords = {"Java", "semantic", "RDF", "metadata", "XTM", "inference"};

  public FilteredDocument() {
    super();
  }

  public void setFilter(Filter f) {
    filter = f;
  }

  public String getCorewords() {
    return corewords;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public boolean isAcceptable() {
    if (!super.isAcceptable()) {
      return false;
    }
    for (int i = 0; i < requiredWords.length; i++) {
      if (rawwords.indexOf(requiredWords[i]) != -1) {
        return true;
      }
    }
    return false;
  }

  public void resolveFields() {
    resolveLinks();

    title = extractString("title");
    System.out.println("TITLE = " + title);
    description = extractString("description");
    String keywords = extractString("keywords");
    String pagetext = extractString("pagetext");
    String heading = new String();
    ArrayList[] headings = new ArrayList[6];
    Object temp;

    for (int i = 0; i < 6; i++) {
      temp = getField("heading" + i);

      if (temp != null) {
        headings[i] = (ArrayList) temp;
        for (Iterator it = headings[i].iterator(); it.hasNext(); ) {
          heading += (String) it.next() + " ";
        }
      }
    }

    rawwords = title + " " + keywords + " " + description + " " + heading + " " + pagetext;
    corewords = filter.filter(rawwords);
  }

  public String extractString(String name) {
    Object ob = getField(name);
    if (ob == null) {
      return "";
    }
    return (String) ob;
  }
}
