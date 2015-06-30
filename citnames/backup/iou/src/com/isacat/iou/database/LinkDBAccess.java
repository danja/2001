
package com.isacat.iou.database;

import java.util.*;
import java.sql.*;
import java.io.*;

import com.isacat.iou.database.*;
import com.isacat.iou.util.*;
import com.isacat.iou.html.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    09 February 2001
 */
public class LinkDBAccess extends DBAccess {

  private String updateString = "UPDATE " + LINKS_TABLE + " SET "
     + LINKS_TABLE + ".INLINKS = ?, " + LINKS_TABLE + ".DOCREF = ?, "
     + LINKS_TABLE + ".TYPE = ?, " + LINKS_TABLE + ".SCANNED = ? WHERE "
     + LINKS_TABLE + ".HOST = ? AND " + LINKS_TABLE + ".PATH = ? AND "
     + LINKS_TABLE + ".FILENAME = ? ";

  private static int HOSTLENGTH = 50;
  private static int PATHLENGTH = 50;
  private static int FILELENGTH = 50;

  private final static String LINKS_TABLE = "SEMCAT.HYPERLINKS";

  /**
   *  Constructor for the LinkBuffer object
   */
  public LinkDBAccess() {
  }

  public synchronized int getRecordCount() {
    return getRecordCount(LINKS_TABLE);
  }

  public synchronized ResultSet check(Object ob) {
    HyperLink link = (HyperLink) ob;
    System.out.println("CHECK");
    System.out.println("link = " + link);
    PreparedStatement check = null;
    ResultSet rs = null;
    Connection conn = null;
    try {
      conn = pool.getConnection();
      System.out.println("test--- conn = " + conn);
      Statement test = conn.createStatement();
      test.executeQuery("SELECT * FROM " + LINKS_TABLE);
      System.out.println("---test");

      check = conn.prepareStatement("SELECT *  FROM " + LINKS_TABLE + " WHERE HOST = ? AND PATH = ? AND FILENAME = ?");

      System.out.println(LINKS_TABLE + ":" + link.getHost() + ":" + link.getPath() + ":" + link.getFilename());

      check.setString(1, link.getHost());
      //
      //     log.println("link.getRelativePath() *"+link.getRelativePath()+"*");
      check.setString(2, link.getPath());
      check.setString(3, link.getFilename());
    } catch (Exception e) {
      log.println("Error setting link check statement : \n" + e);
      e.printStackTrace();
    }

    try {

      rs = check.executeQuery();
      pool.returnConnection(conn);
    } catch (Exception e) {
      log.println("Error executing link check statement : \n" + e);
    }
    return rs;
  }


  public synchronized void update(ResultSet rs, Object ob) {
    HyperLink link = (HyperLink) ob;
    //  System.out.println("UPDATE " + link);
    Connection conn = null;
    PreparedStatement update = null;
    int inlinks = 0;
    int docref = -1;
    String type = null;
    int scanned = 0;

    try {
      scanned = ((rs.getInt("scanned") == 1) || link.isScanned()) ? 1 : 0;
      if (scanned == 1) {
        link.setScanned(true);
      }
      inlinks = rs.getInt("inlinks") + link.getInlinks() + 1;
      if (rs.getInt("docref") < link.getDocRef()) {
        docref = link.getDocRef();
        type = link.getType();
      } else {
        // whatever's already in the db
        docref = rs.getInt("docref");
        type = rs.getString("type");
      }

      // update the record *******************************
      conn = pool.getConnection();
      update = conn.prepareStatement(updateString);
      update.setInt(1, inlinks);
      update.setInt(2, docref);
      update.setString(3, type);
      update.setInt(4, scanned);
      update.setString(5, link.getHost());
      update.setString(6, link.getPath());
      update.setString(7, link.getFilename());
      update.executeUpdate();
    } catch (Exception e) {
      log.println("Error in link update statement : \n" + e);
    }
    pool.returnConnection(conn);
  }

  public synchronized void store(HyperLink link) {
    //  System.out.println("STORE  in linkdbaccess " + links);

    Connection conn = null;
    PreparedStatement store = null;
    //  HyperLink link;
    try {
      conn = pool.getConnection();
      //  for (int i = 0; i < links.size(); i++) {
      //    link = (HyperLink) links.get(i);
      store = conn.prepareStatement("INSERT INTO " + LINKS_TABLE + " VALUES (?,?,?,?,?,?,?)");
      store.setString(1, link.getHost());
      store.setString(2, link.getPath());
      store.setString(3, link.getFilename());
      store.setString(4, link.getType());
      store.setInt(5, (link.isScanned() ? 1 : 0));
      store.setInt(6, link.getDocRef());
      store.setInt(7, link.getInlinks());
      store.executeUpdate();
      //  }
      //   pool.returnConnection(conn);
    } catch (Exception e) {
      log.println("Error in link store statement : " + e);
    }
  }

  public synchronized HyperLink retrieve() {
    System.out.println("Retrieve called");
    PreparedStatement retrieve = null;
    //  log.println("link pool = " + pool);
    Connection conn = pool.getConnection();
    HyperLink link = null;
    ArrayList links = new ArrayList();
    String address = null;
    String path = null;

    try {
      // SELECT TOP 2 * FROM links WHERE DOCREF = -1 ORDER BY INLINKS DESC /////TOP " + fromDBSize + "
      retrieve = conn.prepareStatement("SELECT * FROM " + LINKS_TABLE + " WHERE SCANNED < 1 ORDER BY " + LINKS_TABLE + ".INLINKS, " + LINKS_TABLE);
    } catch (Exception e) {
      log.println("Preparing retrieve statement : " + e);
    }
    // end try retrieve

    try {
      ResultSet rs = retrieve.executeQuery();

      System.out.println("result set : ");
      DBUtils.showResultSet(rs);
      //  for (int i = 0; i < number; i++)
      while (rs.next()) {
        //  while
        address = "http://" + rs.getString("host");
        path = rs.getString("path");
        address += "/" + path;
        address += "/" + rs.getString("filename");

        //    log.println("address = " + address);
        link = new HyperLink();
        link.setAbsolute(address);
        link.setScanned(rs.getInt("scanned") == 1);

        //   links.add(link);
      }
    } catch (Exception e) {
      log.println("Retrieving links : " + e.toString());
    }
    // end try rs
    //pool.returnConnection(conn);
    //  System.out.println("RETRIEVED " + links);
    return link;
  }

}
