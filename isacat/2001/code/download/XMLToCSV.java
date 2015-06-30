
import java.io.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.xml.sax.helpers.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    24 February 2001
 */
public class XMLToCSV extends DefaultHandler {

  public final static String NEWLINE = "\n";
  
  private StringBuffer elementcontent;
  private StringBuffer csv;
  private int nfields;
  private int fieldcounter;
  private String delim;
  private boolean inleaf;

  public static void main(String args[]) {
    if (args.length != 4) {
      System.out.println("java XMLToCSV [source] [destination] [delimiter] [number of fields]");
      System.exit(1);
    }
    new XMLToCSV(args);
  }

  public XMLToCSV(String args[]) {
    csv = new StringBuffer();
    nfields = Integer.parseInt(args[3]);
    delim = args[2];
    parse(args[0]);
    String output = csv.substring(0, csv.length() - delim.length() - 1);
    saveString(args[1], output);
  }


  public void parse(String uri) {
    try {
      // Create a JAXP SAXParser
       SAXParserFactory saxfactory = SAXParserFactory.newInstance();
       SAXParser saxparser = saxfactory.newSAXParser();
      // XMLReader xmlreader = saxparser.getXMLReader();
      XMLReader xmlreader = (XMLReader)saxparser.getParser();
      
      xmlreader.setContentHandler(this);
      xmlreader.setErrorHandler(this);
      xmlreader.setFeature("http://xml.org/sax/features/validation", true);
      xmlreader.parse(uri);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  // DocumentHandler methods

  public void startDocument() {
    fieldcounter = 0;
    System.out.println("start document");
  }

  public void startElement(String uri, String local, String qname, Attributes attrs) {
    elementcontent = new StringBuffer();
    inleaf = true;
  }

  public void characters(char ch[], int start, int length) {
    elementcontent.append(ch, start, length);
  }


  public void endElement(String uri, String local, String qname) {
    if (inleaf) {
      csv.append(elementcontent + delim);
      if (++fieldcounter % nfields == 0) {
        csv.append(NEWLINE);
      }
    }
    inleaf = false;
  }
  

  public void endDocument() {
    System.out.println("end document");
    
  }

	public void warning(SAXParseException e) {
	   System.out.println("Warning : "+e);
	}

	public void error(SAXParseException e) {
	   System.out.println("\nError : "+e);
	}

	public void fatalError(SAXParseException e) {
	   System.out.println("\n\nFatal error : "+e);
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

