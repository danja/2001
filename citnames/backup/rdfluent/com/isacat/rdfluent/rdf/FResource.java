/*
 * FResource.java
 *
 * Created on 16 May 2001, 11:20
 */

package com.isacat.rdfluent.rdf;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;

import com.isacat.rdfluent.ui.*;
import com.isacat.rdfluent.things.*;
import com.hp.hpl.mesa.rdf.jena.model.*;
/**
 * @author     danny
 * @created    16 May 2001
 * @version
 */
public class FResource extends FNode {
  private Model model;
  private Resource resource;
  /**
   *  Creates new FResource
   */
  private static PrintStream log = System.out;

  public FResource(Model model) {
    setType(VisualNode.ELLIPSE);
    this.model = model;
    //   super.setType(VisualNode.ELLIPSE);
    super.setValue("Resource");
    try {
      resource = model.createResource();
      setRDFNode((RDFNode) resource);
    } catch (Exception e) {
      log.println("Error creating resource");
      e.printStackTrace();

    }
    log.println("Resource created");
    log.println("model in resource " + model);
  }

  public void setURI(String uri) {
    try {
      resource = model.createResource(uri);
      ////////////////////////
      setRDFNode((RDFNode) resource);
    } catch (Exception e) {
      log.println("Error creating resource with uri");
      e.printStackTrace();

    }
  }


  public void update() {
    /*
     * try {
     * resource = model.createResource(getText());
     * setRDFNode((RDFNode) resource);
     * } catch (Exception e) {
     * log.println("Error creating resource");
     * e.printStackTrace();
     * }
     * if (getSheet() != null) {
     * getSheet().log(resource.toString());
     * }
     */
  }

}
