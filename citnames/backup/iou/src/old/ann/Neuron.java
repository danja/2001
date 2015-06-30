/*
 * Decompiled by Mocha from Neuron.class
 */

/*
 * Originally compiled from Neuron.java
 */

package old.ann;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class Neuron {

  float input;

  float output;

  float outputError;


  public Neuron() {

    init(0.0F);

  }


  float getInput() {

    return input;
  }


  float getOutput() {

    return output;
  }


  float getOutputError() {

    return outputError;
  }


  void init(float f) {

    input = f;

    output = f;

    outputError = 0.0F;

  }


  void computeInput(float af1[], float af2[]) {

    input = 0.0F;

    for (int i = 0; i < af1.length; i++) {

      if (af1[i] != 0.0F && af2[i] != 0.0F) {

        input += af1[i] * af2[i];
      }
    }

    input += af2[af2.length - 1];

  }


  void activateSigmoid() {

    output = 1.0F / (1.0F + (float) Math.exp((double) -input));

  }


  void computeOutputError(float f) {

    if (output != 0.0F && output != 1.0F) {

      outputError = (f - output) * output * (1.0F - output);
    }
  }


  void computeOutputError(float af1[], float af2[]) {

    outputError = 0.0F;

    for (int i = 0; i < af1.length; i++) {

      if (af1[i] != 0.0F && output != 0.0F && output != 1.0F) {

        outputError += af1[i] * af2[i] * output * (1.0F - output);
      }
    }

  }

}

