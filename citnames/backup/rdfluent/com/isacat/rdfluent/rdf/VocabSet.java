/*
 * Vocabulary.java
 *
 * Created on 24 July 2001, 09:20
 */

package com.isacat.rdfluent.rdf;

import java.util.*;
import com.isacat.rdfluent.util.*;
import com.isacat.rdfluent.things.*;
/**
 * @author     danny
 * @created    24 July 2001
 * @version
 */
public class VocabSet  {
  String value = new String();

//  private Vocabulary[] vocabs;
  //private String[] vocabNames;
 // private String[] vocabURIs;
  private int nVocabs;
  private int type;
  private Thing thing;
  private String uri;
    private Vocabulary[] vocabs;
 // private Vocabulary[] resourcesSets;
 // private Vocabulary[] propertiesSets;

  /**
   *  Creates new Vocabulary
   */
  public VocabSet() {
    load();
  }

  public String[] getVocabNames() {
      String[] names = new String[nVocabs];
      for(int i=0;i<nVocabs;i++){
          names[i] = vocabs[i].getName();
      }
    return names;
  }
  
  public String[] getVocabURIs() {
      String[] uris = new String[nVocabs];
      for(int i=0;i<nVocabs;i++){
          uris[i] = vocabs[i].getName();
      }
    return uris;
  }
  
  
  public Vector getResourcesNameList(int i) {
      
    return vocabs[i].getResourcesNameList();
  }

  public Vector getPropertiesNameList(int i) {
    return vocabs[i].getPropertiesNameList();
  }

  public void load() {
    ConfigLoader configLoader = new ConfigLoader();
    configLoader.load("vocabs.xml");
    Properties vocabList = configLoader.getProperties("Vocabularies");
    Properties uris = configLoader.getProperties("URIs");
    nVocabs = vocabList.size();
//    vocabURIs = new String[nVocabs];
//    vocabNames = new String[nVocabs];
    vocabs = new Vocabulary[nVocabs];
    
  //  resourcesSets = new Vocabulary[nVocabs];
  //  propertiesSets = new Vocabulary[nVocabs];

    Properties vocabulary;
    // Vector wordList;
    String key;
    String value;
    String name;
    String uri;
    int i = -1;

    for (Iterator vocabIt = vocabList.keySet().iterator(); vocabIt.hasNext(); ) {
      i++;
 //     vocabNames[i] = (String) vocabIt.next();
  //    vocabURIs[i] = (String) uris.get(vocabNames[i]);
      vocabs[i] = new Vocabulary();
      name = (String) vocabIt.next();
      uri = (String) uris.get(name);
      vocabs[i].setName(name);
      vocabs[i].setURI(uri);

      vocabulary = configLoader.getProperties(name);


   //   resourcesLists[i] = new Vector();
  //    propertiesLists[i] = new Vector();
      for (Iterator j = vocabulary.keySet().iterator(); j.hasNext(); ) {
        key = j.next().toString();
        value = vocabulary.get(key).toString();
        
        if (value.toString().equals("Resource")) {
         // resourcesLists[i].add(key);
            vocabs[i].createResource(key);
        } else {
        //  propertiesLists[i].add(key);
            vocabs[i].createProperty(key);
        }
      }
    }

  }
}
