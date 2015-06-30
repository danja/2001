/*
 * DBCPTest.java
 *
 * Created on 08 January 2001, 11:25
 */

package com.isacat.iou.database;

import java.util.*;
import java.sql.*;
import javax.sql.*;

/**
 * @author     Danny Ayers
 * @created    26 March 2001
 * @version
 */
public class DBCPTest {

  DBConnectionPool pool = null;

  /**
   *  Creates new DBCPTest
   */
  public DBCPTest() {
    pool = new DBConnectionPool(getPoolProperties());
    query();

  }

  public void query() {
    java.sql.Connection con = null;
    //    pool = new DBConnectionPool(getPoolProperties());
    con = pool.getConnection();
    System.out.println(pool.getStatus());

    try {
      Statement stmt = con.createStatement();

      java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM sysusers");
      printResultSet(rs);
      con.close();
      con = null;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // end run


  // print the Resultset to the PrintStream (by default the console)
  void printResultSet(ResultSet rs) throws SQLException {
    int i;

    ResultSetMetaData rsmd = rs.getMetaData();

    int count = rsmd.getColumnCount();
    StringBuffer buffer = new StringBuffer();
    for (i = 1; i <= count; i++) {
      if (i > 1) {
        buffer.append("\t\t");
      }
      buffer.append(rsmd.getColumnName(i));
    }
    System.out.println(buffer.toString());

    while (rs.next()) {
      buffer = new StringBuffer();
      for (i = 1; i <= count; i++) {
        if (i > 1) {
          buffer.append("\t\t");
        }

        String obj = rs.getString(i);
        buffer.append(String.valueOf(obj));
      }
      System.out.println(buffer.toString());
    }
  }

  public static Properties getPoolProperties() {
    Properties properties = new Properties();

    properties.setProperty("pool.database", "jdbc:odbc:iou");
    properties.setProperty("pool.driver", "sun.jdbc.odbc.JdbcOdbcDriver");
    properties.setProperty("pool.catalog", "");
    properties.setProperty("pool.user", "wrox");
    properties.setProperty("pool.password", "xorw");
    properties.setProperty("pool.size", "10");
    properties.setProperty("pool.timeout", "10");
    return properties;
  }

  /**
   * @param  args  the command line arguments
   */
  public static void main(String args[]) {
    new DBCPTest();

  }
}
