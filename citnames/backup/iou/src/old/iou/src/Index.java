/*
 * Corpus.java
 *
 * Created on 01 December 2000, 20:01
 */
import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.*;

import com.wrox.common.database.*;
import com.wrox.common.util.*;
import com.wrox.iou.page.*;
import com.wrox.iou.database.*;
import com.wrox.iou.filter.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    12 February 2001
 */
public class Index {
  XMLProperties xprop;
  ArrayList doclinks = new ArrayList();
  DBConnectionPool cp = null;
  Connection conn = null;
  DocumentBuffer docbuffer = new DocumentBuffer();
  private final boolean RAWMODE = false;

  private final static String PROP_FILE = "iouproperties.xml";
  private final static int MAXPAGES = 10000;


  public Index() {
    int pagecount = 0;
    HyperLink link;
    ArrayList docLinks;
    LinkBuffer linkbuffer = new LinkBuffer();
    Document doc;
    DBAccess dbaccess;
    Filter filter;
    if (RAWMODE) {
      doc = new RawDocument();
      dbaccess = new RawDBAccess();
    } else {
      doc = new FilteredDocument();
      dbaccess = new FilteredDBAccess();
      filter = new Filter();
      doc.setFilter(filter);
    }
    // sets up log file
    LogFile lf = new LogFile();
    lf.setLogLevel(LogFile.NORMAL);
    LogPrinter log = (LogPrinter) lf.getPrintStream("ioulog.txt", false);

    xprop = new XMLProperties();
    xprop.load(PROP_FILE);
    Properties poolprops = xprop.getProperties("pool");

    try {
      cp = new DBConnectionPool(poolprops);
    } catch (Exception e) {
      System.out.println("error = " + cp);
      log.println(e);
    }
    cp.setLog(log);

    docbuffer.setDBAccess(dbaccess);
    docbuffer.setConnectionPool(cp);

    linkbuffer.setConnectionPool(cp);
    linkbuffer.setLog(log);

    doc.setLog(log);

    while (pagecount++ < MAXPAGES) {

      link = (HyperLink) linkbuffer.load();
      if (link == null) {
        break;
      }
      System.out.println("\n\nCurrent page = " + link.getURL().toString());

      //   try{Thread.sleep(1000);}catch(Exception e){}

      //   doc = new RawDocument();
      doc.setLink(link);
      doc.read(RAWMODE);
      link.setScanned(true);
      linkbuffer.save(link);
      // if(!link.getType().startsWith("text")) continue;

      if (doc.getResponseCode() == HttpURLConnection.HTTP_OK) {
        doc.resolveFields();
        if (!doc.isAcceptable()) {
          continue;
        }

        docbuffer.save(doc);

        doclinks = doc.getLinks();
        // System.out.println("doclinks = " + doclinks);
        if (doclinks != null) {

          for (Iterator i = doclinks.iterator(); i.hasNext(); ) {
            HyperLink newlink = (HyperLink) i.next();
            //        System.out.println("newlink = "+newlink);
            if (!newlink.matches(link)) {
              linkbuffer.save(newlink);
            }
          }
        } else {
          continue;
        }
        doc.setID(docbuffer.getRecordCount());
        link.setDocRef(doc.getID());
        linkbuffer.save(link);
      }
      linkbuffer.flush();
      docbuffer.flush();
    }
    cp.shutdown();
  }


  public static void main(String args[]) {
    new Index();
  }
}
