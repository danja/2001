/*
 * CreateDatabase.java
 *
 * Created on 25 March 2001, 18:31
 */

package com.isacat.iou.database.model;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.isacat.iou.database.*;
import com.isacat.iou.util.*;
/**
 * @author     danny
 * @created    26 March 2001
 * @version
 */
public class DBDatabase {
//  private Connection connection;
  private String name;
  private String propFilename;

  private static PrintStream log = System.out;
  private static DBConnectionPool pool;

  /**
   *  Creates new CreateDatabase
   */
  public DBDatabase() {
  }

  public void setPropertiesFilename(String s) {
    propFilename = s;
  }

  public void setName(String s) {
    name = s;
  }

  public String getName() {
    return name;
  }

  public Connection getConnection() {
    return pool.getConnection();
  }

  public void initialize() {
    XMLProperties xprop = loadProperties(propFilename);
    initPool(xprop);
    initDatabase(xprop);
  }

  public boolean create(String s) {
    name = s;
    drop(name);
    return executeSQL("CREATE DATABASE " + name);
  }

  public boolean use(String db) {
    name = db;
    return executeSQL("USE " + name);
  }


  public boolean drop(String name) {
    return executeSQL("DROP DATABASE " + name, true);
  }

  public boolean createTable(DBTable table) {
    use(name);
    dropTable(table);
    return executeSQL(table.getCreateSQL());
  }

  public boolean dropTable(DBTable table) {
    return executeSQL("DROP TABLE " + table.getName(), true);
  }

  public void returnConnection(Connection conn) {
    pool.returnConnection(conn);
  }

  public void shutdown() {
    pool.shutdown();
  }

  private XMLProperties loadProperties(String propFile) {
    XMLProperties xprop = new XMLProperties();

    xprop.load(propFile);
    System.out.println(xprop);
    return xprop;
    // to do - error handling
  }

  private void initPool(XMLProperties xprop) {
    Properties poolProps = xprop.getProperties("pool");
    System.out.println(poolProps);
    pool = new DBConnectionPool(poolProps);
  }

  private boolean executeSQL(String sql) {
    return executeSQL(sql, false);
  }

  private boolean executeSQL(String sql, boolean supressError) {
    boolean ok = true;
    Connection conn = null;
    try {
      conn = getConnection();
      Statement stmt = conn.createStatement();
      log.println("Executing : " + sql);
      stmt.executeUpdate(sql);
    } catch (SQLException se) {
      if (!supressError) {
        log.println(se);
      }
      ok = false;
    } finally {
      returnConnection(conn);
    }
    return ok;
  }

  private void initDatabase(XMLProperties xprop) {
    Properties dbProps = xprop.getProperties("database");
    String name = dbProps.getProperty("name");
    int nTables = Integer.parseInt(dbProps.getProperty("tables"));
    create(name);
    createTablesFromProperties(xprop, nTables);
  }

  private void createTablesFromProperties(XMLProperties xprop, int nTables) {
    Properties props;
    for (int i = 0; i < nTables; i++) {
      props = xprop.getProperties("table" + i);
      //    String tableName = props.getProperty("name");
      DBTable dbTable = new DBTable();
      dbTable.loadFromProperties(props);
      createTable(dbTable);
    }
  }
}
