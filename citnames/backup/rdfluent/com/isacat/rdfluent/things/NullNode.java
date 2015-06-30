/*
 * NullThing.java
 *
 * Created on 17 May 2001, 21:54
 */

package com.isacat.rdfluent.things;

import java.awt.*;
/**
 * @author     danny
 * @created    18 May 2001
 * @version
 */
public class NullNode extends VisualNode {

  /**
   *  Creates new NullThing
   */
  public NullNode() {
  }

  public void setGraphics(Graphics graphics) {
  }

  public void setShape(VisualNode visualNode) {
  }

  public void setSelected(boolean selected) {
  }

  /**
   *  Creates new Thing
   *
   * @param  name  The new Name value
   */
  public void setName(String name) {
  }

  public void setLocation(Point location) {
  }

  public void setSize(Dimension size) {
  }

  public void setBackgroundColor(Color backgroundColor) {

  }

  public boolean isSelected() {
    return false;
  }

  public Graphics getGraphics() {
    return null;
  }

  public Dimension getSize() {
    return null;
  }

  public String getName() {
    return null;
  }

  public VisualNode getShape() {
    return null;
  }

  public Point getLocation() {
    return null;
  }

  public void paint() {
  }

  public boolean belowPoint(Point point) {
    return false;
  }

  public void unPaint() {
  }

  public void translate(int x, int y) {
  }

}
