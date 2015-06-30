/*
 * Table.java
 *
 * Created on 26 March 2001, 13:30
 */

package com.isacat.iou.database.model;

import java.util.*;

/**
 * @author     danny
 * @created    26 March 2001
 * @version
 */
public class DBTable {

  String name;
  ArrayList fields = new ArrayList();

  /**
   *  Creates new Table
   */
  public DBTable() {
  }

  public DBTable(String s) {
    name = s;
  }

  public void setName(String s) {
    name = s;
  }

  public String getName() {
    return name;
  }

  public String getCreateSQL() {
    Object key;
    DBTableField field;
    String type;

    StringBuffer sql = new StringBuffer("CREATE TABLE " + name + " (");
    field = (DBTableField) fields.get(0);

    sql.append(field.getName() + " " + field.getType() + " " + field.getNullable() + " " + field.getKey());

    for (int i = 1; i < fields.size(); i++) {
      field = (DBTableField) fields.get(i);
      sql.append(", " + field.getName() + " " + field.getType() + " " + field.getNullable() + " ");
      if (field.getKey().equals("PRIMARY KEY")) {
        sql.append("," + field.getKey() + " (" + field.getName() + ")");
      }
      if (field.getKey().equals("KEY")) {
        if (field.getType().equals("BLOB") || field.getType().equals("TEXT")) {
          sql.append("," + field.getKey() + " (" + field.getName() + "(3))");
        } else {
          sql.append("," + field.getKey() + " (" + field.getName() + ")");
        }
      }
    }
    sql.append(")");
    return sql.toString();
  }

  public void addField(DBTableField field) {
    fields.add(field);
  }

  public void loadFromProperties(Properties tableProps) {
// System.out.println(tableName+".fields");
    setName(tableProps.getProperty("name"));
    DBTableField field;
    int nFields = Integer.parseInt(tableProps.getProperty("fields"));
    for (int i = 0; i < nFields; i++) {
      field = new DBTableField();
      field.setName(tableProps.getProperty("field" + i));
      field.setType(tableProps.getProperty("type" + i));
      field.setNullable(tableProps.getProperty("null" + i, ""));
      field.setKey(tableProps.getProperty("key" + i, ""));
      addField(field);
    }
  }
}
