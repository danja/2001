/*
 * FNode.java
 *
 * Created on 25 May 2001, 10:46
 */

package com.isacat.rdfluent.rdf;

import com.hp.hpl.mesa.rdf.jena.model.*;
import com.isacat.rdfluent.things.*;

/**
 * @author     danny
 * @created    26 May 2001
 * @version
 */
public class FNode extends VisualNode {
  protected int type;
  private RDFNode rdfNode;

//  public final static int FRESOURCE = 1;
//  public final static int FLITERAL = 2;

  /**
   *  Creates new FNode
   */
  public FNode() {
  }

  public void setRDFNode(RDFNode rdfNode) {
    this.rdfNode = rdfNode;
  }

  public RDFNode getRDFNode() {
    return rdfNode;
  }
}
