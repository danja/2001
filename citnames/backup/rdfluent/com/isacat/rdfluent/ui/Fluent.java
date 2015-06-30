/*
 * RDFGraph.java
 *
 * Created on 16 May 2001, 11:00
 */

package com.isacat.rdfluent.ui;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

import com.isacat.rdfluent.things.*;
import com.isacat.rdfluent.rdf.*;
import com.isacat.rdfluent.util.*;
import com.isacat.rdfluent.ui.dialogs.*;

/*
 * @author     danny
 * @create d    16 May 2001
 */
/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    18 May 2001
 */
public class Fluent extends JFrame implements ActionListener, ItemListener, InternalFrameListener {
  JFileChooser fileChooser;
  private int width = DEFAULT_WIDTH;
  private int height = DEFAULT_HEIGHT;

  private JPanel jPanelMain;
  private JScrollPane jScrollPane1;
  private JToolBar jToolBarTop;
  private JToolBar jToolBarLeft;
  private JToolBar jToolBarRight;

  private JPanel jPanelRdf;

// private JList jListRdf;
  private JButton jButtonSheet;
  private JButton jButtonResource;
  private JButton jButtonLiteral;
  private JButton jButtonProperty;
//  private JButton jButtonFont;
  private JButton jButtonToRdf;

  private JMenuBar jMenuBarMain;
  private JMenu jMenuFile;

  private JMenuItem jMenuItemNew;
  private JMenuItem jMenuItemOpen;
  private JMenuItem jMenuItemSave;
  private JMenuItem jMenuItemSaveAs;
  private JMenuItem jMenuItemExit;

  private JMenu jMenuSettings;
  private JMenuItem jMenuItemFont;

  private java.util.List sheets = new Vector();
  private Sheet currentSheet;
  private int sheetCount = 0;
  private Font font = new Font(null);
  private Properties properties = new Properties();
  // Variables declaration
  private FontChooser fontChooser = null;
  public static VocabSet vocabs = new VocabSet();
  // FontReceiver,
  private static PrintStream log = System.out;
  private final static int DEFAULT_WIDTH = 600;
  private final static int DEFAULT_HEIGHT = 400;


  /**
   *  Creates new form Fluent
   */
  public Fluent() {

// loadProperties();
    initComponents();

    // saveProperties();
    // Schemas schemas = new Schemas();
    // loadVocab();
  }

  public void setFont(Font font) {
    System.out.println("FONT = " + font);
    this.font = font;
    for (int i = 0; i < sheets.size(); i++) {
      ((Sheet) sheets.get(i)).setFont(font);
    }
  }

  public void itemStateChanged(java.awt.event.ItemEvent itemEvent) {
    // System.out.println(itemEvent.getItem());
  }

  public void actionPerformed(ActionEvent actionEvent) {
    log.println("click!");
    System.out.println("fluent COMMAND = " + actionEvent.getActionCommand());
    Object source = actionEvent.getSource();
    System.out.println("fluent SOURCE = " + source);
    String command = actionEvent.getActionCommand();
    VisualNode newNode = null;
    if (command.equals("Sheet") || source.equals(jButtonSheet)) {
      createSheet();
    }
    if (source.equals(jButtonResource)) {
      currentSheet.addThing(new FResource(currentSheet.getModel()));
    }
    if (source.equals(jButtonLiteral)) {
      currentSheet.addThing(new FLiteral(currentSheet.getModel()));
    }

    if (source.equals(jButtonToRdf)) {
      currentSheet.save();
    }
    // File Menu
    if (command.equals("New")) {
      createSheet();
    }
    if (command.equals("Open")) {
    }

    if (command.equals("Save")) {
      currentSheet.save();
    }

    if (command.equals("SaveAs...")) {

      currentSheet.saveAs();
    }
    if (command.equals("Exit")) {
      System.exit(0);
    }
    // Settings menu
    if (command.equals("Font")) {
      chooseFont();
    }
  }


  public void chooseFont() {
    if (fontChooser == null) {
      fontChooser = new FontChooser(this, true);
    }
    fontChooser.init();
    fontChooser.setSize(new Dimension(300, 150));
    fontChooser.setVisible(true);
  }

//  public void queryNamespace() {
// }

  public void loadProperties() {
    ConfigLoader configLoader = new ConfigLoader();
    configLoader.load("fluent.xml");
    properties = configLoader.getProperties("config");
    width = Integer.parseInt(properties.getProperty("window_width", "600"));
    height = Integer.parseInt(properties.getProperty("window_height", "400"));
  }

  public void internalFrameActivated(javax.swing.event.InternalFrameEvent internalFrameEvent) {
  }

  public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent internalFrameEvent) {
  }

  public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent internalFrameEvent) {
  }

  public void internalFrameOpened(javax.swing.event.InternalFrameEvent internalFrameEvent) {
  }

  public void internalFrameIconified(javax.swing.event.InternalFrameEvent internalFrameEvent) {
  }

  public void internalFrameClosing(javax.swing.event.InternalFrameEvent internalFrameEvent) {
  }

  public void internalFrameClosed(javax.swing.event.InternalFrameEvent internalFrameEvent) {
  }

  private Sheet getCurrentSheet() {
    return currentSheet;
  }


  private void createSheet() {

    currentSheet = new Sheet();
    currentSheet.setResizable(true);
    currentSheet.setTitle("Sheet " + (++sheetCount));
    currentSheet.setVisible(true);
    if (fontChooser != null) {
      font = fontChooser.getFont();
    }
    currentSheet.setFont(font);
    jPanelMain.add(currentSheet);

    // needed because of Swing thread non-safety
    Runnable maximise =
      new Runnable() {
        public void run() {
          try {
            getCurrentSheet().reshape(0, 0, 300, 200);
            getCurrentSheet().setMaximum(true);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      };
    SwingUtilities.invokeLater(maximise);
    sheets.add(currentSheet);
  }

  private void initComponents() {
    setTitle("Fluent");
    jPanelMain = new JPanel();
    //  jListRdf = new JList();

    //   jPanelRDF = new JPanel();
    jScrollPane1 = new JScrollPane();
    jToolBarTop = new JToolBar();
    jToolBarLeft = new JToolBar();
    jToolBarLeft.setOrientation(JToolBar.VERTICAL);
    jToolBarRight = new JToolBar();
    jToolBarRight.setOrientation(JToolBar.VERTICAL);
    ImageIcon sheetIcon = new ImageIcon("images/sheet.gif");
    jButtonSheet = new JButton(sheetIcon);
    // "Sheet",
    jButtonSheet.setName("Sheet");
    ImageIcon resourceIcon = new ImageIcon("images/resource.gif");
    jButtonResource = new JButton(resourceIcon);
    // "Resource",
    ImageIcon literalIcon = new ImageIcon("images/literal.gif");
    //  Dimension biggestButton = jButtonResource.getSize();
    jButtonLiteral = new JButton(literalIcon);
    // "Literal",
    ImageIcon propertyIcon = new ImageIcon("images/property.gif");
    //  jButtonLiteral.setSize(biggestButton);
    jButtonProperty = new JButton(propertyIcon);
    // "Property",
    ImageIcon nsIcon = new ImageIcon("images/tordf.gif");
    //  jButtonProperty.setSize(biggestButton);
    // jButtonFont = new JButton("Font");
    jButtonToRdf = new JButton(nsIcon);
    // "NS",

    jButtonSheet.addActionListener(this);
    jButtonResource.addActionListener(this);
    jButtonLiteral.addActionListener(this);
    jButtonProperty.addActionListener(this);
    jButtonToRdf.addActionListener(this);

    createMenuBar();
    addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent evt) {
          exitForm(evt);
        }
      });

    getContentPane().add(jScrollPane1, BorderLayout.CENTER);
    jToolBarTop.add(jButtonSheet);
    jToolBarTop.add(jButtonResource);
    jToolBarTop.add(jButtonLiteral);
    jToolBarTop.add(jButtonToRdf);
    jToolBarTop.setBackground(Color.darkGray);

    //  jToolBarLeft.add(jListRdf);
    getContentPane().add(jToolBarTop, BorderLayout.NORTH);
    getContentPane().add(jToolBarLeft, BorderLayout.WEST);
    getContentPane().add(jToolBarRight, BorderLayout.EAST);
    setJMenuBar(jMenuBarMain);

    setSize(width, height);
    getContentPane().add(jPanelMain, BorderLayout.CENTER);

  }

  private void createMenuBar() {
    jMenuBarMain = new JMenuBar();
    jMenuFile = new JMenu("File");

    jMenuItemNew = new JMenuItem("New", KeyEvent.VK_N);
    jMenuItemNew.addActionListener(this);
    jMenuFile.add(jMenuItemNew);

    jMenuItemOpen = new JMenuItem("Open", KeyEvent.VK_O);
    jMenuItemOpen.addActionListener(this);
    jMenuFile.add(jMenuItemOpen);

    jMenuItemSave = new JMenuItem("Save", KeyEvent.VK_S);
    jMenuItemSave.addActionListener(this);
    jMenuFile.add(jMenuItemSave);

    jMenuItemSaveAs = new JMenuItem("SaveAs...");
    jMenuItemSaveAs.addActionListener(this);
    jMenuFile.add(jMenuItemSaveAs);

    jMenuItemExit = new JMenuItem("Exit", KeyEvent.VK_E);
    jMenuItemExit.addActionListener(this);
    jMenuFile.add(jMenuItemExit);

    jMenuSettings = new JMenu("Settings");
    jMenuBarMain.add(jMenuFile);
    jMenuItemFont = new JMenuItem("Font", KeyEvent.VK_F);
    jMenuItemFont.addActionListener(this);
    jMenuFile.add(jMenuItemFont);

    jMenuBarMain.add(jMenuFile);
    jMenuBarMain.add(jMenuSettings);
  }

  private void exitForm(WindowEvent evt) {
    System.exit(0);
  }


  /**
   * @param  args  the command line arguments
   */
  public static void main(String args[]) {
    new Fluent().show();
  }

}

