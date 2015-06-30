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
public class IndexThreaded {
  int httpResponseCode = -1;
  XMLProperties xprop;
  ArrayList doclinks = new ArrayList();
  DBConnectionPool cp = null;
  Connection conn = null;
  DocumentBuffer docbuffer = new DocumentBuffer();
  private final boolean RAWMODE = false;
  private static int threadCount = 0;
  private static int pagecount = 0;
  private final static String PROP_FILE = "H:/isacat_code/iou/src/semcat/semcatproperties.xml";
  private final static int MAXPAGES = 10000;


  public IndexThreaded() {
    xprop = new XMLProperties();
    xprop.load(PROP_FILE);
    Properties poolprops = xprop.getProperties("pool");
    try {
      cp = new DBConnectionPool(poolprops);
    } catch (Exception e) {
      System.out.println("error = " + cp);

    }
    while (pagecount < MAXPAGES) {
      while (threadCount < 10) {
        new HttpPump().start();
      }
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
      }

    }

    cp.shutdown();
  }


  public static void main(String args[]) {
    new IndexThreaded();
  }

  /**
   *  Description of the Class
   *
   * @author     Danny Ayers
   * @created    20 April 2001
   */
  class HttpPump extends Thread {
    ArrayList docLinks;
    LinkBuffer linkbuffer = new LinkBuffer();
    Document doc;
    DBAccess dbaccess;
    Filter filter;


    public HttpPump() {
      IndexThreaded.threadCount++;
      //   HyperLink link;

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

      cp.setLog(log);

      docbuffer.setDBAccess(dbaccess);
      docbuffer.setConnectionPool(cp);

      linkbuffer.setConnectionPool(cp);
      linkbuffer.setLog(log);

      doc.setLog(log);
    }

    public void run() {

      IndexThreaded.pagecount++;
      System.out.println("pagecount = " + IndexThreaded.pagecount);
      System.out.println("threadcount = " + IndexThreaded.threadCount);
      HyperLink link = (HyperLink) linkbuffer.load();
      if (link != null) {
        System.out.println("\n\nCurrent page = " + link.getURL().toString());

        //   try{Thread.sleep(1000);}catch(Exception e){}

        //   doc = new RawDocument();
        doc.setLink(link);
        doc.read(true);
        // RAWMODE
        link.setScanned(true);
        linkbuffer.save(link);
        // if(!link.getType().startsWith("text")) continue;
        System.out.println("here");
        httpResponseCode = doc.getHttpResponseCode();
        if (httpResponseCode == HttpURLConnection.HTTP_OK) {
          doc.resolveFields();
          if (doc.isAcceptable()) {

            docbuffer.save(doc);

            doclinks = doc.getLinks();
            System.out.println("doclinks = " + doclinks);
            if (doclinks != null) {

              for (Iterator i = doclinks.iterator(); i.hasNext(); ) {
                HyperLink newlink = (HyperLink) i.next();
                System.out.println("newlink = " + newlink);
                if (!newlink.matches(link)) {
                  linkbuffer.save(newlink);
                }
              }

              doc.setID(docbuffer.getRecordCount());
              link.setDocRef(doc.getID());
              linkbuffer.save(link);
            }
          } else {
            System.out.println(httpResponseCode);
          }
          linkbuffer.flush();
          docbuffer.flush();
        }
      }
      IndexThreaded.threadCount--;
    }

  }
}
