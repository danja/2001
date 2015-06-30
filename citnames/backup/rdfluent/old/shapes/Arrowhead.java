/*
 * Grace 1.0
 *
 * (c) copyright 1999 Gerwin Klein <kleing@in.tum.de>
 * All rights reserved.
 *
 * (c) copyright 1999 Technical University of Munich, Germany
 *
 * This software was written as part of the diploma thesis
 * of Gerwin Klein under supervision of Alfons Brandl at
 * the Chair of Computer Sciences II of the Technical
 * University of Munich.
 *
 * The Technical University of Munich reserves the right to
 * use this software and to make it accessible to other persons.
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 */

package old.shapes;

import java.awt.*;
import java.awt.geom.*;

import java.beans.*;

import grace.editor.PropertyManager;
import grace.util.java2d.Polygon2D;

/**
 *  An arrowhead to be drawn at edge ends. Arrowheads use the {@link
 *  PropertyNames#DECORATOR_PAINT} property for their color.
 *
 * @author     Gerwin Klein
 * @created    18 May 2001
 * @version    $Revision: 1.11 $, $Date: 1999/10/03 00:35:23 $
 * @see        PropertyNames#DECORATOR_PAINT
 */
public class Arrowhead {

  /**
   *  the shape to draw
   */
  private Polygon2D polygon = null;

  /**
   *  for rotation and translation
   */
  private AffineTransform at = new AffineTransform();

  /**
   *  the arrowhead length
   */
  private final static double length = 12.0;


  /**
   *  Constructs a new arrow head
   */
  public Arrowhead() {
    double l1 = length * 1.0;
    double l2 = length * 1.3;
    double w = length * 0.4;

    polygon = new Polygon2D.Double();
    polygon.moveTo(0, 0);
    polygon.lineTo(l2, w);
    polygon.lineTo(l1, 0);
    polygon.lineTo(l2, -w);
    polygon.closePath();
  }


  /**
   *  Get the bounding box of the shape used to draw this connector end.
   *
   * @return    The Bounds value
   */
  public Rectangle2D getBounds() {
    return at.createTransformedShape(polygon).getBounds2D();
  }


  /**
   *  Gets the shape of this figure.
   *
   * @return    the shape of this figure
   */
  public Shape getShape() {
    return at.createTransformedShape(polygon);
  }


  /**
   *  Paint the arrow head in the specified graphics context.
   *
   * @param  g  the graphics context to draw in
   */
  public void paint(Graphics2D g) {
    AffineTransform save = g.getTransform();
    Site pos = getSite();

    at.setToTranslation(pos.getX(), pos.getY());
    at.rotate(getAngle());

    g.transform(at);

    if (paint != null) {
      g.setPaint(paint);
    }

    g.fill(polygon);

    g.setTransform(save);
  }

}
