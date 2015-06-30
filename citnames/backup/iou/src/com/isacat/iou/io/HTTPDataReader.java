
package com.isacat.iou.io;

import java.util.*;
import java.io.*;
import java.net.*;

import com.isacat.iou.parse.*;

/**
 * @author     danny
 * @created    24 March 2001
 * @version
 */
public class HTTPDataReader extends DataReader {

  private ArrayList mimetypes;
  private long lastmodified = -1;
  private long expiration = -1;

  private int httpResponseCode = 0;
  private String httpResponseMessage = null;
  private String mimetype = null;

  private DataParser parser = null;
  private URL url = null;

// public final static int DEFAULT_MAXLENGTH = 65530;
  public final static String[] DEFAULT_MIMETYPES = {"text/plain", "text/html", "text/xml"};

  private static PrintStream log = System.out;

  public HTTPDataReader() {
    mimetypes = new ArrayList(Arrays.asList(DEFAULT_MIMETYPES));
  }

  public void setMimeTypes(ArrayList al) {
    mimetypes = al;
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

  public long getLastModified() {
    return lastmodified;
  }

  public long getExpiryDate() {
    return expiration;
  }


  public int getHttpResponseCode() {
    return httpResponseCode;
  }

  public String getHttpResponseMessage() {
    return httpResponseMessage;
  }

  public boolean isAcceptable() {
    return getType().startsWith("text");
  }

  public boolean read() {
    return read(url, true, false);
  }

  public boolean read(URL url, boolean readRaw, boolean parse) {
    return read(url, readRaw, parse, false);
  }

  public boolean read(URL url, boolean readRaw, boolean parse, boolean unzip) {
    setWarning("Unknown Error");

    try {
      if (url.getProtocol().equals("http")) {
        URLConnection urlconn = url.openConnection();
        HttpURLConnection httpconn = (HttpURLConnection) urlconn;
        httpResponseCode = httpconn.getResponseCode();
        httpResponseMessage = httpconn.getResponseMessage();
        if (httpResponseCode == HttpURLConnection.HTTP_OK) {

          mimetype = httpconn.getContentType();
          if (isTypeAllowed(mimetype)) {
            getConnectionValues(httpconn);
            readContent(urlconn.getInputStream(), readRaw, parse);
            httpconn.disconnect();
          } else {
            setWarning("MIME type not allowed");
            return false;
          }

        } else {
          setWarning("HTTP response code : " + httpResponseCode);
          return false;
          // not HTTP_OK
        }
      } else {
        setWarning("URL protocol : " + url.getProtocol());
        return false;
        // not http
      }

    } catch (ConnectException ce) {
      setWarning("Can't connect : " + ce.getMessage());
      log.println(ce);
      return false;
    } catch (IOException ioe) {
      setWarning(ioe.getMessage());
      log.println(ioe);
      return false;
    }

    return true;
  }

  private void getConnectionValues(HttpURLConnection httpconn) {
    lastmodified = httpconn.getLastModified();
    setDate(httpconn.getDate());
    expiration = httpconn.getExpiration();
    setContentLength(httpconn.getContentLength() > getMaxLength() ? getMaxLength() : httpconn.getContentLength());
  }
}

