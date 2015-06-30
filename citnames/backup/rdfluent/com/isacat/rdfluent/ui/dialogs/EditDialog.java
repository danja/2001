/*
 * SimpleTextDialog.java
 *
 * Created on 19 May 2001, 21:14
 */

package com.isacat.rdfluent.ui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.isacat.rdfluent.ui.*;
import com.isacat.rdfluent.things.*;
/**
 * @author     danny
 * @created    26 May 2001
 */
public class EditDialog extends JDialog implements ActionListener {

  Thing thing;

  private JPanel jPanelMain;
  private JButton jButtonOk;

  private JTextField jTextFieldEntry;
  private JLabel jLabelTop;

  private JPanel resourcesPanel;
  private JPanel propertiesPanel;
  private TermChooser termChooser = null;

  private Sheet sheet;

  /**
   *  Creates new form SimpleTextDialog
   *
   * @param  parent  Description of Parameter
   * @param  modal   Description of Parameter
   * @param  sheet   Description of Parameter
   */
  public EditDialog(Sheet sheet, Frame parent, boolean modal) {
    super(parent, modal);
    this.sheet = sheet;

    initComponents();
  }

  public void setLabel(String label) {
    jLabelTop.setText(label);
  }

  public void setTarget(Thing thing) {
    this.thing = thing;
  }

  public String getText() {
    return jTextFieldEntry.getText();
  }

  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getSource().equals(jButtonOk)) {

      System.out.println("thing = " + thing);
      System.out.println("termChooser = " + termChooser);
      System.out.println("termChooser.getValue() = " + termChooser.getValue());
      thing.setValue(termChooser.getValue());

      //  thing.setText(jTextFieldEntry.getText());
      setVisible(false);
      sheet.redraw();

      System.out.println("CLOSE");
    }
  }


  public void display(int type) {
    // termChooser.setValue
      termChooser = new TermChooser();
         termChooser.setTarget(thing);
    

    termChooser.buildPanel(type);
        getContentPane().add(termChooser, BorderLayout.CENTER);
    pack();
    setVisible(true);

  }

  private void initComponents() {
    jPanelMain = new JPanel();
    jButtonOk = new JButton("OK");
    jButtonOk.addActionListener(this);
    //  jTextFieldEntry = new JTextField();
    //jLabelTop = new JLabel();

    /*
     * addWindowListener(
     * new WindowAdapter() {
     * public void windowClosing(WindowEvent evt) {
     * closeDialog();
     * }
     * });
     */
    jPanelMain.add(jButtonOk);
    getContentPane().add(jPanelMain, BorderLayout.SOUTH);
 //   if (termChooser == null) {
//      termChooser = new TermChooser();
 //     termChooser.buildPanel(type);
//    }

    // termChooser.loadVocab();
    // termChooser.setType(VisualThing.ELLIPSE);
//    termChooser.setTarget(thing);
    
 //   getContentPane().add(termChooser, BorderLayout.CENTER);
 //   pack();
  }

  public static void main(String args[]) {
//   new NodeEditDialog(new JFrame(), true).show();
  }

}

