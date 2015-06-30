/*
 * DMOZCleaner.java
 *
 * Created on 27 March 2001, 19:20
 */

package com.isacat.iou.parse;

import java.io.*;

/**
 * @author     danny
 * @created    27 March 2001
 * @version
 */
public class DMOZFilter {

  private InputStream inputStream = null;
  private OutputStream outputStream = null;
  static String[] original = {"<RDF ", "xmlns=\"http://directory.mozilla.org/rdf\"", "</RDF>", ":id="};
  static String[] replacement = {"<r:RDF ", "", "</r:RDF>", ":ID="};
  static int nSwaps = original.length;
  static StringBuffer buffer = new StringBuffer();
  static int index;
  static int i;


  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public void setOutputStream(OutputStream outputStream) {
    this.outputStream = outputStream;
  }


  public String filterLine(String input) {
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


  public void filter() {
    BufferedReader reader = null;
    BufferedWriter writer = null;
    String line = new String();
    try {
      reader = new BufferedReader(new InputStreamReader(inputStream));
      writer = new BufferedWriter(new OutputStreamWriter(outputStream));
      int counter = 0;
      while (true) {
        line = reader.readLine();

        if (line == null) {
          break;
        }
        //   System.out.print(".");
        line = filterLine(line);
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
