/*
 * Decompiled by Mocha from NeuronLayer.class
 */

/*
 * Originally compiled from NeuronLayer.java
 */

package old.ann;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class NeuronLayer {

  Neuron neuron[];


  NeuronLayer(int i) {

    neuron = new Neuron[i];

    for (int j = 0; j < i; j++) {

      neuron[j] = new Neuron();
    }

  }


  void setInput(Pattern pattern) {

    for (int i = 0; i < neuron.length; i++) {

      neuron[i].init(pattern.getValue(i));
    }

  }


  void setInput(InputValue inputValue) {

    float af[] = new float[3];

    af[0] = inputValue.getX();

    af[1] = inputValue.getY();

    af[2] = inputValue.getZ();

    for (int i = 0; i < neuron.length; i++) {

      neuron[i].init(af[i]);
    }

  }


  float[] getOutput() {

    float af[] = new float[neuron.length];

    for (int i = 0; i < neuron.length; i++) {

      af[i] = neuron[i].getOutput();
    }

    return af;
  }


  float[] getLayerError() {

    float af[] = new float[neuron.length];

    for (int i = 0; i < neuron.length; i++) {

      af[i] = neuron[i].getOutputError();
    }

    return af;
  }


  void computeInput(NeuronLayer neuronLayer, WeightMatrix weightMatrix) {

    for (int i = 0; i < neuron.length; i++) {

      neuron[i].computeInput(neuronLayer.getOutput(), weightMatrix.getInputWeights(i));
    }

  }


  void computeOutput() {

    for (int i = 0; i < neuron.length; i++) {

      neuron[i].activateSigmoid();
    }

  }


  void computeLayerError(Pattern pattern) {

    for (int i = 0; i < neuron.length; i++) {

      neuron[i].computeOutputError(pattern.getValue(i));
    }

  }


  void computeLayerError(NeuronLayer neuronLayer, WeightMatrix weightMatrix) {

    for (int i = 0; i < neuron.length; i++) {

      neuron[i].computeOutputError(neuronLayer.getLayerError(), weightMatrix.getOutputWeights(i));
    }

  }


  int size() {

    return neuron.length;
  }

}

