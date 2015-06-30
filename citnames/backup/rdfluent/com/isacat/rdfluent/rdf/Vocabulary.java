/*
 * Vocabulary.java
 *
 * Created on 08 August 2001, 12:01
 */

package com.isacat.rdfluent.rdf;

import java.util.*;
import com.hp.hpl.mesa.rdf.jena.model.*;
import com.hp.hpl.mesa.rdf.jena.common.*;
/**
 *
 * @author  danny
 * @version 
 */
public class Vocabulary extends HashMap {

    private String name = new String("vocab_name_not_set");
    private String uri = new String("vocab_uri_not_set");
    private Vector resourcesNameList = new Vector();
    private Vector propertiesNameList = new Vector();
    /** Creates new Vocabulary */
    public Vocabulary() {
    }

    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setURI(String uri){
        this.uri = uri;
    }
    
    public String getURI(){
        return uri;
    }
    
    public void createProperty(String name){
        Property property = null;
        try{
            property = new PropertyImpl(uri, name);
        }catch(Exception e){
            e.printStackTrace();
        }
        put(name, property);
        propertiesNameList.add(name);
    }
    
    public Property getProperty(String name){
        return (Property)get(name);
    }
    
    public void createResource(String name){
        Resource resource = new ResourceImpl(uri+name);
        put(name, resource);
        resourcesNameList.add(name);
    }
    
    public Resource getResource(String name){
        return (Resource)get(name);
    }
    
    public Vector getResourcesNameList(){
        return resourcesNameList;
    }
    
    public Vector getPropertiesNameList(){
        return propertiesNameList;
    }
}
