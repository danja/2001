
package com.isacat.iou.buffers;

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
public class LinkBuffer extends DBBuffer {

  int toDBSize = 1;
  // int fromDBSize = 0;/////////////
  int toDBCount = 1;
  int fromDBCount = 0;

//   private DBConnectionPool pool = null;

  private ArrayList toDBLinks = new ArrayList();
  private ArrayList fromDBLinks = new ArrayList();
  private boolean firstGetLink = true;

  private LinkDBAccess linkdb = new LinkDBAccess();
  static boolean locked;

  private static int HOSTLENGTH = 50;
  private static int PATHLENGTH = 50;
  private static int FILELENGTH = 50;

  private final static String LINKS_TABLE = "links";

  /**
   *  Constructor for the LinkBuffer object
   */
  public LinkBuffer() {
    setDBAccess(linkdb);
  }


  public void save(Object link) {

    //  System.out.println("SAVE " + link);
    if (link == null) {
      return;
    }

    if (inBuffer(fromDBLinks, (HyperLink) link)) {
      fromDBLinks.clear();
      flush();
      //  fromDBCount = 0;
    }
    toDBLinks.add(link);
    toDBCount++;
    if (toDBCount == toDBSize) {
      //  linkdb.store(toDBLinks);
      flush();
      toDBLinks.clear();
    }
  }


  public Object load() {

    //System.out.println("LOAD");
    // Object ob;
    if (firstGetLink || (fromDBCount == fromDBSize)) {
      //  System.out.println("REFRESH");
      fromDBLinks = linkdb.retrieve(fromDBSize);
      // System.out.print("FROMDB");
      //  System.out.println(fromDBLinks);
      if (fromDBLinks == null) {
        return null;
      }
      fromDBCount = 0;
      if (firstGetLink) {
        firstGetLink = false;
      }
    }
    if (fromDBLinks.size() == 0) {
      return null;
    }
    HyperLink link = (HyperLink) fromDBLinks.get(fromDBCount++);

    if (inBuffer(toDBLinks, link)) {
      flush();
      return load();
    }

    return link;
  }

  public boolean inBuffer(ArrayList buffer, HyperLink test) {

    for (int i = 0; i < buffer.size(); i++) {
      if (buffer.get(i) != null) {
        //   if (((HyperLink) toDBLinks.get(i)).getHost().equals(test.getHost()) ) {
        if (((HyperLink) buffer.get(i)).matches(test)) {
          return true;
        }
      }
    }
    locked = false;
    return false;
  }

  /**
   *  Description of the Method
   *
   * @return    Description of the Returned Value
   */
  public boolean flush() {

    ArrayList toStore = new ArrayList();
    // System.out.println("FLUSH " + toDBLinks.size());
    for (int i = 0; i < toDBLinks.size(); i++) {

      Object ob = toDBLinks.get(i);
      if (ob == null) {
        break;
      }
      HyperLink link = (HyperLink) ob;
      boolean sizeok;
      try {
        sizeok = (link.getHost().length() <= HOSTLENGTH) && (link.getPath().length() <= PATHLENGTH) && (link.getFilename().length() <= FILELENGTH);
      } catch (Exception e) {
        sizeok = false;
      }
      if (sizeok) {
        ResultSet rs = linkdb.check(link);
        boolean linkInDB = true;

        try {
          linkInDB = rs.next();
        } catch (Exception e) {
          System.out.println(e);
        }
        //   System.out.println("LINK IN DB = " + linkInDB);
        if (linkInDB) {
          linkdb.update(rs, link);
          //     System.out.println("UPDATE " + link);
          continue;
        } else {

          toStore.add(link);
        }
      }
    }
    linkdb.store(toStore);
    toDBLinks.clear();
    toDBCount = 0;

    return true;
  }

}
