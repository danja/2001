/*
 * Sheet.java
 *
 * Created on 16 May 2001, 10:58
 */

package com.isacat.rdfluent.ui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;
import javax.swing.event.*;

import com.hp.hpl.mesa.rdf.jena.mem.*;
import com.hp.hpl.mesa.rdf.jena.model.*;

import com.isacat.rdfluent.things.*;
import com.isacat.rdfluent.rdf.*;

import com.isacat.rdfluent.ui.dialogs.*;

/**
 * @author     danny
 * @created    16 May 2001
 */
public class Sheet extends JInternalFrame implements ActionListener, MouseInputListener {
  java.util.List things = new Vector();
  // ActionListener,

  private PrintStream log = System.out;
  private Model model;

  private JPanel jPanelGraph;
//  private JPanel jPanelText;
// private JLabel jLabel1;
  private Point currentPoint;
  private NullNode nullNode = new NullNode();
  private Thing selectedThing = nullNode;
  private VisualArc arcRequested = null;
// private JSplitPane jSplitPane;

// private SimpleTextDialog textDialogId;

  private JTextArea jTextArea;
  private Class arcClass;
  private FProperty currentArc = null;
  private String sheetTitle = "Sheet";
  private Painter painter = new Painter();
  private Font font;
  private int thingSpacingX = 50;
  private int thingSpacingY = 50;
  private Point latestThingPosition = new Point(0, thingSpacingY);
//  private Dimension thingSpacing = new Dimension(10,10);

  private JPopupMenu popup;
  private JMenuItem popupItemEdit;
  private JMenuItem popupItemDelete;
  private String filename = null;

  private JPanel propertiesPanel;
  private JPanel resourcesPanel;
  private int thingType;
  private EditDialog editDialog;
//  private ActionListener parentActionListener;
  public final static Color backgroundColor = Color.white;

  public Sheet() {
    model = new ModelMem();
    initComponents();
  }


  public void setTitle(String title) {
    this.title = title;
  }

  public void setFont(Font font) {
    System.out.println("SHEETsetfont = " + font);
    this.font = font;
  }

  public void setArcNode(VisualNode node) {
    if (currentArc == null) {
      return;
    }
    if (currentArc.getStartNode() == null) {
      currentArc.setStartNode(node);
      node.addArc(currentArc);
      // node.setFill(true);
    } else {
      currentArc.setEndNode(node);
      node.addArc(currentArc);
      currentArc.getStartNode().setFill(false);
      System.out.println("setting end " + node);
      currentArc.link();
      addThing(currentArc);
    }
  }

  public Model getModel() {
    return model;
  }

  // public void setVisualArc(Class arcClass) {
  //  this.arcClass = arcClass;
  // }

  public Thing getThing() {
    Thing thing;
    for (int i = 0; i < things.size(); i++) {
      thing = (Thing) things.get(i);
      if (thing.belowPoint(currentPoint)) {
        return thing;
      }
    }
    return null;
  }

  public JPanel getGraphPanel() {
    return jPanelGraph;
  }

//  public JPanel getTextPanel() {
//    return jPanelText;
//  }

  public void log(String string) {
//    jTextArea.append(string + "\n");
  }

  public Thing addThing(Thing thing) {
    thing.setSheet(this);
    currentArc = null;
    clearSelected();
    System.out.println("add called");
    //   thing.setGraphics(getContentPane().getGraphics());
    thing.setGraphics(jPanelGraph.getGraphics());
    thing.setBackgroundColor(backgroundColor);
    thing.setFont(font);
    if (thing.getType() != 0) {
      setSpacing(thing);
    }
    things.add(thing);
    thing.paint();
    return thing;
  }

  public void mouseDragged(MouseEvent mouseEvent) {
    Point mousePoint = mouseEvent.getPoint();
    int newPositionX = (int) mousePoint.getX() - (int) currentPoint.getX();
    int newPositionY = (int) mousePoint.getY() - (int) currentPoint.getY();
    currentPoint = mousePoint;
    if (selectedThing instanceof com.isacat.rdfluent.things.VisualNode) {
      moveNode(newPositionX, newPositionY);
    }

    redraw();
  }

  public void mouseExited(MouseEvent mouseEvent) {
    // repaint();
    // redraw();
  }

  public void mouseReleased(MouseEvent mouseEvent) {

    System.out.println("RELEASED");
    // repaint();
    //  redraw();
    showPopup(mouseEvent);
  }


  public void redraw() {

    try {
      EventQueue.invokeLater(painter);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void mouseMoved(MouseEvent mouseEvent) {
    if (currentArc != null) {
      currentArc.rubberband((Graphics2D) jPanelGraph.getGraphics(), mouseEvent.getX(), mouseEvent.getY());
    }
  }

  public void mousePressed(MouseEvent mouseEvent) {
    log.println("MOUSEP");

    currentPoint = mouseEvent.getPoint();
    clearSelected();
    Thing thing = getThing();
    if (thing == null) {
      return;
    }

    if (thing.belowPoint(currentPoint)) {
      thingType = thing.getType();
      System.out.println("sheet thingtype " + thingType);
      selectedThing = thing;
      if (showPopup(mouseEvent)) {
        return;
      }
      thing.setSelected(true);
      thing.paint();
      if (currentArc != null) {
        setArcNode((VisualNode) thing);
      }
    }
  }

  public void mouseClicked(MouseEvent mouseEvent) {
    if (mouseEvent.getClickCount() == 2) {
      arcRequested();
      setArcNode((VisualNode) getThing());
    }
  }

  public void mouseEntered(MouseEvent mouseEvent) {
  }

  public void moveNode(int x, int y) {
    VisualNode node = (VisualNode) selectedThing;
    node.unPaint();
    node.translate(x, y);
    node.paint();
    node.translateArcs();
    return;
  }

  public void arcRequested() {
    currentArc = null;
    try {
      //  currentArc = (VisualArc) arcClass.newInstance();
      currentArc = new FProperty(getModel());
      // currentArc.setModel();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void saveAs() {
    JFileChooser jFileChooser = new JFileChooser();

    int returnVal = jFileChooser.showSaveDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      filename = jFileChooser.getSelectedFile().getPath();
    }
    save();
    setTitle(filename);
  }

  public void save() {
    if (filename == null) {
      saveAs();
    }
    System.out.println("model in save " + model);
    try {
      System.out.println("SAVEAS" + filename);
      model.write(new PrintWriter(new FileOutputStream(filename)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
    String command = actionEvent.getActionCommand();
    // Popup menu

    if (command.equals("Edit")) {
      editItem();
      //  editDialog.setVisible(true);
    }
    /*
     * textDialogId = new SimpleTextDialog(new Frame(), true);
     * textDialogId.setText("Enter Id");
     * textDialogId.setTarget(selectedThing);
     * textDialogId.setVisible(true);
     */

  }

  private Thing setSpacing(Thing thing) {
    if (things.size() != 0) {
      latestThingPosition.translate((int) thing.getSize().getWidth(), 0);
    }

    latestThingPosition.translate(thingSpacingX, 0);
    if (latestThingPosition.getX() + thing.getSize().getWidth() > getWidth()) {
      if (things.size() != 0) {
        latestThingPosition.translate(0, (int) thing.getSize().getHeight());
      }
      latestThingPosition.translate(thingSpacingX, thingSpacingY);
      latestThingPosition.setLocation(thingSpacingX, latestThingPosition.getY());
    }
    thing.setLocation(latestThingPosition);
    return thing;
  }

  private void editItem() {
   // if (editDialog == null) {
      editDialog = new EditDialog(this, new Frame(), true);
   // }
    System.out.println(thingType);
    editDialog.setTarget(selectedThing);
    System.out.println("selectedThing to edit = "+selectedThing);
    editDialog.display(thingType);
  }

  private void initComponents() {
    //   jSplitPane = new JSplitPane(javax.swing.JSplitPane.VERTICAL_SPLIT);
    //   jSplitPane.addMouseMotionListener(this);
    //   jSplitPane.setAutoscrolls(true);
    jPanelGraph = new JPanel();
    //  jPanelGraph.setPreferredSize(new Dimension(300,300));
    //  jPanelText = new JPanel();
    setMaximizable(true);

    setTitle(sheetTitle);
    setIconifiable(true);
    //  setForeground(java.awt.Color.pink);
    setResizable(false);
    setClosable(true);

    //  jSplitPane.setLeftComponent(jPanelGraph);
    //  jSplitPane.setRightComponent(jPanelText);
    //  getContentPane().add(jSplitPane, BorderLayout.CENTER);
    getContentPane().add(jPanelGraph, BorderLayout.CENTER);
    jPanelGraph.setBackground(backgroundColor);
    // jPanelGraph.addMouseListener(this);
    jPanelGraph.addMouseMotionListener(this);
    jPanelGraph.addMouseListener(this);

    //  jTextArea = new JTextArea(10, 10);
    //   jPanelText.setBackground(Color.white);
    //   jPanelText.add(jTextArea);
// sheet.getGraphPanel().addMouseListener(new PopupListener());

    //Add listener to components that can bring up popup menus.
    //   MouseListener popupListener = new PopupListener();
    popup = new JPopupMenu();
    popupItemEdit = new JMenuItem("Edit");
    popupItemEdit.addActionListener(this);
    popup.add(popupItemEdit);
    popupItemDelete = new JMenuItem("Delete");
    popupItemDelete.addActionListener(this);
    popup.add(popupItemDelete);
  }


  private void clearSelected() {
    selectedThing.setSelected(false);
    selectedThing.paint();
    selectedThing = nullNode;
  }

  private boolean showPopup(MouseEvent e) {
    if (e.isPopupTrigger()) {
      popup.show(e.getComponent(),
        e.getX(), e.getY());
      return true;
    }
    return false;
  }

  //   jToolBarLeft.add(jPanelResources);
  //  jToolBarLeft.add(jPanelProperties);
  /**
   *  Description of the Class
   *
   * @author     Danny Ayers
   * @created    19 May 2001
   */
  class Painter implements Runnable {

    public void run() {
      Thing thing;
      VisualNode node = null;

      for (int i = 0; i < things.size(); i++) {
        thing = (Thing) things.get(i);
        try {
          node = (VisualNode) thing;
        } catch (ClassCastException e) {
          // not a node
          //   ((VisualArc)thing).refresh();
          //  continue;
        } finally {
          thing.paint();
        }

        node.setFont(font);
        switch (node.getType()) {
          case VisualNode.ELLIPSE:
            ((FResource) node).update();
            break;
          case VisualNode.RECTANGLE:
            ((FLiteral) node).update();
            break;
        }
        //    node.paint();
      }
      /*
       * Thing thing;
       * for (int i = 0; i < things.size(); i++) {
       * thing = (Thing) things.get(i);
       * thing.setFont(font);
       * thing.update();
       * thing.paint();
       * }
       */
    }
  }

}
