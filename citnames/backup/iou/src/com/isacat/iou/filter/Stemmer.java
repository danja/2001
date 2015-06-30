package com.isacat.iou.filter;

import java.util.*;

/*
 * Porter stemmer
 */

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    12 February 2001
 */
public class Stemmer {
  private char[] characters;
  private int i,
  /*
   * offset into b
   */
    i_end,
  /*
   * offset to end of stemmed word
   */
    j, k;

  /*
   * unit of size whereby b is increased
   */
  public Stemmer() {
    characters = new char[50];
    //   b[] = new char;
    i = 0;
    i_end = 0;
  }

  /**
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.)
   *
   * @param  hs  Description of Parameter
   * @return     Description of the Returned Value
   */

  /**
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.)
   *
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) After a word has been stemmed, it can be
   *  retrieved by toString(), or a reference to the internal buffer can be
   *  retrieved by getResultBuffer and getResultLength (which is generally more
   *  efficient.) After a word has been stemmed, it can be retrieved by
   *  toString(), or a reference to the internal buffer can be retrieved by
   *  getResultBuffer and getResultLength (which is generally more efficient.)
   *  After a word has been stemmed, it can be retrieved by toString(), or a
   *  reference to the internal buffer can be retrieved by getResultBuffer and
   *  getResultLength (which is generally more efficient.) After a word has been
   *  stemmed, it can be retrieved by toString(), or a reference to the internal
   *  buffer can be retrieved by getResultBuffer and getResultLength (which is
   *  generally more efficient.) Stem the word placed into the Stemmer buffer
   *  through calls to add(). Returns true if the stemming process resulted in a
   *  word different from the input. You can retrieve the result with
   *  getResultLength()/getResultBuffer() or toString().
   *
   * @param  hs  Description of Parameter
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   * @return     Description of the Returned Value
   */

  public HashSet stemSet(HashSet hs) {
    HashSet stemmed = new HashSet();
    String word = null;
    System.out.println(hs);
    for (Iterator it = hs.iterator(); it.hasNext(); ) {
      word = (String) it.next();

      System.out.println("word = " + word + "  stemmed = " + stem(word));
      stemmed.add(stem(word));
    }
    return stemmed;
  }

  public String stem(String s) {
    System.out.println(s);
    String stemmed = new String();
    s.getChars(0, s.length(), characters, 0);
    i = s.length();
    k = i - 1;
    //  k = i - 1;

    //  k = i - 1;
    //  if (k > 1) {
    step1();
    step2();
    step3();
    step4();
    step5();
    step6();
    //}
    //   i_end = k+1; i = 0;
    //
    for (int ii = 0; ii < k + 1; ii++) {
      stemmed += characters[ii];
    }
    return stemmed;
  }

  /*
   * true is characters[i] is considered a consonant.
   */

  private final boolean isConsonant(int i) {
    switch (characters[i]) {
      case 'a':
        return false;
      case 'e':
        return false;
      case 'i':
        return false;
      case 'o':
        return false;
      case 'u':
        return false;
      case 'y':
        return (i == 0) ? true : !isConsonant(i - 1);
      default:
        return true;
    }
  }

  /*
   * isPatternCVC(i) is true <=> i-2,i-1,i has the form consonant - vowel - consonant
   * and also if the second c is not w,x or y. this is used when trying to
   * restore an e at the end of a short word. e.g.
   * cav(e), lov(e), hop(e), crim(e), but
   * snow, box, tray.
   */

  private final boolean isPatternCVC(int i) {
    if (i < 2 || !isConsonant(i) || isConsonant(i - 1) || !isConsonant(i - 2)) {
      return false;
    }
     {
      int ch = characters[i];
      if (ch == 'w' || ch == 'x' || ch == 'y') {
        return false;
      }
    }
    return true;
  }

  /*
   * m() measures the number of consonant sequences between 0 and j. if c is
   * a consonant sequence and v a vowel sequence, and <..> indicates arbitrary
   * presence,
   * <c><v>       gives 0
   * <c>vc<v>     gives 1
   * <c>vcvc<v>   gives 2
   * <c>vcvcvc<v> gives 3
   * ....
   */

  private final int countVCPattern() {
    int n = 0;
    int i = 0;
    while (true) {
      if (i > j) {
        return n;
      }
      if (!isConsonant(i)) {
        break;
      }
      i++;
    }
    i++;
    while (true) {
      while (true) {
        if (i > j) {
          return n;
        }
        if (isConsonant(i)) {
          break;
        }
        i++;
      }
      i++;
      n++;
      while (true) {
        if (i > j) {
          return n;
        }
        if (!isConsonant(i)) {
          break;
        }
        i++;
      }
      i++;
    }
  }

  /*
   * containsVowel() is true <=> 0,...j contains a vowel
   */

  private final boolean containsVowel() {
    int i;
    for (i = 0; i <= j; i++) {
      if (!isConsonant(i)) {
        return true;
      }
    }
    return false;
  }

  /*
   * doubleConsonant(j) is true <=> j,(j-1) contain a double consonant.
   */

  private final boolean doubleConsonant(int j) {
    if (j < 1) {
      return false;
    }
    if (characters[j] != characters[j - 1]) {
      return false;
    }
    return isConsonant(j);
  }

  private final boolean endsWith(String s) {
    int length = s.length();
    int o = k - length + 1;
    if (o < 0) {
      return false;
    }
    for (int i = 0; i < length; i++) {
      if (characters[o + i] != s.charAt(i)) {
        return false;
      }
    }
    j = k - length;
    return true;
  }

  /*
   * append(s) sets (j+1),...k to the characters in the string s, readjusting
   * k.
   */

  private final void append(String s) {
    int l = s.length();
    int o = j + 1;
    for (int i = 0; i < l; i++) {
      characters[o + i] = s.charAt(i);
    }
    k = j + l;
  }


  /*
   * step1() gets rid of plurals and -ed or -ing. e.g.
   * caresses  ->  caress
   * ponies    ->  poni
   * ties      ->  ti
   * caress    ->  caress
   * cats      ->  cat
   * feed      ->  feed
   * agreed    ->  agree
   * disabled  ->  disable
   * matting   ->  mat
   * mating    ->  mate
   * meeting   ->  meet
   * milling   ->  mill
   * messing   ->  mess
   * meetings  ->  meet
   */

  private final void step1() {
    if (characters[k] == 's') {
      if (endsWith("sses")) {
        k -= 2;
      } else
        if (endsWith("ies")) {
        append("i");
      } else
        if (characters[k - 1] != 's') {
        k--;
      }
    }
    if (endsWith("eed")) {
      if (countVCPattern() > 0) {
        k--;
      }
    } else
      if ((endsWith("ed") || endsWith("ing")) && containsVowel()) {
      k = j;
      if (endsWith("at")) {
        append("ate");
      } else
        if (endsWith("bl")) {
        append("ble");
      } else
        if (endsWith("iz")) {
        append("ize");
      } else
        if (doubleConsonant(k)) {
        k--;
         {
          int ch = characters[k];
          if (ch == 'l' || ch == 's' || ch == 'z') {
            k++;
          }
        }
      } else if (countVCPattern() == 1 && isPatternCVC(k)) {
        append("e");
      }
    }
  }

  /*
   * step2() turns terminal y to i when there is another vowel in the stem.
   */

  private final void step2() {
    if (endsWith("y") && containsVowel()) {
      characters[k] = 'i';
    }
  }

  /*
   * step3() maps double suffices to single ones. so -ization ( = -ize plus
   * -ation) maps to -ize etc. note that the string before the suffix must give
   * countVCPattern() > 0.
   */

  private final void replace(String s) {
    if (countVCPattern() > 0) {
      append(s);
    }
  }

  private final void step3() {
    if (k == 0) {
      return;
    }
    /*
     * For Bug 1
     */
    switch (characters[k - 1]) {

      case 'a':
        if (endsWith("ational")) {
          replace("ate");
          break;
        }
        if (endsWith("tional")) {
          replace("tion");
          break;
        }
        break;
      case 'c':
        if (endsWith("enci")) {
          replace("ence");
          break;
        }
        if (endsWith("anci")) {
          replace("ance");
          break;
        }
        break;
      case 'e':
        if (endsWith("izer")) {
          replace("ize");
          break;
        }
        break;
      case 'l':
        if (endsWith("bli")) {
          replace("ble");
          break;
        }
        if (endsWith("alli")) {
          replace("al");
          break;
        }
        if (endsWith("entli")) {
          replace("ent");
          break;
        }
        if (endsWith("eli")) {
          replace("e");
          break;
        }
        if (endsWith("ousli")) {
          replace("ous");
          break;
        }
        break;
      case 'o':
        if (endsWith("ization")) {
          replace("ize");
          break;
        }
        if (endsWith("ation")) {
          replace("ate");
          break;
        }
        if (endsWith("ator")) {
          replace("ate");
          break;
        }
        break;
      case 's':
        if (endsWith("alism")) {
          replace("al");
          break;
        }
        if (endsWith("iveness")) {
          replace("ive");
          break;
        }
        if (endsWith("fulness")) {
          replace("ful");
          break;
        }
        if (endsWith("ousness")) {
          replace("ous");
          break;
        }
        break;
      case 't':
        if (endsWith("aliti")) {
          replace("al");
          break;
        }
        if (endsWith("iviti")) {
          replace("ive");
          break;
        }
        if (endsWith("biliti")) {
          replace("ble");
          break;
        }
        break;
      case 'g':
        if (endsWith("logi")) {
          replace("log");
          break;
        }
    }
  }

  /*
   * step4() deals with -ic-, -full, -ness etc. similar strategy to step3.
   */

  private final void step4() {
    switch (characters[k]) {
      case 'e':
        if (endsWith("icate")) {
          replace("ic");
          break;
        }
        if (endsWith("ative")) {
          replace("");
          break;
        }
        if (endsWith("alize")) {
          replace("al");
          break;
        }
        break;
      case 'i':
        if (endsWith("iciti")) {
          replace("ic");
          break;
        }
        break;
      case 'l':
        if (endsWith("ical")) {
          replace("ic");
          break;
        }
        if (endsWith("ful")) {
          replace("");
          break;
        }
        break;
      case 's':
        if (endsWith("ness")) {
          replace("");
          break;
        }
        break;
    }
  }

  /*
   * step5() takes off -ant, -ence etc., in context <c>vcvc<v>.
   */

  private final void step5() {
    if (k == 0) {
      return;
    }
    switch (characters[k - 1]) {
      case 'a':
        if (endsWith("al")) {
          break;
        }
        return;
      case 'c':
        if (endsWith("ance")) {
          break;
        }
        if (endsWith("ence")) {
          break;
        }
        return;
      case 'e':
        if (endsWith("er")) {
          break;
        }
        return;
      case 'i':
        if (endsWith("ic")) {
          break;
        }
        return;
      case 'l':
        if (endsWith("able")) {
          break;
        }
        if (endsWith("ible")) {
          break;
        }
        return;
      case 'n':
        if (endsWith("ant")) {
          break;
        }
        if (endsWith("ement")) {
          break;
        }
        if (endsWith("ment")) {
          break;
        }
        if (endsWith("ent")) {
          break;
        }
        return;
      case 'o':
        if (endsWith("ion") && j >= 0 && (characters[j] == 's' || characters[j] == 't')) {
          break;
        }
        if (endsWith("ou")) {
          break;
        }
        return;
      /*
       * takes care of -ous
       */
      case 's':
        if (endsWith("ism")) {
          break;
        }
        return;
      case 't':
        if (endsWith("ate")) {
          break;
        }
        if (endsWith("iti")) {
          break;
        }
        return;
      case 'u':
        if (endsWith("ous")) {
          break;
        }
        return;
      case 'v':
        if (endsWith("ive")) {
          break;
        }
        return;
      case 'z':
        if (endsWith("ize")) {
          break;
        }
        return;
      default:
        return;
    }
    if (countVCPattern() > 1) {
      k = j;
    }
  }

  /*
   * step6() removes a final -e if countVCPattern() > 1.
   */

  private final void step6() {
    j = k;
    if (characters[k] == 'e') {
      int a = countVCPattern();
      if (a > 1 || a == 1 && !isPatternCVC(k - 1)) {
        k--;
      }
    }
    if (characters[k] == 'l' && doubleConsonant(k) && countVCPattern() > 1) {
      k--;
    }
  }

}

