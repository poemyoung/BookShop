package com.xt.dao;

import com.xt.entity.UserInfo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
    private Connection conn;
    private BaseDao dao;
    public BaseDao(Connection conn){
        this.conn = conn;
    }
    public ArrayList<UserInfo>doExcute(String sql) throws SQLException {
        ArrayList<UserInfo> list = new ArrayList<UserInfo>();
        PreparedStatement stmt = null;
        try {
           stmt = this.conn.prepareStatement(sql);
           ResultSet res =  stmt.executeQuery(sql);
           while (res.next()){
               UserInfo info = new UserInfo();
               info.setUsername(res.getString(1));
               info.setPassword(res.getString(2));
               info.setEmail(res.getString(3));
               list.add(info);
           }

           res.close();
        }catch (SQLException e){
           System.out.println(e);
        }finally {
            if(stmt!=null){
                stmt.close();
            }
        }
        if(list.size()>0) {
            return list;
        }
        else {
            System.out.println("error");
            return list;
        }
    }
}
