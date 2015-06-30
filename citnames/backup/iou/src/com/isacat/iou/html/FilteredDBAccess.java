/*
 * DBAccess.java
 *
 * Created on 04 December 2000, 17:57
 */

package com.isacat.iou.html;

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
public class FilteredDBAccess extends DBAccess {

  int rawCount = 0;
  int recordCount = -1;

  private PrintStream log = System.out;

// private ArrayList docs = new ArrayList();

  //  private static final int toDBSize = 1; //50?

  private final static String FILTERED_TABLE = "core";

  /**
   *  Creates new DBAccess
   */
  public FilteredDBAccess() {
    // docs = new Document[toDBSize];
  }


  public int getRecordCount() {

    if (recordCount == -1) {
      Connection conn = pool.getConnection();
      recordCount = DBUtils.countRecords(conn, FILTERED_TABLE);
      pool.returnConnection(conn);
    }
    return recordCount;
  }

  public void store(ArrayList docs) {
    /////////
    // System.out.println("commit raw");
    Connection conn = pool.getConnection();

    //     System.out.println("conn = "+conn);
    PreparedStatement storeraw = null;
    try {
      storeraw = conn.prepareStatement("INSERT INTO " + FILTERED_TABLE + " (ID, COREWORDS, TITLE, DESCRIPTION) VALUES (?,?,?,?)");
    } catch (Exception e) {
      log.println("error preparing filter store statement : " + e);
    }
    for (int i = 0; i < docs.size(); i++) {
      recordCount++;
      FilteredDocument filteredDoc = (FilteredDocument) docs.get(i);
      HyperLink filteredDoclink = filteredDoc.getLink();
      try {
        storeraw.setInt(1, filteredDoc.getID());
        storeraw.setString(2, filteredDoc.getCorewords());
        storeraw.setString(3, filteredDoc.getTitle());
        System.out.println("tit " + filteredDoc.getTitle());
        storeraw.setString(4, filteredDoc.getDescription());
      } catch (Exception e) {
        log.println("error setting filter store statement : " + e);
      }
      try {
        storeraw.executeUpdate();
        filteredDoc.setID(recordCount);
      } catch (Exception e) {
        log.println("error executing filter store statement : " + e);
      }
    }
    docs.clear();
    pool.returnConnection(conn);
  }
}
