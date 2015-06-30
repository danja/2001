package com.isacat.iou.filter;

import java.util.*;

import com.isacat.iou.util.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    12 February 2001
 */
public class Filter {

  Stopper stopper = new Stopper();

  private final String STOPSET = "H:/isacat_code/iou/src/semcat/stopwords.txt";
  private final static int RESULT_LENGTH = 200;

  public Filter() {
    stopper.setStopSet(FileUtils.loadHashSet(STOPSET));
  }

  public String filter(String source) {
    String filtered = StringUtils.depunct(source);
    filtered = stopper.process(filtered, true, false, 3);
    filtered = StringUtils.trim(filtered, RESULT_LENGTH);
    return filtered;
  }
}

