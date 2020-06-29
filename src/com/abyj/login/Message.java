package com.abyj.login;
import com.abyj.databaseconnect.DbConnect;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Message
{
    public void database(String username,String message) throws SQLException, ClassNotFoundException {
        System.out.println("check 1");
        DbConnect.connection();

        // String query = "select id, username, password from Users where username="+"jagriti"+" and password="+"jags";
        String InsertQuery = "insert into ";
        String TableName = " usermessage ";
        String colName = "(username,message)";
        //String whereQuery = " where username ='jagriti' and password ='jags' ";
        String vals = "values('"+username+"','"+message+"')";
        String allQuery = InsertQuery+TableName+colName;
        System.out.println(allQuery);
        ResultSet rs = DbConnect.query(allQuery);
    }
}
