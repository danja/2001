/*
 * Thing.java
 *
 * Created on 16 May 2001, 11:17
 */

package com.isacat.rdfluent.things;

import java.awt.*;
import com.isacat.rdfluent.ui.*;

import com.hp.hpl.mesa.rdf.jena.model.*;
/**
 * @author     danny
 * @created    16 May 2001
 * @version
 */
public interface Thing {

  public void setModel(Model model);

 // public void setText(String text);

  // public String getText();

  public void setType(int type);

  public int getType();

  public void setSize(Dimension size);

  public Dimension getSize();

  public void setLocation(Point position);

  public Point getLocation();

  public void setFont(Font font);

  public void setSheet(Sheet sheet);

  public Sheet getSheet();

  public void setGraphics(Graphics graphics);

  public Graphics getGraphics();

  public void setSelected(boolean selected);

  public boolean isSelected();

  public boolean belowPoint(Point point);

  public void paint();

  public void unPaint();

  public void setBackgroundColor(Color backgroundColor);

  public void setValue(String uri);
  
    public String getValue();
    
    public String toString();

}

