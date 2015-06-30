/*
 * Decompiled by Mocha from BackpropagationNet.class
 */

/*
 * Originally compiled from BackpropagationNet.java
 */

package old.ann;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class BackPropNet extends NeuralNet {

  Vector neuronLayerVector;
  NeuronLayer neuronLayerArray[];
  WeightMatrix weightMatrixArray[];
  Pattern inputPatternArray[];
  Pattern targetPatternArray[];
  float minimumError;
  float layerOutputError[][];
  float error;
  String conversionTable[][];
  int lastLayer;
  int lastMatrix;
  int lastPattern;

  public BackPropNet() {
    minimumError = 5.0E-4F;
    learningRate = 0.25F;
    error = 0.0F;
    neuronLayerVector = new Vector();
  }

  void setMinimumError(float f) {
    minimumError = f;
  }

  float[][] getWeightValues(int i) {
    if (i < 0 || i > lastMatrix) {
      System.out.println("Incorrect number for weight matrix.");
      System.exit(0);
    }
    return weightMatrixArray[i].getWeights();
  }

  float getMinimumError() {
    return minimumError;
  }

  String getAsciiValue(String string) {
    int i1 = 0;
    for (int j = conversionTable.length; i1 < j; ) {
      int k = i1 + j >> 1;
      int i2 = string.compareTo(conversionTable[k][1]);
      if (i2 == 0) {
        return conversionTable[k][0];
      }
      if (i2 > 0) {
        i1 = k;
      } else {
        j = k;
      }
    }
    return "*";
  }

  float getError() {
    float f = 0.0F;
    for (int i = 0; i < layerOutputError.length; i++) {
      for (int j = 0; j < layerOutputError[0].length; j++) {
        f += square(layerOutputError[i][j]);
      }
    }
    return Math.abs(f * 0.5F);
  }

  void addNeuronLayer(int i) {
    neuronLayerVector.addElement(new NeuronLayer(i));
  }

  void connectLayers() {
    weightMatrixArray = new WeightMatrix[neuronLayerVector.size() - 1];
    neuronLayerArray = new NeuronLayer[neuronLayerVector.size()];
    int i = 0;
    for (Enumeration enumeration = neuronLayerVector.elements(); enumeration.hasMoreElements(); ) {
      neuronLayerArray[i++] = (NeuronLayer) enumeration.nextElement();
    }
    neuronLayerVector = null;
    for (i = 0; i < weightMatrixArray.length; i++) {
      weightMatrixArray[i] = new WeightMatrix(neuronLayerArray[i].size(), neuronLayerArray[i + 1].size(), true);
      weightMatrixArray[i].init();
    }
    lastLayer = neuronLayerArray.length - 1;
    lastMatrix = weightMatrixArray.length - 1;
    System.out.println("neuron layers connected.");
  }

  synchronized void readConversionFile(String string1) {
    String string2 = null;
    try {
      System.out.println("reading conversion file.");
      DataInputStream dataInputStream = new DataInputStream(new FileInputStream(string1));
      int i = Integer.parseInt(dataInputStream.readLine());
      conversionTable = new String[i][2];
      for (int j = 0; j < i; j++) {
        string2 = dataInputStream.readLine();
        conversionTable[j][0] = String.valueOf(string2.charAt(0));
        conversionTable[j][1] = string2.substring(1);
      }
    } catch (FileNotFoundException e1) {
      error(105);
      return;
    } catch (IOException e2) {
      error(104);
    }
    return;
  }

  void learn() {
    learningCycle++;
    for (int i = 0; i <= lastPattern; i++) {
      neuronLayerArray[0].setInput(inputPatternArray[i]);
      for (int j = 1; j <= lastLayer; j++) {
        neuronLayerArray[j].computeInput(neuronLayerArray[j - 1], weightMatrixArray[j - 1]);
        neuronLayerArray[j].computeOutput();
      }
      neuronLayerArray[lastLayer].computeLayerError(targetPatternArray[i]);
      layerOutputError[i] = neuronLayerArray[lastLayer].getLayerError();
      for (int k = lastMatrix; k >= 0; k--) {
        weightMatrixArray[k].changeWeights(neuronLayerArray[k].getOutput(), neuronLayerArray[k + 1].getLayerError(), learningRate);
        if (k > 0) {
          neuronLayerArray[k].computeLayerError(neuronLayerArray[k + 1], weightMatrixArray[k]);
        }
      }
    }
    error = getError();
  }

  synchronized void readPatternFile(String string1) {
    String string2 = null;
    try {
      System.out.println("reading patternfile \"" + string1 + "\".");
      DataInputStream dataInputStream = new DataInputStream(new FileInputStream(string1));
      try {
        int i1 = Integer.parseInt(dataInputStream.readLine());
        int j = Integer.parseInt(dataInputStream.readLine());
        if (j * conversionTable[0][1].length() != neuronLayerArray[0].size()) {
          error(106);
        }
        int k = Integer.parseInt(dataInputStream.readLine());
        if (k * conversionTable[0][1].length() != neuronLayerArray[lastLayer].size()) {
          error(107);
        }
        inputPatternArray = new Pattern[i1];
        targetPatternArray = new Pattern[i1];
        lastPattern = inputPatternArray.length - 1;
        layerOutputError = new float[lastPattern + 1][neuronLayerArray[lastLayer].size()];
        for (int i2 = 0; i2 < i1; i2++) {
          string2 = dataInputStream.readLine();
          if (string2 == null) {
            error(102);
          } else if (string2.length() != j + k + 1) {
            error(100);
          } else {
            //       inputPatternArray[i2] = new Pattern(string2.substring(0, j), i2, "input", conversionTable);
            //      targetPatternArray[i2] = new Pattern(string2.substring(j + 1), i2, "target", conversionTable);
          }
        }
      } catch (EOFException e1) {
        error(102);
        return;
      }
      return;
    } catch (FileNotFoundException e2) {
      error(105);
      return;
    } catch (IOException e3) {
      error(104);
    }
  }
}

