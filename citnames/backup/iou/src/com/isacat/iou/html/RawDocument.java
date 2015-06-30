package com.isacat.iou.html;

import java.util.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    11 February 2001
 */
public class RawDocument extends Document {

  public RawDocument() {
    super();
  }

  public void resolveFields() {
    resolveLinks();
  }
}
