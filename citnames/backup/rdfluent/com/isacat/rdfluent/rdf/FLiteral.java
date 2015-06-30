/*
 * FLiteral.java
 *
 * Created on 16 May 2001, 11:22
 */

package com.isacat.rdfluent.rdf;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

import com.isacat.rdfluent.things.*;
import com.isacat.rdfluent.ui.*;
import com.hp.hpl.mesa.rdf.jena.model.*;
/**
 * @author     danny
 * @created    16 May 2001
 * @version
 */
public class FLiteral extends FNode {
  private Model model;
  private RDFNode rdfNode;
  private Literal literal;

  /**
   *  Creates new FLiteral
   */
  private static PrintStream log = System.out;

  public FLiteral(Model model) {
    setType(VisualNode.RECTANGLE);
    this.model = model;
    //  super.setType(VisualNode.RECTANGLE);
    super.setValue("Literal");
    update();
    log.println("Literal created");
    log.println("model in literal " + model);

  }

  public void update() {
    setValue();
  }

  public void setValue(){
          try {
      literal = model.createLiteral(getValue());
      setRDFNode((RDFNode) literal);
      if (getSheet() != null) {
        getSheet().log(literal.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
