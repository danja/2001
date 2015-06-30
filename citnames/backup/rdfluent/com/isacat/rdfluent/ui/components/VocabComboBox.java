/*
 * VocabComboBox.java
 *
 * Created on 01 August 2001, 14:58
 */

package com.isacat.rdfluent.ui.components;

import javax.swing.*;
import java.util.*;
/**
 *
 * @author  danny
 * @version 
 */
public class VocabComboBox extends JComboBox {

    private Vector items;
    private String name;
    private int type;
    /** Creates new VocabComboBox */
    public VocabComboBox() {
    }

    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setType(int type){
        System.out.println("SETTING TYPE : "+type);
        this.type = type;
    }
    
    public int getType(){
        return type;
    }
    
    public void setItems(Vector items){
        this.items = items;
        for(int i=0;i<items.size();i++){
            addItem(items.elementAt(i));
        }
    }
    
    public Vector getItems(){
        return items;
    }
}
