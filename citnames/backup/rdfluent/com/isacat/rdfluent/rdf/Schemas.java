/*
 * Schemas.java
 *
 * Created on 25 May 2001, 20:13
 */

package com.isacat.rdfluent.rdf;

import java.io.*;

import com.hp.hpl.mesa.rdf.jena.mem.*;
import com.hp.hpl.mesa.rdf.jena.model.*;
/**
 * @author     danny
 * @created    26 May 2001
 * @version
 */
public class Schemas {

  private static Model rdfsModel;

  /**
   *  Creates new Schemas
   */
  public Schemas() {
    rdfsModel = new ModelMem();
    String filename = "rdf-schema.rdf";
    try {
      rdfsModel.read(new FileReader(filename), "");
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      for (StmtIterator i = rdfsModel.listStatements(); i.hasNext(); ) {
        Resource resource = null;
        RDFNode node = null;
        node = i.next();
        // System.out.println(i.next().getClass());
        //  if(node instanceof com.hp.hpl.mesa.rdf.jena.model.Resource)
        //  try{
        //   resource = (Resource)node; // i.next();
        // }catch(Exception e){}
        System.out.println(i.next().getObject());
        // if(resource !=null)  System.out.println(resource.getProperty(rdfsModel.createProperty()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
