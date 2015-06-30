/*
 * Decompiled by Mocha from Pattern.class
 */

/*
 * Originally compiled from Pattern.java
 */

package old.ann;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    23 March 2001
 */
public class Pattern {

  int pat[];

  int nr;

  String type;

  String patString;


  public Pattern(String string1, String aastring[][]) {

    patString = string1;

    pat = new int[string1.length() * aastring[0][1].length()];

    String string2 = new String();

    String string3 = new String();

    for (int i = 0; i < string1.length(); i++) {

      for (int j = 0; j < aastring.length; j++) {

        string3 = String.valueOf(string1.charAt(i));

        if (string3.compareTo(" ") == 0) {

          string2 += aastring[0][1];

          break;
        }

        if (string3.compareTo(aastring[j][0]) != 0) {

          continue;
        }

        string2 += aastring[j][1];

        break;
      }

    }

    char ach[] = new char[string2.length()];

    ach = string2.toCharArray();

    for (int k = 0; k < pat.length; k++) {

      pat[k] = Character.digit(ach[k], 10);
    }

  }


  String getPatternString() {

    return patString;
  }


  float getValue(int i) {

    return (float) pat[i];
  }


  int size() {

    return pat.length;
  }


  boolean checkValues() {

    for (int i = 0; i < pat.length; i++) {

      if (pat[i] < 0 || pat[i] > 1) {

        return false;
      }
    }

    return true;
  }

}

