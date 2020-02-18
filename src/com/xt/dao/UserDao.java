package com.xt.dao;
import java.sql.*;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.Iterator;

import com.xt.entity.UserInfo;

public class UserDao {

    private String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private String User = "root";
    private String pwd = "s13558540729cu";
    private String DB_URL = "jdbc:mysql://localhost:3306/userLogin";
    private Connection conn;
    private Statement state;
    private BaseDao dao = null;

    public UserDao() {

    }
    public ArrayList<UserInfo> query(String sql) throws SQLException, ClassNotFoundException {
        Class.forName(jdbc_driver);
        Connection conn = DriverManager.getConnection(DB_URL,User,pwd);
        dao = new BaseDao(conn);
        ArrayList<UserInfo>re = null;
        try{
           re = dao.doExcute(sql);
        }catch (SQLException e){
            System.out.println("hhhh");
        }finally {
            conn.close();
        }
        return re;
    }
}
