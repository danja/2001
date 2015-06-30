/*
 * Decompiled by Mocha from ThreeD.class
 */

/*
 * Originally compiled from ThreeD.java
 */

package old.ann;

import java.awt.Point;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class ThreeD {

  final int tableSize = 512;

  final int angleShift = 9;

  final int maxAngle = 511;

  int sinTable[];

  int cosTable[];

  int xSize;

  int ySize;

  int zSize;

  int midX;

  int midY;

  int eyeX;

  int eyeY;

  int eyeZ;

  int xAngle;

  int yAngle;

  int zAngle;

  int xAngleStep;

  int yAngleStep;

  int zAngleStep;


  public ThreeD(int i1, int j1, int k, int i2, int j2) {

    xSize = k;

    ySize = i2;

    zSize = j2;

    midX = i1 / 2;

    midY = j1 / 2;

    computeSinCosTables();

    setEyePosition(0, 0, -500);

    setAngles(0, 0, 0);

  }


  public void setEyePosition(int i, int j, int k) {

    eyeX = i;

    eyeY = j;

    eyeZ = k;

  }


  public void setAngles(int i, int j, int k) {

    xAngle = (i >= 0 && i <= 511) ? i : 0;

    yAngle = (j >= 0 && j <= 511) ? j : 0;

    zAngle = (k >= 0 && k <= 511) ? k : 0;

  }


  public void setAngleSteps(int i, int j, int k) {

    xAngleStep = (Math.abs(i) < 51) ? i : 0;

    yAngleStep = (Math.abs(j) < 51) ? j : 0;

    zAngleStep = (Math.abs(k) < 51) ? k : 0;

  }


  public Point[] setPerspective(Point3D apoint3D[]) {

    Point apoint[] = new Point[apoint3D.length];

    int i = midX;

    int j = midY;

    for (int k = 0; k < apoint3D.length; k++) {

      i = midX;

      j = midY;

      if (eyeZ != apoint3D[k].z) {

        double d = (double) eyeZ / ((double) eyeZ - apoint3D[k].z);

        if (apoint3D[k].x != 0) {

          i = (int) ((double) i + d * apoint3D[k].x);
        }

        if (apoint3D[k].y != 0) {

          j = (int) ((double) j + d * apoint3D[k].y);
        }
      }

      apoint[k] = new Point(i, j);

    }

    return apoint;
  }


  public Point setPerspective(Point3D point3D) {

    int i = midX;

    int j = midY;

    if (eyeZ != point3D.z) {

      double d = (double) eyeZ / ((double) eyeZ - point3D.z);

      if (point3D.x != 0) {

        i = (int) ((double) i + d * point3D.x);
      }

      if (point3D.y != 0) {

        j = (int) ((double) j + d * point3D.y);
      }
    }

    return new Point(i, j);
  }


  public int changeAngle(int i, int j) {

    if (j >= 0) {

      i = (i <= 511 - j) ? (i + j) : (511 + j - i);
    } else {

      i = (i >= Math.abs(j)) ? (i + j) : (511 + j);
    }

    return i;
  }


  public void changeAngles() {

    xAngle = changeAngle(xAngle, xAngleStep);

    yAngle = changeAngle(yAngle, yAngleStep);

    zAngle = changeAngle(zAngle, zAngleStep);

  }


  public Point[] rotate3Dto2D(Point3D apoint3D[]) {

    Point apoint[] = new Point[apoint3D.length];

    int i3 = sinTable[xAngle];

    int j3 = sinTable[yAngle];

    int k3 = sinTable[zAngle];

    int i4 = cosTable[xAngle];

    int j4 = cosTable[yAngle];

    int k4 = cosTable[zAngle];

    for (int i5 = 0; i5 < apoint3D.length; i5++) {

      int i2 = apoint3D[i5].x;

      int j2 = apoint3D[i5].y;

      int k2 = apoint3D[i5].z;

      int i1 = i2 * j4 + k2 * j3 >> 9;

      int k1 = k2 * j4 - i2 * j3 >> 9;

      i2 = i1;

      k2 = k1;

      int j1 = j2 * k4 - k2 * k3 >> 9;

      k1 = j2 * k3 + k2 * k4 >> 9;

      j2 = j1;

      k2 = k1;

      i1 = i2 * i4 - j2 * i3 >> 9;

      j1 = i2 * i3 + j2 * i4 >> 9;

      i2 = i1;

      j2 = j1;

      i1 = midX;

      j1 = midY;

      if (eyeZ != k2) {

        double d = (double) eyeZ / ((double) eyeZ - k2);

        if (i2 != 0) {

          i1 = (int) ((double) i1 + d * i2);
        }

        if (j2 != 0) {

          j1 = (int) ((double) j1 + d * j2);
        }
      }

      apoint[i5] = new Point(i1, j1);

    }

    return apoint;
  }


  public Point3D[] rotate3DPoint(Point3D apoint3D1[]) {

    Point3D apoint3D2[] = new Point3D[apoint3D1.length];

    for (int i3 = 0; i3 < apoint3D1.length; i3++) {

      int i2 = apoint3D1[i3].x;

      int j2 = apoint3D1[i3].y;

      int k2 = apoint3D1[i3].z;

      int i1 = i2 * cosTable[yAngle] + k2 * sinTable[yAngle] >> 9;

      int k1 = k2 * cosTable[yAngle] - i2 * sinTable[yAngle] >> 9;

      i2 = i1;

      k2 = k1;

      int j1 = j2 * cosTable[zAngle] - k2 * sinTable[zAngle] >> 9;

      k1 = j2 * sinTable[zAngle] + k2 * cosTable[zAngle] >> 9;

      j2 = j1;

      k2 = k1;

      i1 = i2 * cosTable[xAngle] - j2 * sinTable[xAngle] >> 9;

      j1 = i2 * sinTable[xAngle] + j2 * cosTable[xAngle] >> 9;

      i2 = i1;

      j2 = j1;

      apoint3D2[i3] = new Point3D(i2, j2, k2);

    }

    return apoint3D2;
  }


  public Point3D rotate3DPoint(Point3D point3D) {

    int i2 = point3D.x;

    int j2 = point3D.y;

    int k2 = point3D.z;

    int i1 = i2 * cosTable[yAngle] + k2 * sinTable[yAngle] >> 9;

    int k1 = k2 * cosTable[yAngle] - i2 * sinTable[yAngle] >> 9;

    i2 = i1;

    k2 = k1;

    int j1 = j2 * cosTable[zAngle] - k2 * sinTable[zAngle] >> 9;

    k1 = j2 * sinTable[zAngle] + k2 * cosTable[zAngle] >> 9;

    j2 = j1;

    k2 = k1;

    i1 = i2 * cosTable[xAngle] - j2 * sinTable[xAngle] >> 9;

    j1 = i2 * sinTable[xAngle] + j2 * cosTable[xAngle] >> 9;

    i2 = i1;

    j2 = j1;

    return new Point3D(i2, j2, k2);
  }


  void computeSinCosTables() {

    sinTable = new int[512];

    cosTable = new int[512];

    for (int i = 0; i < 512; i++) {

      double d = (double) i * 3.141592653589793 / 256.0;

      sinTable[i] = (int) Math.round(Math.sin(d) * 512.0 + 0.5);

      cosTable[i] = (int) Math.round(Math.cos(d) * 512.0 + 0.5);

    }

  }

}

