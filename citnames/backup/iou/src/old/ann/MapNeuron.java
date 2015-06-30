/*
 * Decompiled by Mocha from MapNeuron.class
 */

/*
 * Originally compiled from MapNeuron.java
 */

package old.ann;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class MapNeuron extends Neuron {

  int x;

  int y;

  float feedback;


  public MapNeuron() {

    init(0, 0);

  }


  public MapNeuron(int i, int j) {

    init(i, j);

  }


  int getXPos() {

    return x;
  }


  int getYPos() {

    return y;
  }


  float getFeedback() {

    return feedback;
  }


  void init(int i, int j) {

    x = i;

    y = j;

    feedback = 0.0F;

  }


  float computeFeedback(MapNeuron mapNeuron, double d) {

    int i = Math.abs(x - mapNeuron.getXPos());

    int j = Math.abs(y - mapNeuron.getYPos());

    return (float) Math.exp((double) -(i * i + j * j) / d);
  }

}

