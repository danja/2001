/*
 * VocabLoader.java
 *
 * Created on 06 June 2001, 17:38
 */

package com.isacat.rdfluent.ui.dialogs;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import com.isacat.rdfluent.util.*;
import com.isacat.rdfluent.things.*;  
import com.isacat.rdfluent.ui.*;
import com.isacat.rdfluent.ui.components.*;
/**
 * @author     danny
 * @created    08 June 2001
 * @version
 */ 
public class TermChooser extends JPanel implements ActionListener {
  String value = new String();
  private VocabComboBox[] vocabComboBoxes;
  private JLabel[] jVocabLabels;
  private JPanel jPanelValue = null;
  private JTextField jTextFieldSelectedValue = new JTextField(20);
  private int nVocabs;
//  private int type;
  private Thing thing;
  //private String uri;

  /**
   *  Creates new VocabLoader
   */
  public TermChooser() {
    //initPanel();

  }

  public void setValue(String value) {
    this.value = value;
    Runnable setText =
      new Runnable() {
        public void run() {
          jTextFieldSelectedValue.setText(getValue());
        }
      };

    SwingUtilities.invokeLater(setText);

  }

  public void setTarget(Thing thing) {
    this.thing = thing;

  }

  public String getValue() {
  //  return value;
   return   jTextFieldSelectedValue.getText();
  }


  public void buildPanel(int type) {
      add(jTextFieldSelectedValue);
          jPanelValue = new JPanel();
    jPanelValue.setLayout(new BoxLayout(jPanelValue, BoxLayout.Y_AXIS));
      if(type == VisualThing.RECTANGLE){
            buildTextPanel();
      }else{
          buildComboPanel(type);
      }
          add(jPanelValue);
  }
  
  public void buildTextPanel(){
  }
  
  public void buildComboPanel(int type){
//    Vector choices = new Vector();

    int nVocabs = Fluent.vocabs.getVocabNames().length;
    vocabComboBoxes = new VocabComboBox[nVocabs];

    System.out.println("nVocabs = " + nVocabs);
    jVocabLabels = new JLabel[nVocabs];
    for (int i = 0; i < nVocabs; i++) {
            vocabComboBoxes[i] = new VocabComboBox();

if(type == VisualThing.ELLIPSE){

          vocabComboBoxes[i].setItems((Fluent.vocabs.getResourcesNameList(i)));
          vocabComboBoxes[i].setType(VisualThing.ELLIPSE);
} else {
          vocabComboBoxes[i].setItems((Fluent.vocabs.getPropertiesNameList(i)));
                    vocabComboBoxes[i].setType(VisualThing.ARROW);
      }

  //    vocabComboBoxes[i] = new VocabComboBox(choices);

      vocabComboBoxes[i].addActionListener(this);

      jVocabLabels[i] = new JLabel(Fluent.vocabs.getVocabNames()[i]);
      jPanelValue.add(jVocabLabels[i]);
      System.out.println("label " + i + " " + jVocabLabels[i].getText());

      jPanelValue.add(vocabComboBoxes[i]);
    }

  }

  public void actionPerformed(ActionEvent actionEvent) {
    System.out.println("source= " + actionEvent.getSource());
    VocabComboBox selectedComboBox = (VocabComboBox) actionEvent.getSource();
    int type = selectedComboBox.getType();
    value = (String) (selectedComboBox).getSelectedItem();
    

    jTextFieldSelectedValue.setText(value);
    //    System.out.println("SELECTED = "+selected);
    if (type == VisualThing.ARROW) {

      System.out.println("nVocabs = " + nVocabs);
      for (int i = 0; i < nVocabs; i++) {

        System.out.println("i = " + i);
       
          if (vocabComboBoxes[i].equals((VocabComboBox) actionEvent.getSource())) {
     //     uri = Fluent.vocabs.getVocabURIs()[i];
              System.out.println("found vocab "+i);
              System.out.println("nme = "+Fluent.vocabs.getVocabNames()[i]);
          }
         
      }

      if (type == VisualThing.ELLIPSE) {

        for (int i = 0; i < nVocabs; i++) {
          /*
           * if (jComboBoxResources[i].equals((VocabComboBox) actionEvent.getSource())) {
           * uri = vocabURIs[i];
           * uri = Fluent.vocabs.getVocabURIs()[i];
           * }
           */
        }
      }
    }
  }


}
