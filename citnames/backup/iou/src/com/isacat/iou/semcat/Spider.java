
package com.isacat.iou.semcat;

import java.io.*;
import java.net.*;
import javax.swing.text.html.HTMLEditorKit.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.isacat.iou.io.*;
import com.isacat.iou.filter.*;
import com.isacat.iou.parse.*;
import com.isacat.iou.html.*;
import com.isacat.iou.parse.rdf.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    20 April 2001
 */
public class Spider {

  public Spider() {

    Document filteredDoc = new FilteredDocument();
    filteredDoc.setFilter(new Filter());
    filteredDoc.setAddress("http://localhost/index.htm");
    filteredDoc.read(true);
    filteredDoc.resolveFields();
    System.out.println(((FilteredDocument) filteredDoc).getCorewords());
    System.out.println(((FilteredDocument) filteredDoc).getLinks());
  }


  public static void main(String[] args) {
    new Spider();
  }
}

