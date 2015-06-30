package com.isacat.iou.database;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.beans.*;

import com.isacat.iou.util.*;
/**
 *  General-purpose JDBC database connection pool designed to be tolerant of SQL
 *  errors & connection mishandling The class is 'semi-bean' - supports some
 *  bean facilities such as property change listeners, though would need more
 *  work to be deployed as a 'proper' bean.
 *
 * @author     Danny Ayers
 * @created    10 November 2000
 */
public class DBConnectionPool_1 {
  // implements Serializable

  private PropertyChangeSupport changesupport;
  private Properties properties;

  private Connection[] connection;
  private int[] state;
  private long[] expiry;

  private String database;
  private String user;
  private String driver;
  private String catalog;
  private String password;
  private int size;
  private int delay;
  private int period;
  private int lifespan;
  private Timer watchdog;
  private String testtable = "TEST" + Integer.toString((int) (Math.random() * 1000D));
  private int available;

  private static PrintStream log = System.out;
  private final static int CLOSED = 0;
  private final static int AVAILABLE = 1;
  private final static int IN_USE = 2;
  private final static int RETIRING = 3;


  /**
   *  Constructor for the ConnectionPool object Parameters - pool.datase :
   *  database URL, e.g. jdbc:odbc:wroxcon pool.driver : JDBC driver, e.g.
   *  sun.jdbc.odbc.JdbcOdbcDriver pool.user : authorised database user name
   *  pool.password : user's authentication pool.size : number of connections in
   *  pool, default = 10 pool.delay : initialisation time delay in millis before
   *  watchdog kicks in, default = 30secs pool.period : time between monitor
   *  cycles, default = 10secs pool.lifespan : maximum lease for a connection,
   *  default = 30mins
   *
   * @param  prop  Properties specifying pool
   */
  public DBConnectionPool_1(Properties prop) {
    // throws SQLException
    properties = prop;
    changesupport = new PropertyChangeSupport(this);
    database = properties.getProperty("database");
    driver = properties.getProperty("driver");
    catalog = properties.getProperty("catalog");
    user = properties.getProperty("user");
    password = properties.getProperty("password");
    size = Integer.parseInt(properties.getProperty("size", "10"));
    delay = Integer.parseInt(properties.getProperty("delay", "30000"));
    period = Integer.parseInt(properties.getProperty("period", "10000"));
    lifespan = Integer.parseInt(properties.getProperty("lifespan", "1800000"));
    prepareConnections();
  }


  /**
   *  Sets the Log attribute of the ConnectionPool object
   *
   * @param  lp  The new Log value
   */
  public void setLog(LogPrinter lp) {
    log = lp;
  }


  /**
   *  Gets the Connection attribute of the ConnectionPool object
   *
   * @return    The Connection value
   */
  public synchronized Connection getConnection() {
    for (int i = 0; i < size; i++) {
      if (state[i] == AVAILABLE) {
        state[i] = IN_USE;
        //   change();
        expiry[i] = System.currentTimeMillis() + lifespan;
        // log.debugln("Pool giving connection " + i);
        return connection[i];
      }
    }
    return null;
  }


  /**
   *  Gets the AvailableConnections attribute of the ConnectionPool object
   *
   * @return    The AvailableConnections value
   */
  public int getAvailableConnections() {
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (state[i] == AVAILABLE) {
        count++;
      }
    }
    return count;
  }


  /**
   *  Gets the Status attribute of the ConnectionPool object
   *
   * @return    The Status value
   */
  public String getStatus() {
    String status = "";
    for (int i = 0; i < size; i++) {
      switch (state[i]) {
        case AVAILABLE:
          status += "A";
          break;
        case IN_USE:
          status += "U";
          break;
        case RETIRING:
          status += "R";
          break;
        case CLOSED:
          status += "C";
          break;
      }
    }
    return status;
  }


  /**
   *  Description of the Method
   *
   * @return    Description of the Returned Value
   */
  public synchronized boolean prepareConnections() {
    // log.debugln("Pool : " + properties);
    watchdog = new Timer();
    watchdog.schedule(new PoolMonitor(), delay, period);
    /*
     * String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
     * String chemin = "c:/jdk1.3/jswdk-1.0.1/examples/jsp/liste/base/bd.mdb";
     * String fullConnectionString = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + chemin;
     * String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
     * String chemin = "c:/jdk1.3/jswdk-1.0.1/examples/jsp/liste/base/bd.mdb";
     * String fullConnectionString = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + chemin;
     */

    boolean ok = false;
    try {
      Class.forName(driver);
    } catch (Exception e) {
      log.println("Can't find JDBC driver : " + driver);
      log.println(e);
      System.exit(1);
      // can't continue without a DB
    }
    try {
      connection = new Connection[size];
      state = new int[size];
      expiry = new long[size];
      Connection c = null;
      for (int i = 0; i < size; i++) {
        connection[i] = obtainConnection();
        if (connection[i] != null) {
          state[i] = AVAILABLE;
          //   change();
          ok = true;
        } else {
          // log.debugln("Connection " + i + " closed.");
          state[i] = CLOSED;
        }
      }
      log.println("Pool ready.");
    } catch (Exception e) {
      log.println(e);
    }
    return ok;
  }


  /**
   *  Description of the Method
   *
   * @param  conn  Description of Parameter
   */
  public synchronized void returnConnection(Connection conn) {

    if (conn == null) {
      return;
    }
    for (int i = 0; i < size; i++) {
      if (conn.equals(connection[i])) {
        //     log.debugln("Returning connection "+i);
        try {
          if (conn.isClosed()) {
            state[i] = CLOSED;
            return;
          } else {
            state[i] = AVAILABLE;

          }
        } catch (Exception e) {
          log.println(e);
        }
      }
    }
    // change();
  }


  /**
   *  Description of the Method
   *
   * @param  conn    Description of Parameter
   * @param  millis  Description of Parameter
   */
  public synchronized void keepAlive(Connection conn, int millis) {
    for (int i = 0; i < size; i++) {
      if (conn.equals(connection[i])) {
        expiry[i] += millis;
      }
    }
  }


  /**
   *  Description of the Method
   *
   * @exception  SQLException  Description of Exception
   */
  public synchronized void closeAll() throws SQLException {
    for (int i = 0; i < size; i++) {
      state[i] = RETIRING;
    }
    while (getStatus().lastIndexOf('C') != -1) {
      try {
        Thread.sleep(lifespan);
      } catch (Exception e) {
        log.println(e);
      }
    }
    watchdog.cancel();
    // stop the monitor
  }


  /**
   *  Description of the Method
   */
  public void shutdown() {
    try {
      closeAll();
      log.println("Shutting down connection pool...");
    } catch (Exception e) {
      log.println(e);
    } finally {
      for (int i = 0; i < size; i++) {
        connection[i] = null;
        // throw away the connection
        state[i] = CLOSED;
        expiry[i] = 0;
      }
      changesupport = null;
    }
  }


  /**
   *  Description of the Method
   *
   * @return    Description of the Returned Value
   */
  private synchronized Connection obtainConnection() {
    Connection conn = null;
    try {
      System.out.println(database + user + password);
      DriverManager.setLogWriter(new java.io.PrintWriter(System.out));
      //     conn = DriverManager.getConnection(database); // , user, password
      conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=C:/netbeans/bin/semcat.mdb;UID=Admin;PWD=isacat");
      // conn.setCatalog(catalog);
      System.out.println("CONNECTION : " + conn);
      conn.setAutoCommit(true);
    } catch (Exception e) {
      log.println("Can't get connection\n" + e);
    }
    Statement stmt = null;
    ResultSet rs = null;
    String returnstring = null;

    /*
     * try {
     * conn.setAutoCommit(true);
     * stmt = conn.createStatement();
     * stmt.execute("CREATE TABLE " + testtable + " (conntest VARCHAR(10))");
     * stmt.executeUpdate("INSERT INTO " + testtable + " (conntest) VALUES('testing')");
     * rs = stmt.executeQuery("SELECT * FROM " + testtable);
     * rs.next();
     * returnstring = rs.getString("conntest");
     * stmt.execute("DROP TABLE " + testtable);
     * } catch (Exception e) {
     * log.println("Exception during test SQL\n" + e);
     * } finally {
     * rs = null;
     * stmt = null;
     * }
     * if (!returnstring.equals("testing")) {
     * log.println("Connection test failed.");
     * conn = null;
     * }
     */
    return conn;
  }


  /**
   *  Description of the Class
   *
   * @author     Danny Ayers
   * @created    26 March 2001
   */
  class PoolMonitor extends TimerTask {
    long loops = 0;
    long total = 0;


    /**
     *  Main processing method for the PoolMonitor object
     */
    public void run() {
      loops++;
      total += getAvailableConnections();
      // log.debugln("average connections = " + (total / loops));
      for (int i = 0; i < size; i++) {
        if (state[i] != AVAILABLE) {
          refresh(i);
        }
      }
      // log.println(getStatus());
    }


    /**
     *  Description of the Method
     *
     * @param  i  Description of Parameter
     */
    public synchronized void refresh(int i) {
      boolean finish = false;
      long now = System.currentTimeMillis();
      if (connection[i] == null) {
        state[i] = CLOSED;
      }
      if (state[i] == RETIRING) {
        finish = true;
      }
      //	log.debugln("life of connection "+i+" is "+(expiry[i]-now)+" millis");
      if (((finish) || (state[i] == IN_USE)) && (now > expiry[i])) {

        try {
          //		log.debugln("Connection " + i + " expired");
          if (!connection[i].isClosed()) {
            connection[i].close();
          }
          state[i] = CLOSED;
        } catch (Exception e) {
          log.println("Connection " + i + " :\n" + e);
        }
      }

      if (!finish) {
        try {
          if (!connection[i].isClosed()) {
            connection[i].close();
          }

          connection[i] = DriverManager.getConnection(database, user, password);
          //	log.debugln("Connection " + i + " refreshed");
          state[i] = AVAILABLE;

        } catch (Exception e) {
          log.println("Connection " + i + " :\n" + e);
        }
      }
      //   change();
    }
  }

}

