/*
 * DMOZCleaner.java
 *
 * Created on 27 March 2001, 19:20
 */

package com.isacat.iou.util;

import java.io.*;

/**
 * @author     danny
 * @created    27 March 2001
 * @version
 */
public class DMOZCleaner {
  static String[] original = {"<RDF ", "xmlns=\"http://directory.mozilla.org/rdf\"", "</RDF>", ":id="};
  static String[] replacement = {"<r:RDF ", "", "</r:RDF>", ":ID="};
  static int nSwaps = original.length;
  static StringBuffer buffer = new StringBuffer();
  static int index;
  static int i;


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
        if (++counter % 100 == 0) {
          if (counter % 1000 == 0) {
            System.out.println(counter);
          }

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
