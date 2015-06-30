/*
 * Decompiled by Mocha from WeightMatrix.class
 */

/*
 * Originally compiled from WeightMatrix.java
 */

package old.ann;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class WeightMatrix {

  float weight[][];

  float bias[];

  int size;


  public WeightMatrix(int i, int j, boolean flag) {

    weight = new float[i][j];

    bias = new float[j];

    for (int k = 0; k < j; k++) {

      bias[k] = 1.0F;
    }

    size = i * j;

    if (flag) {

      size += j;
    }
  }


  float[] getInputWeights(int i) {

    float af[] = new float[weight.length + 1];

    for (int j = 0; j < weight.length; j++) {

      af[j] = weight[j][i];
    }

    af[weight.length] = bias[i];

    return af;
  }


  float[] getOutputWeights(int i) {

    float af[] = new float[weight[0].length];

    for (int j = 0; j < weight[0].length; j++) {

      af[j] = weight[i][j];
    }

    return af;
  }


  float[][] getWeights() {

    return weight;
  }


  float[] getBiases() {

    return bias;
  }


  void init() {

    for (int i = 0; i < weight.length; i++) {

      for (int j = 0; j < weight[0].length; j++) {

        weight[i][j] = (float) Math.random() * 2.0F - 1.0F;
      }
    }

    for (int k = 0; k < weight[0].length; k++) {

      bias[k] = (float) Math.random() * 2.0F - 1.0F;
    }

  }


  void init(float aaf[][]) {

    for (int i = 0; i < weight.length; i++) {

      for (int j = 0; j < weight[0].length; j++) {

        weight[i][j] = aaf[i][j];
      }
    }

  }


  void init(InputValue ainputValue[], int i1) {

    int i2;

    switch (i1) {

      case 1:

        for (int j = 0; j < weight[0].length; j++) {

          weight[0][j] = ainputValue[j].getX();
        }

        return;
      case 2:

        for (int k = 0; k < weight[0].length; k++) {

          weight[0][k] = ainputValue[k].getX();

          weight[1][k] = ainputValue[k].getY();

        }

        return;
      case 3:

        i2 = 0;

        break;
      default:

    }

    for (int i = 0; i < weight[0].length; i++) {

      weight[0][i] = ainputValue[i].getX();

      weight[1][i] = ainputValue[i].getY();

      weight[2][i] = ainputValue[i].getZ();

    }

    return;
  }


  void changeWeights(float af1[], float af2[], double d) {

    for (int i = 0; i < weight.length; i++) {

      if (af1[i] != 0.0F) {

        for (int j = 0; j < weight[0].length; j++) {

          if (af2[j] != 1.0F && af2[j] != 0.0F) {

            float f1 = weight[i][j];

            weight[i][j] = 0.0F;

            weight[i][j] = f1 + af1[i] * af2[j] * (1.0F - af2[j]) * (float) d;

          }
        }

      }
    }

    for (int k = 0; k < bias.length; k++) {

      if (af2[k] != 1.0F && af2[k] != 0.0F) {

        float f2 = bias[k];

        bias[k] = 0.0F;

        bias[k] = f2 + af2[k] * (1.0F - af2[k]) * (float) d;

      }
    }

  }


  void changeWeightsKFM(float af1[], float af2[], double d) {

    for (int i = 0; i < weight.length; i++) {

      for (int j = 0; j < weight[0].length; j++) {

        if (af1[i] != weight[i][j] && af2[j] != 0.0F) {

          float f = weight[i][j];

          weight[i][j] = 0.0F;

          weight[i][j] = f + af2[j] * (af1[i] - f) * (float) d;

        }
      }

    }

  }


  int size() {

    return size;
  }

}

