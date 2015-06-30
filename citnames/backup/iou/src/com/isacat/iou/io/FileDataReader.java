/*
 * HTTPReader.java
 *
 * Created on 24 March 2001, 18:46
 */

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
public class FileDataReader extends DataReader {

  public FileDataReader() {

  }


  public boolean read(File file) {
    return read(file, true, false);
  }

  public boolean read(File file, boolean readRaw, boolean parse) {

    setWarning("Unknown Error");
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
    } catch (IOException ioe) {
      ioe.printStackTrace();
      setWarning(ioe.getMessage());
      log.println(ioe);
      return false;
    }
    super.readContent(fis, readRaw, parse);

    return true;
  }

}
