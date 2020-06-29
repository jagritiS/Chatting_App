package com.abyj.login;

import com.abyj.Chat.ChatClient;
import com.abyj.Chat.ChatServer;
import com.abyj.databaseconnect.DbConnect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectUsers {
    public void signUp(String u, String p)  {
        try {
            DbConnect.connection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String findALl = "select * from users where username =:us and password =: ps"+",[us:"+u+",ps:"+p+"]";
      //  ResultSet rs = DbConnect.query(findALl);
        String allQuery  = "insert into users (username,password) values ('"+u+"','"+p+"')";
        int rs = 0;
        try {
            rs = DbConnect.querys(allQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(rs == 1){
            System.out.println("into if ");
            try {
                ChatServer.main();
            } catch (Exception e) {
                e.printStackTrace();
            }
           // ChatClient.main();
           // ChatClient.logIns();
        }else{
            System.out.println("into else ");
        }
        }
        public void signIns() throws Exception {
            DbConnect.connection();
            ChatServer.main();

        }
        public static int  checkUsers(String username) throws SQLException, ClassNotFoundException {
        String queries  = "select * from users where username ="+"'"+username+"'";
            DbConnect.connection();
            ResultSet rs = DbConnect.query(queries);
            // checking if ResultSet is empty
            if (rs.next() == false) { System.out.println("no user ");
            return 0;}
            else{
                System.out.println("yes user");
                return 1;
            }
        }
    public void signIn(String u, String p) throws Exception {
        System.out.println("check 1");
        DbConnect.connection();
       // String query = "select id, username, password from Users where username="+"jagriti"+" and password="+"jags";
        String selectQuery = "select * ";
        String fromQuery = " from users ";
        //String whereQuery = " where username ='jagriti' and password ='jags' ";
        String whereQuery = " where username ='"+u+"' and password ='"+p+"' ";
        //String[] params = {"jagriti", "jags"};
      //  String params = " u : jagriti, p :jags ";
        String allQuery = selectQuery+fromQuery+whereQuery;
        System.out.println(allQuery);
        ResultSet rs = DbConnect.query(allQuery);
        if(rs != null){
            System.out.println("yes there is data ");
          //  ChatServer.startProcess();
           // ChatClient cs = new ChatClient(u,"localhost");
           // ChatClient.newPage();
            /*while (rs.next()) {
                int id = rs.getInt(1);
                String uname = rs.getString(2);
                String pasw = rs.getString(3);
                System.out.println("id = "+id+" username = "+uname+" password = "+pasw);
                 com.abyj.Chat.ChatServer.startProcess();
                com.abyj.Chat.ChatClient.newPage();
                //com.abyj.Chat.ChatClient cs = new com.abyj.Chat.ChatClient(uname,"jagriti");
              //  cs.buildInterface();

            }*/
        }else{
            System.out.println("go back");
        }
        DbConnect.closeFiles();
    }
}
