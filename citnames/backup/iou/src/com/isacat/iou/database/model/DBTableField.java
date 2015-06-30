/*
 * TableField.java
 *
 * Created on 26 March 2001, 13:23
 */

package com.isacat.iou.database.model;

import java.util.*;
/**
 * @author     danny
 * @created    26 March 2001
 * @version
 */
public class DBTableField {

  private String name;
  private String type;
  private String key;
  private String nullable;

  /**
   *  Creates new TableField
   */
  public DBTableField() {
  }

  public DBTableField(String s) {
    name = s;
  }

  public void setName(String s) {
    name = s;
  }


  public void setType(String s) {
    type = s;
  }

  public void setKey(String s) {
    key = s;
  }

  public void setNullable(String s) {
    nullable = s;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getKey() {
    return key;
  }

  public String getNullable() {
    return nullable;
  }
}
