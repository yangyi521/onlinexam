package com.hp.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBhelper
{
  public final String DBDriver = "com.mysql.jdbc.Driver";
  public final String URL = "jdbc:mysql://127.0.0.1:3306/onlinexam";
  Connection con = null;
  ResultSet res = null;
  
  public void DataBase()
  {
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      this.con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/onlinexam", "root", "123456");
    }
    catch (ClassNotFoundException e)
    {
      System.err.println("装载 JDBC/ODBC驱动失败");
      e.printStackTrace();
    }
    catch (SQLException e)
    {
      System.err.println("数据库连接失败");
      e.printStackTrace();
    }
  }
  
  public ResultSet Search(String sql, String[] str)
  {
    DataBase();
    try
    {
      PreparedStatement pst = this.con.prepareStatement(sql);
      if (str != null) {
        for (int i = 0; i < str.length; i++) {
          pst.setString(i + 1, str[i]);
        }
      }
      this.res = pst.executeQuery();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return this.res;
  }
  
  public int AddU(String sql, Object[] str)
  {
    int a = 0;
    DataBase();
    try
    {
      PreparedStatement pst = this.con.prepareStatement(sql);
      if (str != null) {
        for (int i = 0; i < str.length; i++) {
          pst.setObject(i + 1, str[i]);
        }
      }
      a = pst.executeUpdate();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return a;
  }
}

