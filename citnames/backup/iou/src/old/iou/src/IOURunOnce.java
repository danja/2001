import java.io.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;

import com.wrox.common.database.*;
import com.wrox.common.util.*;
import com.wrox.iou.page.*;
import com.wrox.iou.database.*;
/**
 * @author     Danny Ayers
 * @created    21 November 2000
 * @version    0.1
 */
public class IOURunOnce {
  XMLProperties xprop;

  private static int HOSTLENGTH = 50;
  private static int PATHLENGTH = 50;
  private static int FILELENGTH = 50;
  private static int MIMELENGTH = 15;

  /**
   *  PrintStream for logging
   */
  private static PrintStream log;
  private final static String PROP_FILE = "iouproperties.xml";


  /**
   *  Creates new RunOnce
   */
  public IOURunOnce() {
    xprop = new XMLProperties();
    xprop.load(PROP_FILE);

    Properties poolProps = xprop.getProperties("pool");
    DBConnectionPool pool = new DBConnectionPool(poolProps);
    Connection conn = pool.getConnection();

    LogFile lf = new LogFile();
    lf.setLogLevel(LogFile.DEBUG);
    log = lf.getPrintStream("runoncelog.txt", false);
    dropTables(pool);
    makeTables(pool);

    setSeedURLs(pool);

    pool.shutdown();

  }

  public void setSeedURLs(DBConnectionPool pool) {
    HyperLink link;
    LinkBuffer lbuffer = new LinkBuffer();
    lbuffer.setConnectionPool(pool);
    String[] urls = {
    /*
     * "http://www.mumbai-central.com",
     * "http://www.mu.ac.in",
     * "http://www.mumbainet.com",
     * "http://theory.tifr.res.in/bombay",
     * "http://www.mumbaizone.com",
     * "http://www.cidcoindia.com/right.htm",
     * "http://www.inmumbai.com/inmumbai/startf.html",
     * "http://www.indiatravelite.com/mumbai/aboutmumbai.htm",
     * "http://www.shivajipark.com/",
     * "http://bombaylocal.indya.com/"
     */
      "http://localhost/index.htm"
    //"http://www.caljug.org/",

    //"http://www.indiajava.org/",
    //"http://abhinavgupta.tripod.com/JavaCert/india-java.html",
    //"http://www.neca.com/~vmis/iijug.html",
    //"http://sunsite.iisc.ernet.in/java/applets/"

    //      "http://www.lankamasks.org/index.html",
    //   "http://www.lankapage.com/index.html"
      };
    for (int i = 0; i < urls.length; i++) {
      link = new HyperLink();
      link.setAbsolute(urls[i]);
      link.setType("text/html");
      lbuffer.save(link);
    }
    lbuffer.flush();
  }


  public void dropTables(DBConnectionPool pool) {
    Connection conn = pool.getConnection();
    Statement stmt = null;
    // URL List
    try {
      stmt = conn.createStatement();
    } catch (Exception e) {
      log.println(e);
      e.printStackTrace(log);
    }

    try {
      log.println("executing drops");
      stmt.execute("drop table links");
      stmt.execute("drop table raw");
      stmt.execute("drop table core");
    } catch (Exception e) {
      log.println(e);
      e.printStackTrace(log);
    }
  }

  public String makeTables(DBConnectionPool pool) {
    Connection conn = pool.getConnection();
    log.println("Creating tables");
    Statement stmt = null;

// URL List
    try {
      stmt = conn.createStatement();
    } catch (Exception e) {
      log.println(e);
      e.printStackTrace(log);
    }
    String urlTable = "CREATE TABLE links ("
    // INTEGER for SQL Server & Postgresql, INT(5) for MySQL
       + "host VARCHAR(" + HOSTLENGTH + "), "
       + "path VARCHAR(" + PATHLENGTH + "), "
       + "filename VARCHAR(" + FILELENGTH + "), "
       + "type VARCHAR(" + MIMELENGTH + "), "
       + "scanned INTEGER, "
       + "docref INTEGER, "
       + "inlinks INTEGER)";
    try {
      log.println("executing " + urlTable);
      stmt.execute(urlTable);
    } catch (Exception e) {
      log.println(e);
      e.printStackTrace(log);
    }

// Raw
    try {
      stmt = conn.createStatement();
    } catch (Exception e) {
      log.println(e);
      e.printStackTrace(log);
    }
    String rTable = "CREATE TABLE raw ("
       + "ID INT(7), "
    // INTEGER for SQL Server & Postgresql, INT(5) for MySQL
       + "HOST VARCHAR(" + HOSTLENGTH + "), "
       + "PATH VARCHAR(" + PATHLENGTH + "), "
       + "FILENAME VARCHAR(" + FILELENGTH + "), "
       + "PAGEDATE DATETIME, "
       + "EXPIRYDATE DATETIME, "
       + "SCANDATE DATETIME, "
       + "CONTENT TEXT)";

    try {
      log.println("executing " + rTable);
      stmt.execute(rTable);
    } catch (Exception e) {
      log.println(e);
      e.printStackTrace(log);
    }

    // Filtered
    try {
      stmt = conn.createStatement();
    } catch (Exception e) {
      log.println(e);
      e.printStackTrace(log);
    }
    String fTable = "CREATE TABLE core ("
       + "ID INT(7), "
    // INTEGER for SQL Server & Postgresql, INT(x) for MySQL
       + "COREWORDS VARCHAR(200))";

    try {
      log.println("executing " + fTable);
      stmt.execute(fTable);
    } catch (Exception e) {
      log.println(e);
      e.printStackTrace(log);
    }
    return "Tables created ok.";
  }

  /**
   *  The main program for the RunOnce class
   *
   * @param  args  The command line arguments
   */
  public static void main(String[] args) {
    new IOURunOnce();
  }
}
