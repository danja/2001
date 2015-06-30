/*
 * FProperty.java
 *
 * Created on 16 May 2001, 11:21
 */

package com.isacat.rdfluent.rdf;

import java.io.*;

import com.isacat.rdfluent.things.*;
import com.isacat.rdfluent.ui.*;
import com.hp.hpl.mesa.rdf.jena.model.*;
/**
 * @author     danny
 * @created    16 May 2001
 * @version
 */
public class FProperty extends VisualArc {
  Model model;
  Property property;

  private static PrintStream log = System.out;

  /**
   *  Creates new FProperty
   *
   * @param  model  Description of Parameter
   */
  public FProperty(Model model) {
    this.model = model;
    super.setValue("http://temp/#hasProperty");

    try {
      //   property = model.createProperty("dummyURI");
      //    subject.addProperty(property, object);
    } catch (Exception e) {
      log.println("Error creating property");
      e.printStackTrace();
    }
  }

  public void link() {
    Resource subject = (Resource) ((FResource) getStartNode()).getRDFNode();
    RDFNode object = ((FNode) getEndNode()).getRDFNode();
    System.out.println("getEndNode() = "+getEndNode());

    try {
      System.out.println("Property = " + getURI() + "   " + getValue());
      property = model.createProperty(getValue());
      System.out.println("creating property link : "+getURI()+"  "+getValue());
      System.out.println("property, object : "+property+" , "+ object);
      subject.addProperty(property, object);
    } catch (Exception e) {
      log.println("Error linking property");
      e.printStackTrace();
    }
  }
}
