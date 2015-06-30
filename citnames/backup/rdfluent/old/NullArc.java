/*
 * NullArc.java
 *
 * Created on 18 May 2001, 17:25
 */

package old;

/**
 * @author     danny
 * @created    18 May 2001
 * @version
 */
public class NullArc extends VisualArc {

  /**
   *  Creates new NullArc
   */
  public NullArc() {
  }

  public VisualNode getStartNode() {
    return new NullNode();
  }

  public VisualNode getEndNode() {
    return new NullNode();
  }
}
