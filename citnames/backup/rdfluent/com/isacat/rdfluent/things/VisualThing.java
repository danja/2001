/*
 * VisualThing.java
 *
 * Created on 16 May 2001, 11:39
 */

package com.isacat.rdfluent.things;

import java.awt.*;
import java.util.*;
import com.isacat.rdfluent.ui.*;
import java.awt.font.*;
import com.isacat.rdfluent.things.*;
import com.hp.hpl.mesa.rdf.jena.model.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    16 May 2001
 */
public abstract class VisualThing implements Thing {

  public Point position = new Point();

  protected Sheet sheet;
  protected String text;
  protected String uri;

  protected boolean selected = false;

  protected VisualNode visualNode;

  protected Graphics graphics;
  protected Graphics2D graphics2D;
  protected Color backgroundColor;

  protected Font font;
  protected Model model;

  protected int type = 99;
  protected Color currentColor;
  protected Color foregroundColor = DEFAULT_COLOR;
  protected Color selectedColor = DEFAULT_SELECTED_COLOR;

  public final static Color DEFAULT_SELECTED_COLOR = Color.red;
  public final static Color DEFAULT_COLOR = Color.black;

  public final static int ARROW = 0;
  public final static int RECTANGLE = 1;
  public final static int ELLIPSE = 2;
  final static BasicStroke stroke = new BasicStroke(1.2f);
  final static int maxCharHeight = 130;
  final static int minFontSize = 6;
  static int textWidth = 180;

  /**
   *  Creates new VisualThing
   */
  public VisualThing() {
  }

  public VisualThing(Model model) {
    this.model = model;
  }

  public void setGraphics(Graphics graphics) {
    this.graphics = graphics;
    this.graphics2D = (Graphics2D) graphics;
  }

  public void setType(int type) {
    this.type = type;
  }

  public void setFont(Font font) {
    //  System.out.println("thingsetfont = " + font);
    this.font = font;
  }

  public void setSheet(Sheet sheet) {
    this.sheet = sheet;
  }

  //public void setText(String text) {
 //   this.text = text;
 // }

  public void setTextWidth(int textWidth) {
    this.textWidth = textWidth;
  }

  public void setSize(Dimension size) {
    visualNode.setSize(size);
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
    if (selected) {
      currentColor = selectedColor;
    } else {
      currentColor = foregroundColor;
    }
  }

  public void setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
  }

  public void setColor(Color color) {
    this.currentColor = color;
  }

  public void setModel(Model model) {
    this.model = model;
  }

  public void setLocation(Point position) {
    this.position = position;
  }

  public void setValue(String uri) {
    this.uri = uri;
  }

  public int getType() {
    return type;
  }

  public Sheet getSheet() {
    return sheet;
  }

  public int getTextWidth() {
    return textWidth;
  }

  public Graphics getGraphics() {
    return graphics;
  }

  //public String getText() {
  //  return text;
  //}

  public Color getColor() {
    return currentColor;
  }


  public boolean isSelected() {
    return selected;
  }

  //public void setPosition(Point position) {
  //  this.position = position;
  //}

  //public Point getPosition() {
  //  return position;
  //}

  public Dimension getSize() {
    return visualNode.getSize();
  }

  public Point getLocation() {
    return position;
  }

  public String getURI() {
    return uri;
  }

  /*
   * public void unPaint() {
   * paint(false);
   * }
   *
   * public void paint(boolean draw){
   * Color current = currentColor;
   * if(!draw) currentColor = backgroundColor;
   * paint();
   * if(!draw) currentColor = current;
   * }
   */

  protected void paintText(String text, int centreX, int centreY) {
    paintText(text, centreX, centreY, true);
  }

  protected void unPaintText(String text, int centreX, int centreY) {
    paintText(text, centreX, centreY, false);
  }

  protected void paintText(String text, int centreX, int centreY, boolean draw) {

    if (graphics2D == null) {
      return;
    }
    Color current = currentColor;
    if (!draw) {
      graphics2D.setColor(backgroundColor);
    }
    graphics2D.setFont(font);
    FontMetrics fontMetrics = graphics2D.getFontMetrics();
    graphics2D.drawString(text, centreX - fontMetrics.stringWidth(text) / 2, centreY + fontMetrics.getAscent() / 3);
    if (!draw) {
      graphics2D.setColor(current);
    }
  }

  protected FontMetrics pickFont(String longString, int xSpace) {
    System.out.println("picksetfont = " + font);
    //  fontMap.put(TextAttribute.SIZE, new Integer(100));
    boolean fontFits = false;
//    Font font = graphics2D.getFont();
    //  Font font = new Font(fontMap);
    FontMetrics fontMetrics = graphics2D.getFontMetrics();
    int size = font.getSize();
    String name = font.getName();
    int style = font.getStyle();

    while (!fontFits) {
      if ((fontMetrics.getHeight() <= maxCharHeight)
         && (fontMetrics.stringWidth(longString) <= xSpace)) {
        fontFits = true;
      } else {
        if (size <= minFontSize) {
          fontFits = true;
        } else {
          graphics2D.setFont(font = new Font(name,
            style,
            --size));
          fontMetrics = graphics2D.getFontMetrics();
        }
      }
    }
    return fontMetrics;
  }
  
  public String getValue() {
      return getURI();
  }
  
  public String toString(){
      return getTypeName(getType())+" : "+getValue();
  }
  
  public String getTypeName(int type){
      switch(type){
          case ARROW:
              return "Property";

          case ELLIPSE:
              return "Resource";

          case RECTANGLE:
              return "Literal";

      }
      return "Error, unknown type";
  }
}
