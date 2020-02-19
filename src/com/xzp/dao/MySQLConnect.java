package com.xzp.dao;
import com.mysql.cj.log.Log;

import java.sql.*;
import java.util.logging.Logger;

public class MySQLConnect {

    private static  String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_URL = "jdbc:mysql://localhost:3306/bookshop";
    private static Connection conn = null;
    private static String userName = "root";
    private static String userPassword = "s13558540729cu";
    private static int counter = 0;

    private MySQLConnect() throws ClassNotFoundException, SQLException {

    }
    public static  Connection getConnection() throws SQLException, ClassNotFoundException {
        if(conn == null) {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, userName, userPassword);
            counter++;
            Logger.getGlobal().info("连接了一次,共连接了"+counter+"次数据库");
        }
        return conn;
    }
}
