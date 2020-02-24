package com.xzp.imp;

import com.mysql.cj.MysqlConnection;
import com.xzp.dao.MySQLConnect;
import com.xzp.dao.XUsersDAO;
import com.xzp.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class XUsersDAOImp implements XUsersDAO {
   private Connection conn = null;
   public  XUsersDAOImp(Connection con){
       this.conn = con;
   }
    @Override
    public boolean doCreate(Users aUser) throws SQLException {
       String sql = "INSERT INTO users(name,password,phone) VALUES (?,?,?)";
       int count;
       Boolean flag = false;
       try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
               stmt.setString(1,aUser.getName());
               stmt.setString(2,aUser.getPassword());
              stmt.setString(3,aUser.getPhone());
              if(!checkPhone(aUser.getPhone())){
                  count = stmt.executeUpdate();
                  if(count > 0){
                      flag = true;
                  }
              }
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
        return flag;
    }

    @Override
    public boolean doCheckLogin(String phone, String UserPwd) throws SQLException {
       String sql = "SELECT * FROM users WHERE phone = ?";

       try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
           stmt.setString(1,phone);

       }

        return false;
    }

    @Override
    public boolean doUpdate(Users aUser) throws SQLException {
        return false;
    }

    @Override
    public Users selectById(int aId) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Users> selectAll() throws SQLException {
        return null;
    }

    @Override
    public Users selectByName(String aName) throws SQLException {
        return null;
    }

    @Override
    public boolean doDelete(int aId) {
        return false;
    }

    @Override
    public boolean checkPhone(String phone){
       Boolean flag = false;
       String sql = "SELECT * FROM users WHERE phone = ?";
       try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
           stmt.setString(1,phone);
           ResultSet res = stmt.executeQuery();
           if(res.next()){
               flag = true;
           }
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
       return flag;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        XUsersDAOImp test = new XUsersDAOImp(MySQLConnect.getConnection());
        Users auser = new Users();
        auser.setName("Xu Zepeng");
        auser.setPassword("123456");
        auser.setPhone("13558540729");
       System.out.println(test.doCreate(auser));
    }
}
