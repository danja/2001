/*
 * Parser.java
 *
 * Created on 25 March 2001, 09:40
 */

// package com.isacat.iou.html;

import java.io.*;

/**
 * @author     danny
 * @created    25 March 2001
 * @version
 */
public interface GenericParser {

  public void parse(Reader in);

  public void setHandler(Object ob);
}

