/*
 * SimpleTextDialog.java
 *
 * Created on 19 May 2001, 21:14
 */

package old;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.isacat.rdfluent.things.*;
/**
 * @author     danny
 * @created    26 May 2001
 */
public class SimpleTextDialog extends JDialog implements ActionListener {

  Thing thing;

  private JPanel jPanelMain;
  private JButton jButtonOk;

  private JTextField jTextFieldEntry;
  private JLabel jLabelTop;

  /**
   *  Creates new form SimpleTextDialog
   *
   * @param  parent  Description of Parameter
   * @param  modal   Description of Parameter
   */
  public SimpleTextDialog(Frame parent, boolean modal) {
    super(parent, modal);
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
      thing.setText(jTextFieldEntry.getText());
      setVisible(false);
      System.out.println("CLOSE");
    }
  }

  private void initComponents() {
    jPanelMain = new JPanel();
    jButtonOk = new JButton("OK");
    jButtonOk.addActionListener(this);
    jTextFieldEntry = new JTextField();
    jLabelTop = new JLabel();

    addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent evt) {
          closeDialog();
        }
      });

    jPanelMain.add(jButtonOk);

    getContentPane().add(jPanelMain, BorderLayout.SOUTH);
    getContentPane().add(jTextFieldEntry, BorderLayout.CENTER);

    jLabelTop.setText("Enter Text");
    jLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
    getContentPane().add(jLabelTop, BorderLayout.NORTH);

    pack();
  }

  /**
   *  Closes the dialog
   */
  private void closeDialog() {
    // thing.setText(jTextFieldEntry.getText());
    //  setVisible(false);
    //  dispose();
  }

  /**
   * @param  args  the command line arguments
   */
  public static void main(String args[]) {
    new SimpleTextDialog(new JFrame(), true).show();
  }

}

