/*
 * DBAccess.java
 *
 * Created on 04 December 2000, 17:57
 */

package com.isacat.iou.buffers;

import java.util.*;
import java.sql.*;
import java.io.*;

import com.isacat.iou.database.*;
import com.isacat.iou.util.*;
import com.isacat.iou.html.*;
/**
 * @author     Danny Ayers
 * @created    10 February 2001
 * @version
 */
public class DocumentBuffer extends DBBuffer {

// RawDBAccess rawdb = new RawDBAccess();
  int rawCount = 0;
//  int rawEntries = -1;
  int toDBSize = 10;
  private PrintStream log = System.out;

  private ArrayList docs = new ArrayList();
  static boolean locked;


  public DocumentBuffer() {
  }


  public int getRecordCount() {

    return dbaccess.getRecordCount();
  }

  public void save(Document doc) {

    docs.add(doc);
    if (++rawCount == toDBSize) {
      flush();
    }
  }

  public void flush() {

    dbaccess.store(docs);
    rawCount = 0;

  }
}
