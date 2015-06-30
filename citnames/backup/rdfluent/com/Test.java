/*
 * Test.java
 *
 * Created on 23 July 2001, 14:56
 */

package com;

import java.lang.reflect.*;
/**
 *
 * @author  danny
 * @version 
 */
public class Test {

    /** Creates new Test */
    public Test() {
    }

    /**
    * @param args the command line arguments
    */
    public static void main (String args[]) {
        String s = Vocab.value;
        System.out.println(s);
        String ns = "Vocab";
        String item = "value";
        Class c = null;
        try{
        c = Class.forName("com.Vocab");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Field[] fs = c.getFields();
        Field f;
            
        f = c.getField("height");
        heightValue = (String) heightField.get(r);
        
        System.out.println(f[0]);
        }
    }

}
