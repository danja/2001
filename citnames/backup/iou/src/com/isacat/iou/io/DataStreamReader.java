/*
 * DataReader.java
 *
 * Created on 29 March 2001, 13:54
 */

package com.isacat.iou.io;

// import java.util.*;
import java.io.*;
// import java.net.*;
import com.isacat.iou.parse.*;
/**
 * @author     danny
 * @created    29 March 2001
 * @version
 */
public interface DataStreamReader {

  public void setMaxLength(int length);

  public void setParser(DataParser p);

  public String getWarning();

  public long getDate();

  public char[] getContentAsChars();

  public String getContentAsString();

  public boolean read(InputStream stream);

  public boolean read(InputStream stream, boolean readRaw, boolean parse);

}

