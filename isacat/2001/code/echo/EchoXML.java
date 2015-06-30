
import java.io.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.xml.sax.helpers.*;


public class EchoXML extends DefaultHandler {

  public  final static String PROLOG = "<?xml version=\"1.0\"?>";
  
  StringBuffer output = new StringBuffer(); 

  public static void main(String args[]) {
    new EchoXML(args);
  }

  public EchoXML(String args[]) {
    output.append(PROLOG + "\n"
       + "<!DOCTYPE foo SYSTEM \"foo.dtd\">\n");
    parse(args[0]);


    saveString("output.xml", output.toString());
  }


  public void parse(String uri) {
    try {
       SAXParserFactory saxfactory = SAXParserFactory.newInstance();
       SAXParser saxparser = saxfactory.newSAXParser();
      XMLReader xmlreader = saxparser.getXMLReader();
      // XMLReader xmlreader = (XMLReader)saxparser.getParser();
      
      xmlreader.setContentHandler(this);
      xmlreader.setErrorHandler(this);
      xmlreader.setFeature("http://xml.org/sax/features/validation", true);
      xmlreader.parse(uri);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  public void startElement(String uri, String local, String qname, Attributes attrs) {
     int nattrs = attrs.getLength();

     output.append("<");   
      output.append(local);
 
     for(int i=0;i<nattrs;i++){
      output.append(" ");      
      output.append(attrs.getLocalName(i));
      output.append("=\"");
      output.append(attrs.getValue(i));
      output.append("\"");
    }
     output.append(">");   
  }

  public void characters(char ch[], int start, int length) {   // not fixed for multiple calls
    String content = new String(ch, start, length);
    output.append(content);
  }


  public void endElement(String uri, String local, String qname) {
      output.append("</");
      output.append(local);
      output.append(">");   
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

