/*
 * HTTPReader.java
 *
 * Created on 24 March 2001, 18:46
 */

// package com.isacat.iou.io;

import java.util.*;
import java.io.*;
import java.net.*;


// import com.isacat.iou.html.*;

/**
 * @author     danny
 * @created    24 March 2001
 * @version
 */
public class HTTPReader {

  private int maxlength;
  private ArrayList mimetypes;
  private long date = -1;
  private long lastmodified = -1;
  private long expiration = -1;
  private long scandate = -1;
  private int responsecode = 0;
  private String mimetype = null;
  private char[] content;
  private int length = 0;
  private String warning;

  private GenericParser parser = null;

  public final static int DEFAULT_MAXLENGTH = 50000;
  public final static String[] DEFAULT_MIMETYPES = {"text/plain", "text/html", "text/xml"};

  private static PrintStream log = System.out;

  /**
   *  Creates new HTTPReader
   */
  public HTTPReader() {
    maxlength = DEFAULT_MAXLENGTH;
    mimetypes = new ArrayList(Arrays.asList(DEFAULT_MIMETYPES));
  }

  public void setMimeTypes(ArrayList al) {
    mimetypes = al;
  }

  public void setMaxLength(int length) {
    maxlength = length;
  }

  public void setParser(GenericParser p) {
    parser = p;
  }

  public String getWarning() {
    return warning;
  }

  public ArrayList getMimeTypes() {
    return mimetypes;
  }

  public boolean isTypeAllowed(String type) {
    return mimetypes.contains(type);
  }

  public String getType() {
    return mimetype;
  }


  public long getDate() {
    return date;
  }

  public long getLastModified() {
    return lastmodified;
  }

  public long getExpiryDate() {
    return expiration;
  }


  public long getScanDate() {
    return scandate;
  }

  public int getresponsecode() {
    return responsecode;
  }

  public char[] getContent() {
    return content;
  }

  public String getContentAsString() {
    if (content == null) {
      return null;
    }
    return new String(content);
  }

  public boolean isAcceptable() {
    return getType().startsWith("text");
  }

  public boolean read(URL url, boolean parse) {
    warning = "Unknown Error";

    try {
      if (url.getProtocol().equals("http")) {
        scandate = System.currentTimeMillis();
        URLConnection urlconn = url.openConnection();
        HttpURLConnection httpconn = (HttpURLConnection) urlconn;
        responsecode = httpconn.getResponseCode();
        if (responsecode == HttpURLConnection.HTTP_OK) {

          mimetype = httpconn.getContentType();
          if (isTypeAllowed(mimetype)) {
            getConnectionValues(httpconn);
            readContent(urlconn, parse);
            httpconn.disconnect();
          } else {
            warning = "MIME type not allowed";
            return false;
          }

        } else {
          warning = "HTTP response code : " + responsecode;
          return false;
          // not HTTP_OK
        }
      } else {
        warning = "URL protocol : " + url.getProtocol();
        return false;
        // not http
      }

    } catch (ConnectException ce) {
      warning = "Can't connect : " + ce.getMessage();
      log.println(ce);
      return false;
    } catch (IOException ioe) {
      warning = ioe.getMessage();
      log.println(ioe);
      return false;
    }

    return true;
  }

  private void getConnectionValues(HttpURLConnection httpconn) {
    lastmodified = httpconn.getLastModified();
    date = httpconn.getDate();
    expiration = httpconn.getExpiration();
    length = httpconn.getContentLength() > maxlength ? maxlength : httpconn.getContentLength();
  }

  private boolean readContent(URLConnection urlconn, boolean parse) {
    boolean ok = true;
    InputStreamReader isr = null;
    BufferedReader in = null;
    try {
      isr = new InputStreamReader(urlconn.getInputStream());
      in = new BufferedReader(isr);
      //  in.mark(length);
      content = new char[length];
      in.read(content, 0, length);
      // in.reset();
      in.close();
      isr.close();
      if (parse && (parser != null)) {
        CharArrayReader car = new CharArrayReader(content);
        parser.parse(car);
      }
    } catch (IOException ioe) {
      warning = ioe.getMessage();
      log.println(ioe);
      ok = false;
    } finally {
      in = null;
      isr = null;
      return ok;
    }
  }



}
