package org.edwith.webbe.guestbook.util;

import java.sql.*;

public class DBUtil {
    public static Connection getConnection(){
        return getConnection("jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8&useSSL=false","connectuser","connect123!@#");
    }

    public static Connection getConnection(String dbURL, String dbId, String dbPassword){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbId, dbPassword);
            return conn;
        }catch(Exception ex){
            throw new RuntimeException("Connection Error");
        }
    }
    // Connection 객체를 만들고 가져오는 함수
}
