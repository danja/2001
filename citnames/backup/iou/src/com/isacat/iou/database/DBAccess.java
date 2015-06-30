package com.isacat.iou.database;

import com.isacat.iou.database.*;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    10 February 2001
 */
public class DBAccess {

  protected PrintStream log = System.out;
  protected int recordCount = -1;
  protected int toDBSize = 1;
  protected int fromDBSize = 1;
  protected DBConnectionPool pool;

  // fill buffer from DB

  public void setLog(PrintStream ps) {
    log = ps;
  }

  public void setConnectionPool(DBConnectionPool p) {
    pool = p;
  }

  public int getRecordCount() {
    return recordCount;
  }

  public synchronized void store(ArrayList al) {
  }

  protected int getRecordCount(String table) {
    // not thread safe

    if (recordCount == -1) {
      Connection conn = pool.getConnection();
      recordCount = DBUtils.countRecords(conn, table);
      pool.returnConnection(conn);
    }
    return recordCount;
  }

  /*
   * add an Object to the buffer
   * abstract Object load();
   * load an Object from the buffer
   * abstract boolean store();
   * store the buffer to DB
   * abstract boolean retrieve();
   */
}
