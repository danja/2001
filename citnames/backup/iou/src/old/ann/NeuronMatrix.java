/*
 * Decompiled by Mocha from NeuronMatrix.class
 */
/*
 * Originally compiled from NeuronMatrix.java
 */
package old.ann;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class NeuronMatrix {
  MapNeuron mapNeuron[];
  int nr;
  int xSize;
  int ySize;

  public NeuronMatrix(int i) {
    xSize = i;
    ySize = 1;
    mapNeuron = new MapNeuron[xSize * ySize];
    for (int j = 0; j < i; j++) {
      mapNeuron[j] = new MapNeuron();
    }
  }

  public NeuronMatrix(int i1, int j1) {
    xSize = i1;
    ySize = j1;
    mapNeuron = new MapNeuron[i1 * j1];
    int k = 0;
    for (int i2 = 0; i2 < i1; i2++) {
      for (int j2 = 0; j2 < j1; j2++) {
        mapNeuron[k++] = new MapNeuron();
      }
    }
  }

  MapNeuron[] getMapNeurons() {
    return mapNeuron;
  }

  float[] getFeedback(MapNeuron mapNeuron, double d) {
    float af[] = new float[this.mapNeuron.length];
    float f = (float) (2.0 * d * d);
    for (int i = 0; i < this.mapNeuron.length; i++) {
      af[i] = this.mapNeuron[i].computeFeedback(mapNeuron, (double) f);
    }
    return af;
  }

  void init(InputValue ainputValue[]) {
    int i = 0;
    for (int j = 0; j < xSize; j++) {
      for (int k = 0; k < ySize; k++) {
        mapNeuron[i].init(j, k);
        i++;
      }
    }
  }

  int size() {
    return mapNeuron.length;
  }

  int xSize() {
    return xSize;
  }

  int ySize() {
    return ySize;
  }

  MapNeuron computeActivationCenter(NeuronLayer neuronLayer, WeightMatrix weightMatrix) {
    float f1 = 1.0E20F;
    int i = 0;
    float af1[] = new float[weightMatrix.size()];
    float af2[] = new float[neuronLayer.size()];
    af2 = neuronLayer.getOutput();
    for (int j = 0; j < mapNeuron.length; j++) {
      af1 = weightMatrix.getInputWeights(j);
      float f2 = 0.0F;
      for (int k = 0; k < af2.length; k++) {
        if (af2[k] != af1[k]) {
          f2 += (af2[k] - af1[k]) * (af2[k] - af1[k]);
        }
      }
      if (f2 < f1) {
        f1 = f2;
        i = j;
      }
    }
    return mapNeuron[i];
  }
}
