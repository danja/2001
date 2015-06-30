/*
 * Arc.java
 *
 * Created on 16 May 2001, 11:19
 */

package com.isacat.rdfluent.things;

import java.awt.geom.*;
import java.awt.*;
import java.awt.image.*;
/**
 * @author     danny
 * @created    16 May 2001
 * @version
 */
public abstract class VisualArc extends VisualThing {

  protected VisualNode startNode;
  protected VisualNode endNode;

  protected int startX;
  protected int startY;
  protected int endX;
  protected int endY;
  protected int lastStartX;
  protected int lastStartY;
  protected int lastEndX;
  protected int lastEndY;

  private boolean xBelow = false;
  private boolean yBelow = false;

  private Line2D line;
  private Color color = Color.blue;
  private Color selectedColor = Color.red;

  /**
   *  Creates new Arc
   */
  public VisualArc() {
    line = new Line2D.Double();
    super.setType(VisualThing.ARROW);
  }

  public void setStartNode(VisualNode startNode) {
    this.startNode = startNode;
  }

  public void setEndNode(VisualNode endNode) {
    this.endNode = endNode;
  }

  public VisualNode getStartNode() {
    return startNode;
  }

  public VisualNode getEndNode() {
    return endNode;
  }

  public int getCentreX() {
    return startX + (endX - startX) / 2;
  }

  public int getCentreY() {
    return startY + (endY - startY) / 2;
  }

  public int getWidth() {
    int width = endX - startX;
    if (width < 0) {
      xBelow = true;
      return -width;
    }
    xBelow = false;
    return width;
  }

  public int getHeight() {
    int height = endY - startY;
    if (height < 0) {
      yBelow = true;
      return -height;
    }
    yBelow = false;
    return height;
  }

  public boolean belowPoint(Point point) {
    if ((point.getX() < startX) || (point.getX() > endX)) {
      return false;
    }
    if ((point.getY() < startY) || (point.getY() > endY)) {
      return false;
    }
    return true;
  }

  public void update() {
    startX = (int) startNode.getCentre().getX();
    startY = (int) startNode.getCentre().getY();
    endX = (int) endNode.getCentre().getX();
    endY = (int) endNode.getCentre().getY();
    line.setLine(startX, startY, endX, endY);
  }

  public void paint() {
    paint(graphics2D, false);
  }

  public void rubberband(Graphics2D graphs2D, int endX, int endY) {
    //unPaintArrow(graphs2D);
    //unPaintText(getText(), getCentreX(), getCentreY() - 20);
    unPaint(graphs2D, true);

    startX = (int) startNode.getCentre().getX();
    startY = (int) startNode.getCentre().getY();
    this.endX = endX;
    this.endY = endY;
    paint(graphs2D, true);
  }

  public void paint(Graphics2D graphs2D, boolean rubberband) {
    graphs2D.setStroke(stroke);
    if (!rubberband) {
      update();
    }
    if (isSelected()) {
      graphs2D.setPaint(selectedColor);
    } else {
      graphs2D.setPaint(color);
    }

    paintText(getValue(), getCentreX(), getCentreY() - 20);

    line.setLine(startX, startY, endX, endY);
    graphs2D.draw(line);

    if (!rubberband) {
      paintArrow(graphs2D);
    }
    getStartNode().overPaint();
    if (endNode != null) {
      getEndNode().overPaint();
    }
    graphs2D.setPaint(color);
  }

  public void paintArrow(Graphics2D graphs2D) {
    paintArrow(graphs2D, true);
  }

  public void unPaintArrow(Graphics2D graphs2D) {
    paintArrow(graphs2D, false);
  }

  public void paintArrow(Graphics2D graphs2D, boolean draw) {
    if (graphics2D == null) {
      return;
    }
    Color foreColor = graphics2D.getColor();
    if (!draw) {
      graphics2D.setColor(backgroundColor);
    }
    line.setLine(startX, startY, getCentreX(), getCentreY());
    AffineTransform at = new AffineTransform();

    at.rotate(Math.toRadians(15), getCentreX(), getCentreY());
    at.scale(0.1, 0.1);
    at.translate(9 * (double) getCentreX(), 9 * (double) getCentreY());
    graphs2D.setTransform(at);
    graphs2D.draw(line);

    at.setToIdentity();
    at.rotate(Math.toRadians(-15), getCentreX(), getCentreY());
    at.scale(0.1, 0.1);
    at.translate(9 * (double) getCentreX(), 9 * (double) getCentreY());
    graphs2D.setTransform(at);
    graphs2D.draw(line);
    at.setToIdentity();
    graphs2D.setTransform(at);
    if (!draw) {
      graphics2D.setColor(foreColor);
    }
  }

  public void overPaint() {
  }

  public void refresh() {
    unPaint();
    paint();
  }

  public void unPaint() {
    unPaint(graphics2D, false);
  }

  public void unPaint(Graphics2D graphs2D, boolean rubberband) {
    if (!rubberband) {
      unPaintArrow(graphs2D);
      unPaintText(getValue(), getCentreX(), getCentreY() - 20);
    }

    graphs2D.setStroke(stroke);
    graphs2D.setPaint(Color.white);
    // backgroundColor
    line.setLine(startX, startY, endX, endY);
    graphs2D.draw(line);
    graphs2D.setPaint(color);
  }
}
