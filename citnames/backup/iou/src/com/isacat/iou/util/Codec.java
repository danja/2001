/*
 * Codec.java
 *
 * Created on 25 March 2001, 00:33
 */

package com.isacat.iou.util;

import java.io.*;
import java.util.zip.*;
/**
 * @author     danny
 * @created    25 March 2001
 * @version
 */
public class Codec {

  public final static int DEFAULT_BUFFER = 4096;
  private static int buffersize;

  public Codec() {
    buffersize = DEFAULT_BUFFER;
  }

  public void setBufferSize(int i) {
    buffersize = i;
  }

  public static void main(String args[]) {
    // Test
    String input = "Drag and Drop is a direct manipulation gesture found in many Graphical User Interface systems that provides a mechanism to transfer information between two entities logically associated with presentation elements in the GUI.";
    byte[] source = input.getBytes();
    System.out.println(new String(source) + "\nSource length = " + source.length);
    byte[] bytes = compress(source);
    System.out.println("Compressed size = " + bytes.length);
    byte[] output = decompress(bytes);
    if ((new String(output)).equals(new String(input))) {
      System.out.println("OK!");
    } else {
      System.out.println("Test Failed.");
    }
    System.out.println(new String(source) + " : " + new String(output));

  }

  /**
   * @param  data  Description of Parameter
   * @return       Description of the Returned Value
   */

  static byte[] compress(byte data[]) {
    return compress(data, false);
  }

  static byte[] compress(byte data[], boolean escape) {
    ByteArrayOutputStream bytes = null;
    try {
      bytes = new ByteArrayOutputStream(buffersize);
      //4096
      DeflaterOutputStream out = new DeflaterOutputStream(bytes);
      // write the data
      if (!escape) {
        out.write(data, 0, data.length);
      } else {
        for (int i = 0; i < data.length; i++) {
          switch ((char) data[i]) {
            case 0:
              //  nul `\0'
              out.write((byte) '\\');
              out.write((byte) '0');
              break;
            case 92:
              //  `\\'
              out.write((byte) '\\');
              out.write((byte) '\\');
            case 39:
              //   `\''
              out.write((byte) '\\');
              out.write((byte) '\'');
            case 34:
              //  ` `\"'
              out.write((byte) '\\');
              out.write((byte) '\"');
            default:
              out.write(data[i]);
          }
        }
      }
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bytes.toByteArray();
  }

  /**
   *  Uncompress an array of bytes.
   *
   * @param  data  Description of Parameter
   * @return       Description of the Returned Value
   */
  static byte[] decompress(byte data[]) {
    ByteArrayOutputStream out = null;
    try {
      ByteArrayInputStream bytes = new ByteArrayInputStream(data);
      InflaterInputStream in = new InflaterInputStream(bytes);
      // read the data
      out = new ByteArrayOutputStream(buffersize);
      byte buf[] = new byte[1001];
      while (true) {
        try {
          int n = in.read(buf, 0, buf.length);
          if (n > 0) {
            out.write(buf, 0, n);
          } else {
            break;
          }
        } catch (EOFException e) {
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return out.toByteArray();
  }
}
/*
 * MySQL
 * If you want to insert binary data into a BLOB column, the following characters must be represented by escape sequences:
 * NUL
 * ASCII 0. You should represent this by `\0' (a backslash and an ASCII `0' character).
 * \
 * ASCII 92, backslash. Represent this by `\\'.
 * '
 * ASCII 39, single quote. Represent this by `\''.
 * "
 * ASCII 34, double quote. Represent this by `\"'.
 */
