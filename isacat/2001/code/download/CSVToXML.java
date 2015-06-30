/*
 * CSVToXML.java
 *
 * @version 1.0
 *
 *
 * @author     Danny Ayers
 * @created    23 February 2001
 */

import java.util.*;
import java.io.*;

/**
 *  Converts CSV data file into XML
 */
public class CSVToXML {

  public  final static String PROLOG = "<?xml version=\"1.0\"?>";
  public  final static String OPEN_START = "<";
  public  final static String OPEN_END = "</";
  public  final static String CLOSE = ">";
  public  final static String NEWLINE = "\n";
  public  final static String INDENT = "\t";
  
  private String delimiter;
  private int nfields;
  private int nrows;
  private String header;
  private String rowname;
  private String rootname;
  private String[] fieldnames;


  public static void main(String filenames[]) {
    if (filenames.length != 3) {
      System.out.println("java CSVToXML [properties file] [source] [destination]");
      System.exit(1);
    }
    new CSVToXML(filenames);
  }
  
  public CSVToXML(String filenames[]) {
    Properties props = loadProperties(filenames[0]);
        setParameters(props);
    String source = loadString(filenames[1]);
    String outFile = filenames[2];

    ArrayList items = stringToArrayList(source, delimiter);
    nrows = items.size() / nfields;
    String output = convert(items);
    saveString(outFile, output);
  }

  public Properties loadProperties(String filename) {
    Properties props = new Properties();
    FileInputStream instream;;
    BufferedInputStream buffer;
    
    try {
      instream = new FileInputStream(filename);
      buffer = new BufferedInputStream(instream);
      props.load(buffer);
      buffer.close();
      instream.close(); 
    } catch (Exception e) {
      System.out.println(e);
    }    
    return props;
  }
  
  public void setParameters(Properties props) {
    delimiter = props.getProperty("delimiter");
    rootname = props.getProperty("rootname");
    rowname = props.getProperty("recordname");
    
    String prolog = props.getProperty("prolog");
    String dtd = props.getProperty("dtd");
    String comment = props.getProperty("comment");

    header = PROLOG + NEWLINE
       + "<DOCTYPE " + rootname + " SYSTEM " + dtd + ">" + NEWLINE + NEWLINE
       + "<!-- " + comment + " -->" + NEWLINE;

    nfields = Integer.parseInt(props.getProperty("fields"));

    fieldnames = new String[nfields];
    String fieldref;

    for (int field = 0; field < nfields; field++) {
      fieldref = "field" + field;
      fieldnames[field] = props.getProperty(fieldref);
    }


  }
  
  public String loadString(String filename) {
    StringBuffer text = new StringBuffer();
    FileInputStream instream;
    BufferedInputStream buffer;
    int readint;

    try {
      instream = new FileInputStream(filename);
      buffer = new BufferedInputStream(instream);
      while ((readint = buffer.read()) != -1) {
        text.append( (char) readint);
      }
      buffer.close();
      instream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
   return text.toString();
  }
    


  public ArrayList stringToArrayList(String source, String delim) {
    ArrayList alist = new ArrayList();
    String word;
    StringTokenizer st = new StringTokenizer(source, delim);
    while (st.hasMoreTokens()) {
      word = st.nextToken();
      word = stripSpaces(word);
      alist.add(word);
    }
    return alist;
  }

  public String stripSpaces(String s) {
    while (Character.isWhitespace(s.charAt(0))) {
      s = s.substring(1, s.length());
    }
    return s;
  }
    
  public String convert(ArrayList items) {
    StringBuffer output = new StringBuffer();
    output.append(header);
    output.append(NEWLINE);
    output.append(OPEN_START + rootname + CLOSE);
    output.append(NEWLINE);

    Iterator values = items.iterator();
    String value;
    for (int rowcount = 0; rowcount < nrows; rowcount++) {
      output.append(INDENT + OPEN_START + rowname + CLOSE + NEWLINE);

      for (int field = 0; field < nfields; field++) {
        output.append(INDENT + INDENT + OPEN_START + fieldnames[field] + CLOSE);
        value = (String) values.next();
        output.append(value);
        output.append(OPEN_END + fieldnames[field] + CLOSE + NEWLINE);
      }

      output.append(INDENT + OPEN_END + rowname + CLOSE);
      output.append(NEWLINE);
    }
    output.append(OPEN_END + rootname + CLOSE);
    output.append(NEWLINE);
    return output.toString();
  }



  public void saveString(String filename, String string) {

    FileOutputStream outstream;
    BufferedOutputStream buffer;
    int length;
    byte[] bytes;
    try {
      outstream = new FileOutputStream(filename);
      buffer = new BufferedOutputStream(outstream);
      bytes = string.getBytes();
      length = string.length();
      for (int i = 0; i < length; i++) {
        buffer.write(bytes[i]);
      }
      buffer.close();
      outstream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
