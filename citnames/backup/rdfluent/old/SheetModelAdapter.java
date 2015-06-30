/*
 * SheetModelAdapter.java
 *
 * Created on 20 May 2001, 09:20
 */

package old;

import java.io.*;
import com.hp.hpl.mesa.rdf.jena.mem.*;
import com.hp.hpl.mesa.rdf.jena.model.*;
/**
 * @author     danny
 * @created    26 May 2001
 * @version
 */
public class SheetModelAdapter extends Sheet {

  private Model model;

  /**
   *  Creates new SheetModelAdapter
   */
  public SheetModelAdapter() {
    super();
    model = new ModelMem();
  }

  public Model getModel() {
    return model;
  }

  public void saveAs() {
    String filename = "testfluent.rdf";
    System.out.println("model in save " + model);
    try {
      model.write(new PrintWriter(new FileOutputStream(filename)));
    } catch (Exception e) {
      e.printStackTrace();
    }
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
}
