package com.abyj.databaseconnect;

import java.sql.*;

public class DbConnect {
  static  String sql;
    static    String username =null;
    String password = null;
    static  Connection con;
    static  Statement stmt;
    static  ResultSet rs;

    public static void connection() throws ClassNotFoundException, SQLException {
        System.out.println("Into dbconnect");
        Class.forName("com.mysql.cj.jdbc.Driver");//load driver
        System.out.println("driver loaded");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chattingapp", "root", "");
        System.out.println("connection created/nConnection Successful");
        //create statement
        stmt = con.createStatement();
        System.out.println("Statement created");
        //sql query
    }
    public static ResultSet query(String sql) throws SQLException {
       // sql = "select * from Users";
        //Execute Query
        System.out.println(sql);

        rs = stmt.executeQuery(sql);
        System.out.println("data from rs " + rs);
        return rs;
    }
    public static int querys(String sql) throws SQLException {
        // sql = "select * from Users";
        //Execute Query
        System.out.println(sql);

       int rs = stmt.executeUpdate(sql);
        System.out.println("data from rs " + rs);
        return rs;
    }
    public static void closeFiles() throws SQLException {
        rs.close();
        stmt.close();
        con.close();

    }
}

//the runinng code of jdbc
/*
public static void main(String[] args) throws ClassNotFoundException, SQLException {
    String sql;
    String username =null;
    String password = null;
    Connection con;
    Statement stmt;
    ResultSet rs;
    System.out.println("Into dbconnect");
    Class.forName("com.mysql.cj.jdbc.Driver");//load driver
    System.out.println("driver loaded");
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp","root","");
    System.out.println("connection created/nConnection Successful");
    //create statement
    stmt = con.createStatement();
    System.out.println("Statement created");
    //sql query
    sql = "select * from Users";
    //Execute Query
    rs = stmt.executeQuery(sql);
    //process the result
    // String ename = rs.getString(1);
    // System.out.println(ename);
    System.out.println("data from rs "+rs);
    rs.close();
    stmt.close();
    con.close();

}
*/
