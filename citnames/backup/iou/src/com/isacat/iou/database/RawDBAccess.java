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
 * @created    26 March 2001
 */
public class RawDBAccess extends DBAccess {

  int rawCount = 0;

  private PrintStream log = System.out;

  private final static String RAW_TABLE = "raw";

  public RawDBAccess() {
  }

  public int getRecordCount() {
    return getRecordCount(RAW_TABLE);
  }

  public void store(ArrayList docs) {
    Connection conn = pool.getConnection();
    PreparedStatement storeraw = null;
    try {
      storeraw = conn.prepareStatement("INSERT INTO " + RAW_TABLE + " VALUES (?,?,?,?,?,?,?,?)");
    } catch (Exception e) {
      log.println("error preparing raw store statement : " + e);
    }
    for (int i = 0; i < docs.size(); i++) {
      recordCount++;
      RawDocument rd = (RawDocument) docs.get(i);
      HyperLink rdlink = rd.getLink();
      try {
        storeraw.setInt(1, rd.getID());
        storeraw.setString(2, rdlink.getHost());
        storeraw.setString(3, rdlink.getPath());
        storeraw.setString(4, rdlink.getFilename());
        storeraw.setDate(5, rd.getPageDate());
        storeraw.setDate(6, rd.getExpiryDate());
        storeraw.setDate(7, rd.getScanDate());
        storeraw.setString(8, rd.getContent());

      } catch (Exception e) {
        log.println("error setting raw store statement : " + e);
      }
      try {
        storeraw.executeUpdate();
        rd.setID(recordCount);
      } catch (Exception e) {
        log.println("error executing raw store statement : " + e);
      }
    }
    docs.clear();
    pool.returnConnection(conn);
  }
}
