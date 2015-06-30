/*
 * Decompiled by Mocha from NeuralNet.class
 */

/*
 * Originally compiled from NeuralNet.java
 */

package old.ann;

import java.io.PrintStream;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
abstract class NeuralNet {

  final int PATTERN_LENGTH = 100;

  final int PATTERN_VALUE = 101;

  final int PATTERNFILE_LENGTH = 102;

  final int GENERAL_IO = 104;

  final int FILE_NOT_FOUND = 105;

  final int NUMBER_OF_IVALUES = 106;

  final int NUMBER_OF_TVALUES = 107;

  final int TOO_FEW_LEARNINGCYCLES = 108;

  double learningRate;

  int learningCycle;

  int maxLearningCycles;

  String maxLearningCyclesString;

  int displayStep;

  long startTime;

  boolean learnInfinitely;

  boolean stopLearning;


  NeuralNet() {

    maxLearningCycles = -1;

    learnInfinitely = false;

    stopLearning = false;

  }


  public double square(double d) {

    return d * d;
  }


  void setLearningRate(double d) {

    learningRate = d;

  }


  void setDisplayStep(int i) {

    displayStep = i;

  }


  void setMaxLearningCycles(int i) {

    if (i == -1) {

      learnInfinitely = true;

      maxLearningCycles = -1;

      maxLearningCyclesString = "infinite";

      return;
    }

    if (i > 0) {

      maxLearningCycles = i;

      learnInfinitely = false;

      maxLearningCyclesString = String.valueOf(i);

      return;
    }

    error(108);

  }


  double getLearningRate() {

    return learningRate;
  }


  String getElapsedTime() {

    return String.valueOf((float) ((double) (System.currentTimeMillis() - startTime) * 0.0010));
  }


  int getMaxLearningCycles() {

    return maxLearningCycles;
  }


  int getLearningCycle() {

    return learningCycle;
  }


  boolean finishedLearning() {

    return stopLearning;
  }


  void error(int i) {

    String string1 = null;

    String string2 = null;

    switch (i) {

      case 105:

        string1 = "The specified pattern file couldn't be found.";

        string2 = "Check path and filename of your pattern file.";

        break;
      case 104:

        string1 = "A general IO error occurred while reading a file.";

        string2 = "Check the accessed file, maybe it's corrupted.";

        break;
      case 100:

        string1 = "A pattern in the specified pattern file has the wrong number of values.";

        string2 = "The amount of values in one row must be: value in second line + value in third line + 1.";

        break;
      case 106:

        string1 = "The number of input values in the specified pattern file is not the same as the number of neurons in the input neuron layer.";

        string2 = "Set the value in the second line of your pattern file to the number of input neurons or change the number of neurons in the input layer.";

        break;
      case 107:

        string1 = "The number of target values in the specified pattern file is not the same as the number of neurons in the output neuron layer.";

        string2 = "Set the value in the third line of your pattern file to the number of output neurons.";

        break;
      case 101:

        string1 = "A pattern value in the specified pattern file was wrong.";

        string2 = "Use pattern values that are 0 or 1.";

        break;
      case 102:

        string1 = "The number of patterns in the specified pattern file does not match the given number.";

        string2 = "Correct the number in the first line of your pattern file.";

        break;
      case 108:

        string1 = "There are too few maximum learning cycles defined.";

        string2 = "Increase the value of the maximum learning cycles or set it to -1 if the net should learn infinitely.";

        break;
    }

    System.out.println("Error [" + i + "]:\n\r" + string1);

    System.out.println("Try this: " + string2 + "\n\r");

    System.exit(0);

  }


  boolean displayNow() {

    if (learningCycle % displayStep == 0) {

      return true;
    } else {

      return false;
    }

  }


  void resetTime() {

    startTime = System.currentTimeMillis();

  }


  void incLearningCycle() {

    learningCycle++;

  }

}

