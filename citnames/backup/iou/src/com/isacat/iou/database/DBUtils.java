/*
 * DBUtils.java
 *
 * Created on 06 December 2000, 11:06
 */

package com.isacat.iou.database;

import java.util.*;
import java.sql.*;
import java.io.*;
/**
 * @author     Danny Ayers
 * @created    10 February 2001
 * @version
 */
public class DBUtils {

  private static PrintStream log = System.out;

  public static int countRecords(Connection connection, String table) {
    int itemCount = -1;
    log.println("COUNT");
    //   SELECT count(*) FROM table AS count
    //	ArrayList items = new ArrayList();

    Statement stmt;
    String countString = "SELECT count(*) FROM " + table;
    try {
      stmt = connection.prepareStatement(countString);
      ResultSet rs = stmt.executeQuery(countString);
      rs.first();
      // or next?
      itemCount = rs.getInt(1);
      log.println("COUNT = " + itemCount);
      rs.close();
      stmt.close();
    } catch (Exception e) {
      log.println(e);
      e.printStackTrace(log);
    }
    return itemCount;
  }

  public static void showResultSet(ResultSet rs) {
    showResultSet(rs, System.out);
  }

  public static void showResultSet(ResultSet rs, PrintStream ps) {
    int i;
    try {
      rs.beforeFirst();
      ResultSetMetaData rsmd = rs.getMetaData();
      int numCols = rsmd.getColumnCount();
      ps.println("\n*** ResultSet ***\n");
      for (i = 1; i <= numCols; i++) {
        if (i > 1) {
          ps.print(",");
        }
        ps.print(rsmd.getColumnLabel(i));
      }
      ps.print("\n-------------\n");
      boolean more = rs.next();
      while (more) {
        for (i = 1; i <= numCols; i++) {
          if (i > 1) {
            ps.print(",");
          }
          String string = rs.getString(i);
          if (string != null) {
            ps.print(string);
          }
        }
        ps.println("\n");
        more = rs.next();
      }
      rs.beforeFirst();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
