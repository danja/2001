/*
 * VisualNode.java
 *
 * Created on 17 May 2001, 10:26
 */

package com.isacat.rdfluent.things;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

import com.isacat.rdfluent.ui.*;

/**
 * @author     danny
 * @created    18 May 2001
 * @versio
 */
public class VisualNode extends VisualThing {

  protected java.util.List arcs = new Vector();
  protected Shape shape;
  protected int positionX = START_X;
  protected int positionY = START_Y;
  protected int width = DEFAULT_WIDTH;
  protected int height = DEFAULT_HEIGHT;

//  private int type = 1;
  private boolean fill = false;
  private boolean wipe = false;

  private Rectangle2D rectangle = new Rectangle2D.Float(positionX, positionY, width, height);
  private Ellipse2D ellipse = new Ellipse2D.Float(positionX, positionY, width, height);
//  private Shape[] shapes = {rectangle, ellipse};
  public final static int DEFAULT_WIDTH = 120;
  public final static int DEFAULT_HEIGHT = 40;
  public final static int START_X = 10;
  public final static int START_Y = 10;


  /**
   *  Creates new VisualNode
   */
  public VisualNode() {
  }

  public void setType(int type) {
    System.out.println("TYPE = " + type);
//   this.type = type;
    super.setType(type);
    switch (type) {
      case VisualThing.RECTANGLE:
        shape = rectangle;
        break;
      case VisualThing.ELLIPSE:
        shape = ellipse;
        break;
    }
  }

  public void setLocation(Point location) {
    positionX = (int) location.getX();
    positionY = (int) location.getY();
  }

  public void setLocation(int positionX, int positionY) {
    this.positionX = positionX;
    this.positionY = positionY;
  }

  public void setSize(Dimension size) {
    this.width = (int) size.getWidth();
    this.height = (int) size.getHeight();
  }

  public void setSize(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void setShape(Shape shape) {
    this.shape = shape;
  }

  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public void setFill(boolean fill) {
    this.fill = fill;
  }

  public Dimension getSize() {
    return new Dimension(width, height);
  }

  public Point getCentre() {
    return new Point(getCentreX(), getCentreY());
  }

  public int getCentreX() {
    return positionX + width / 2;
  }

  public int getCentreY() {
    return positionY + height / 2;
  }


  public void overPaint() {
    wipe = true;
    paint();
    wipe = false;
    paint();
  }

  public void paint() {
    paint(graphics2D);
  }

  public void unPaint() {
    Color current = currentColor;
    currentColor = backgroundColor;
    paint();
    currentColor = current;
  }

  public void paint(Graphics2D graphs2D) {
    // graphs2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    switch (type) {
      case RECTANGLE:

        ((Rectangle2D.Float) shape).setRect(positionX, positionY, width, height);
        break;
      case ELLIPSE:
        ((Ellipse2D.Float) shape).setFrame(positionX, positionY, width, height);
        break;
    }
    /*
     * if (selected) {
     * currentColor = selectedColor;
     * } else {
     * currentColor = foregroundColor;
     * }
     */
    graphs2D.setPaint(currentColor);
    graphs2D.setStroke(stroke);
    if (fill) {
      graphs2D.fill(shape);
    } else {
      if (wipe) {
        graphs2D.setPaint(backgroundColor);
        graphs2D.fill(shape);
        graphs2D.setPaint(currentColor);
      }
    }
    graphs2D.draw(shape);
    paintText(getURI(), getCentreX(), getCentreY());
  }

  public boolean belowPoint(Point point) {
    return shape.contains(point);
  }

  public void translate(int x, int y) {
    unPaint();
    positionX += x;
    positionY += y;
    paint();
  }

  public void addArc(VisualArc arc) {
    arcs.add(arc);
  }

  public void removeArc(VisualArc arc) {
    arcs.remove(arc);
  }

  public void translateArcs() {
    for (int i = 0; i < arcs.size(); i++) {
      ((VisualArc) arcs.get(i)).refresh();
    }
  }
}

