/*
 * Decompiled by Mocha from InputValue.class
 */

/*
 * Originally compiled from InputValue.java
 */

package old.ann;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class InputValue {

  int x;

  int y;

  int z;


  public InputValue() {

    x = 0;

    y = 0;

    z = 0;

  }


  public InputValue(int i, int j, int k) {

    x = i;

    y = j;

    z = k;

  }


  void setX(int i) {

    x = i;

  }


  void setY(int i) {

    y = i;

  }


  void setZ(int i) {

    z = i;

  }


  int getX() {

    return x;
  }


  int getY() {

    return y;
  }


  int getZ() {

    return z;
  }

}

