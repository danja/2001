/*
 * DBCache.java
 *
 * Created on 16 December 2000, 20:35
 */

package com.isacat.iou.buffers;

import com.isacat.iou.database.*;

import java.sql.*;
import java.io.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    10 February 2001
 */
public class DBBuffer {
  // was abstract

  protected PrintStream log = System.out;
  protected DBAccess dbaccess = null;
  protected int toDBSize = 1;
  protected int fromDBSize = 1;
  protected DBConnectionPool pool;

  // fill buffer from DB

  public void setLog(PrintStream ps) {
    log = ps;
    dbaccess.setLog(log);
  }

  public void setConnectionPool(DBConnectionPool p) {
    pool = p;
    dbaccess.setConnectionPool(p);
  }

  public void setDBAccess(DBAccess dba) {
    dbaccess = dba;
  }

  public void setToDBSize(int i) {
    toDBSize = i;
  }

  public void setFromDBSize(int i) {
    fromDBSize = i;
  }

  public int getToDBSize() {
    return toDBSize;
  }

  public int getFromDBSize() {
    return fromDBSize;
  }

//  abstract void save(Object ob);

  // add an Object to the buffer

//  abstract Object load();

  // load an Object from the buffer

//  abstract boolean store();

  // store the buffer to DB

// abstract boolean retrieve();

}
