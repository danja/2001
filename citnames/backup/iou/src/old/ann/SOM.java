/*
 * Decompiled by Mocha from KohonenFeatureMap.class
 */

/*
 * Originally compiled from KohonenFeatureMap.java
 */

package old.ann;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class SOM extends NeuralNet {

  NeuronMatrix mapLayer;

  NeuronLayer inputLayer;

  WeightMatrix weightMatrix;

  InputMatrix inputMatrix;

  InputValue iv[];

  int xSize;

  int ySize;

  double activationArea;

  double stopArea;

  double initActivationArea;

  double initLearningRate;


  public SOM() {

    learningCycle = 0;

    maxLearningCycles = -1;

    initLearningRate = 0.6;

    learningRate = initLearningRate;

    initActivationArea = (xSize >= ySize) ? (xSize / 2) : (ySize / 2);

    activationArea = initActivationArea;

    stopArea = initActivationArea / 10.0;

    stopLearning = false;

    resetTime();

  }


  void setInitLearningRate(double d) {

    initLearningRate = d;

    learningRate = d;

  }


  void setInitActivationArea(double d) {

    initActivationArea = d;

    activationArea = d;

  }


  void setActivationArea(double d) {

    activationArea = d;

  }


  void setStopArea(double d) {

    stopArea = d;

  }


  double getInitLearningRate() {

    return initLearningRate;
  }


  double getInitActivationArea() {

    return initActivationArea;
  }


  double getStopArea() {

    return stopArea;
  }


  double getActivationArea() {

    return activationArea;
  }


  int getMapSizeX() {

    return xSize;
  }


  int getMapSizeY() {

    return ySize;
  }


  int getNumberOfWeights() {

    return weightMatrix.size();
  }


  float[][] getWeightValues() {

    return weightMatrix.getWeights();
  }


  MapNeuron[] getMapNeurons() {

    return mapLayer.getMapNeurons();
  }


  InputValue[] getInputValues() {

    return inputMatrix.getInputValues();
  }


  void createMapLayer(int i) {

    if (mapLayer != null) {

      mapLayer = null;
    }

    mapLayer = new NeuronMatrix(i);

    xSize = i;

    ySize = 0;

  }


  void createMapLayer(int i, int j) {

    if (mapLayer != null) {

      mapLayer = null;
    }

    mapLayer = new NeuronMatrix(i, j);

    xSize = i;

    ySize = j;

  }


  void connectLayers(InputMatrix inputMatrix) {

    this.inputMatrix = inputMatrix;

    if (inputLayer != null) {

      inputLayer = null;
    }

    inputLayer = new NeuronLayer(inputMatrix.getDimension());

    int i = mapLayer.size();

    if (weightMatrix != null) {

      weightMatrix = null;
    }

    weightMatrix = new WeightMatrix(inputLayer.size(), i, false);

    if (iv != null) {

      iv = null;
    }

    iv = new InputValue[i];

    for (int j = 0; j < iv.length; j++) {

      iv[j] = inputMatrix.getRandomInput();
    }

    weightMatrix.init(iv, inputMatrix.getDimension());

    mapLayer.init(iv);

  }


  void decreaseActivationArea() {

    double d = (double) learningCycle * 2.0E-4;

    setLearningRate(initLearningRate * Math.exp(-2.0 * d));

    setActivationArea(initActivationArea * Math.exp(-5.0 * d));

  }


  void learn() {

    if (activationArea > stopArea && (learningCycle < maxLearningCycles || maxLearningCycles == -1)) {

      learningCycle++;

      inputLayer.setInput(inputMatrix.getRandomInput());

      MapNeuron mapNeuron = mapLayer.computeActivationCenter(inputLayer, weightMatrix);

      weightMatrix.changeWeightsKFM(inputLayer.getOutput(), mapLayer.getFeedback(mapNeuron, activationArea), learningRate);

      decreaseActivationArea();

      return;
    }

    stopLearning = true;

  }

}

