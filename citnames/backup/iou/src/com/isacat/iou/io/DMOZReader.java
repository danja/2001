/*
 * DMOZCleaner.java
 *
 * Created on 27 March 2001, 19:20
 */

package com.isacat.iou.io;

import java.io.*;

import com.isacat.iou.parse.*;

/**
 * @author     danny
 * @created    27 March 2001
 * @version
 */
public class DMOZReader implements DataStreamReader {
  static String[] original = {"<RDF ", "xmlns=\"http://directory.mozilla.org/rdf\"", "</RDF>", ":id="};
  static String[] replacement = {"<r:RDF ", "", "</r:RDF>", ":ID="};
  static int nSwaps = original.length;
  static StringBuffer buffer = new StringBuffer();
  static int index;
  static int i;


  public void setMaxLength(int maxLength) {
  }

  public void setParser(DataParser p) {
  }

  public String getContentAsString() {
    return "";
  }

  public char[] getContentAsChars() {
    char[] chars = {'a'};
    return chars;
  }

  public long getDate() {
    return 1L;
  }

  public String getWarning() {
    return "warning";
  }

  public boolean read(InputStream stream) {
    return true;
  }

  public boolean read(InputStream stream, boolean readRaw, boolean parse) {
    return true;
  }

  public static String filter(String input) {
    buffer.delete(0, buffer.length());
    for (i = 0; i < nSwaps; i++) {
      if ((index = input.indexOf(original[i])) != -1) {
        buffer.append(input.substring(0, index));
        buffer.append(replacement[i]);
        buffer.append(input.substring(index + original[i].length(), input.length()));
        return buffer.toString();
      }
    }
    return input;
  }

  public static void main(String filenames[]) {
    if (filenames.length != 2) {
      System.out.println("java DMOZCleaner [source file] [destination file]");
      System.exit(1);
    }
    String line;
    BufferedReader reader = null;
    BufferedWriter writer = null;
    try {
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(filenames[0])));
      writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filenames[1])));
      int counter = 0;
      while (true) {
        line = reader.readLine();

        if (line == null) {
          break;
        }
        //   System.out.print(".");
        line = filter(line);
        writer.write(line + "\n");
        line = null;
        writer.flush();
        if (++counter % 1000 == 0) {
          System.out.println(counter);

          try {
            Thread.sleep(10);
          } catch (Exception e) {
            e.printStackTrace();
          }
          System.gc();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      reader.close();
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
