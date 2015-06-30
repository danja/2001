/*
 * HyperLink.java
 *
 * Created on 01 December 2000, 10:56
 */

package com.isacat.iou.html;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * @author     Danny Ayers
 * @created    10 February 2001
 * @version
 */
public class HyperLink {

  private PrintStream log = System.out;

  private URL url = null;

  private String protocol = null;
  private String host = null;
  private String path = null;
  // resolved path
  private String filename = null;

  private String absolute = null;
  // url.toString()
  private String abspath = null;
  // host + a path
  private String relative = null;
  // url relative to abspath

  private String mimetype = null;
  private String linktext = new String();
  private int inlinks = 0;
  private int linktype = UNKNOWN;
  private int docref = -1;
  private boolean scanned = false;
  private int responseCode = 0;
  public final static int UNKNOWN = -1;
  public final static int OTHER = 0;
  public final static int HTML = 1;
  public final static int IMG = 2;
  public final static int XML = 3;

  /**
   *  Creates new HyperLink
   */
  public HyperLink() {
  }

  public void setLog(PrintStream ps) {
    log = ps;
  }

  public void setAbsolute(String s) {
    // http://www.host.com/fish/chips/peas.html
    absolute = s;
  }

  public void setHost(String s) {
    host = stripEndSlashes(s);
    //  System.out.println("\nset host = "+host);
  }

  public void setPath(String s) {
    path = stripEndSlashes(s);
    //    System.out.println("set path = "+path);
  }

  public void setFilename(String s) {
    filename = stripEndSlashes(s);
    //  System.out.println("set filename = "+filename);
  }

  public void setRelative(String s) {
    // ../../chips/peas.html
    relative = stripEndSlashes(s);
    // System.out.println("set relative = "+relative);
  }

  public void setAbsolutePath(String s) {
    //  http://www.host.com/fish/chips
    abspath = stripEndSlashes(s);
    //   System.out.println("set abspath = *"+abspath+"*");
  }


  public void setType(String s) {
    mimetype = s;
  }


  public void setProtocol(String s) {
    protocol = s;
  }

  public void setInlinks(int i) {
    inlinks = i;
  }

  public void setDocRef(int i) {
    docref = i;
  }

  public void setScanned(boolean b) {
    scanned = b;
  }

  public void setResponseCode(int i) {
    responseCode = i;
  }


  public String getAbsolute() {
    // http://www.host.com/fish/chips/peas.html
    if (absolute != null) {
      return absolute;
    }
    if (!resolve()) {
      return null;
    }
    absolute = url.toString();
    return absolute;
  }


  public String getAbsolutePath() {
    //     http://www.host.com/fish/chips
    if (abspath != null) {
      return abspath;
    }
    if (!resolve()) {
      return null;
    }
    // String filename = getFilename();
    abspath = getProtocol() + "://"
       + getHost() + "/" + getPath();

    return abspath;
  }

  public String getHost() {
    if (host != null) {
      return host;
    }

    if (!resolve()) {
      return null;
    }
    String host;
    try {
      host = url.getHost();

    } catch (Exception e) {
      return null;
    }
    return host;
  }

  public String getFilename() {
    if (filename != null) {
      return filename;
    }
    if (!resolve()) {
      return null;
    }

    try {
      filename = url.getFile();
    } catch (Exception e) {
      log.println(e + " in hyperlink : getFilename()");
      return null;
    }
    int dirend = filename.lastIndexOf("/") > filename.lastIndexOf("\\") ? filename.lastIndexOf("/") : filename.lastIndexOf("\\");
    filename = filename.substring(dirend + 1, filename.length());
    //  if(dirend != 0)
    return filename;
  }

  public String getPath() {
    //  /fish/chips
    if (path != null) {
      return path;
    }
    if (!resolve()) {
      return null;
    }
    String urlfile;

    try {
      urlfile = url.getFile();

    } catch (Exception e) {
      return null;
    }

    int dirend = urlfile.lastIndexOf("/") > urlfile.lastIndexOf("\\") ? urlfile.lastIndexOf("/") : urlfile.lastIndexOf("\\");
    if (dirend > 0) {
      path = urlfile.substring(0, dirend);
      path = stripEndSlashes(path);
    } else {
      path = "";
    }
    //    System.out.println("strippper "+path);
    // + "/";
    return path;
  }


  public URL getURL() {
    if (url != null) {
      return url;
    }
    if (!resolve()) {
      return null;
    }
    return url;
  }

  public String getType() {
    return mimetype;
  }

  public String getProtocol() {
    if (protocol != null) {
      return protocol;
    }
    if (!resolve()) {
      return null;
    }
    //   System.out.println("URL = "+url);
    try {
      protocol = url.getProtocol();
    } catch (Exception e) {
      log.println(e + " in hyperlink : getProtocol()");
      return null;
    }
    return protocol;
  }

  public String getLinkText() {
    return linktext;
  }

  public int getInlinks() {
    return inlinks;
  }

  public int getDocRef() {
    return docref;
  }

  public boolean isScanned() {
    return scanned;
  }

  public int getResponseCode() {
    return responseCode;
  }

  public boolean matches(HyperLink test) {
    /*
     * if (!getFilename().equals(test.getFilename())) return false;
     * if (!getHost().equals(test.getHost())) {
     * return false;
     * }
     * log.println("partial " + toString() + "  " + test);
     * if (!getPath().equals(test.getPath())) {
     * return false;
     * }
     * log.println("full");
     * log.println("ABS "+getAbsolute()+"  "+test.getAbsolute());
     */
    boolean match;
    try {
      match = getFilename().equals(test.getFilename()) && getHost().equals(test.getHost()) && getPath().equals(test.getPath());
    } catch (Exception e) {
      match = false;
    }
    return match;
  }

  public void appendLinkText(String s) {
    linktext += " " + s;
  }

  public String toString() {
    if (!resolve()) {
      return "Incomplete URL";
    }
    return url.toString() + " " + isScanned() + "\n";
  }

  private boolean resolve() {
    if (url != null) {
      return true;
    }
    if (absolute != null) {
      try {
        url = new URL(absolute);
      } catch (Exception e) {
        log.println("absolute -> url " + e);

        return false;
      }
      return true;
    }

    if ((host != null) && (path != null) && (filename != null)) {
      try {
        url = new URL(protocol + "://" + host + "/" + path + "/" + filename);
      } catch (Exception e) {
        log.println("protocol+host+path+filename -> url " + e);
      }
      return true;
    }

    if ((abspath != null) && (relative != null)) {
      if (!relative.startsWith("/")) {
        relative = "/" + relative;
      }
      if (!abspath.endsWith("/")) {
        abspath += "/";
      }
      //  System.out.println("abs = "+abspath+"   rel = "+relative);
      String combined = abspath + relative;
      // "/"+
      //    System.out.println("combined in HL "+combined);
      combined = combined.replace('\\', '/');
      StringTokenizer st = new StringTokenizer(combined, "/");
      ArrayList dirs = new ArrayList();
      int dotdot;
      while (st.hasMoreTokens()) {
        dirs.add(st.nextToken());
      }

      while (dirs.contains("..")) {
        dotdot = dirs.indexOf("..");
        //       System.out.println("dirs = "+dirs);
        dirs.remove(dotdot);

        if (dotdot > 0) {
          dirs.remove(dotdot - 1);
          // the one before
        }
      }
      combined = (String) dirs.get(0) + "/";
      // protocol:
      for (int i = 1; i < dirs.size(); i++) {
        combined += "/" + (String) dirs.get(i);
      }
      //       System.out.println("combined = "+combined);
      try {
        url = new URL(combined);
      } catch (Exception e) {
        log.println("abspath+relative -> url " + e);
        return false;
      }
      abspath = url.getProtocol() + "://"
         + url.getHost() + "/" + getPath();
      //    System.out.println("url = "+url);
      return true;
    }
    /*
     * if((host != null) && (path != null) && (relative != null)){
     * try{
     * url = new URL(host+"/"+path+"/"+relative);
     * }catch(Exception e){
     * log.println("host+path+relative -> url "+e);
     * }
     * return true;
     * }
     */
    return false;
  }

  private String stripEndSlashes(String s) {
    //   System.out.println("before strip *"+s+"*");
    if (s.length() == 0) {
      return "";
    }
    if ("/".equals(s)) {
      return "";
    }
    if (s.startsWith("/")) {
      s = s.substring(1);
    }
    int length = s.length();
    if (length == 0) {
      return "";
    }
    if ("/".equals(s)) {
      return "";
    }
    if (s.endsWith("/")) {
      s = s.substring(0, length - 1);
    }
    if (" ".equals(s)) {
      s = "";
    }
    //       System.out.println("after strip *"+s+"*");
    return s;
  }
}
