
package old.ann;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    26 March 2001
 */
public class InputMatrix {

  InputValue value[];

  int dimension;


  public InputMatrix(int i, int j) {

    value = new InputValue[i];

    dimension = (j > 0 && j < 4) ? j : 3;

    for (int k = 0; k < i; k++) {

      value[k] = new InputValue();
    }

  }


  public void setInputX(int an[]) {

    if (an.length <= value.length) {

      for (int i = 0; i < an.length; i++) {

        value[i].setX(an[i]);
      }
    }
  }


  public void setInputY(int an[]) {

    if (an.length <= value.length) {

      for (int i = 0; i < an.length; i++) {

        value[i].setY(an[i]);
      }
    }
  }


  public void setInputZ(int an[]) {

    if (an.length <= value.length) {

      for (int i = 0; i < an.length; i++) {

        value[i].setZ(an[i]);
      }
    }
  }


  public void setInputValues(int an1[], int an2[], int an3[]) {

    setInputX(an1);

    setInputY(an2);

    setInputZ(an3);

  }


  int getDimension() {

    return dimension;
  }


  InputValue[] getInputValues() {

    return value;
  }


  InputValue getRandomInput() {

    return value[(int) (Math.random() * value.length)];
  }


  int size() {

    return value.length;
  }

}

