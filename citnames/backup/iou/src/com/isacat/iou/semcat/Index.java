package com.isacat.iou.semcat;

/*
 * Corpus.java
 *
 * Created on 01 December 2000, 20:01
 */
import java.sql.*;
import java.io.*;
import java.util.*;
import java.net.*;

import javax.swing.text.html.HTMLEditorKit.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.isacat.iou.io.*;
import com.isacat.iou.database.*;
import com.isacat.iou.util.*;
import com.isacat.iou.filter.*;
import com.isacat.iou.parse.*;
import com.isacat.iou.html.*;
import com.isacat.iou.parse.rdf.*;
import com.isacat.iou.buffers.*;
/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    12 February 2001
 */
public class Index {
  int httpResponseCode = -1;
  XMLProperties xprop;
  ArrayList doclinks = new ArrayList();
  DBConnectionPool cp = null;
  Connection conn = null;
  DocumentBuffer docbuffer = new DocumentBuffer();
  private final boolean RAWMODE = false;

  private final static String PROP_FILE = "H:/isacat_code/iou/src/semcat/semcatproperties.xml";
  private final static int MAXPAGES = 5;


  public Index() {
    int pagecount = 0;
    HyperLink link;
    ArrayList docLinks;
//    linkDBAccess linkDBAccess = new linkDBAccess();
    LinkDBAccess linkDBAccess = new LinkDBAccess();
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

    linkDBAccess.setConnectionPool(cp);
    linkDBAccess.setLog(log);

    doc.setLog(log);

    while (pagecount++ < MAXPAGES) {
      System.out.println("Pages : " + pagecount);
      link = linkDBAccess.retrieve();
      if (link == null) {
        System.out.println("NULL LINK");
        break;
      }
      System.out.println("\n\nCurrent page = " + link.getURL().toString());

      //   try{Thread.sleep(1000);}catch(Exception e){}

      //   doc = new RawDocument();
      doc.setLink(link);
      doc.read(true);
      // RAWMODE
      link.setScanned(true);
      linkDBAccess.store(link);
      // if(!link.getType().startsWith("text")) continue;
      System.out.println("here");
      httpResponseCode = doc.getHttpResponseCode();
      if (httpResponseCode == HttpURLConnection.HTTP_OK) {
        doc.resolveFields();
        if (!doc.isAcceptable()) {
          System.out.println("NOT ACCEPTABLE");
          continue;
        }

        docbuffer.save(doc);

        doclinks = doc.getLinks();
        System.out.println("doclinks = " + doclinks);
        if (doclinks != null) {

          for (Iterator i = doclinks.iterator(); i.hasNext(); ) {
            HyperLink newlink = (HyperLink) i.next();
            System.out.println("newlink = " + newlink);
            if (!newlink.matches(link)) {
              linkDBAccess.store(newlink);
            }
          }
        } else {
          continue;
        }
        doc.setID(docbuffer.getRecordCount());
        link.setDocRef(doc.getID());
        linkDBAccess.store(link);
      } else {
        System.out.println(httpResponseCode);
      }
      //   linkDBAccess.flush();
      docbuffer.flush();
    }
    cp.shutdown();
  }


  public static void main(String args[]) {
    new Index();
  }
}
