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

    /**
     *
     * @param aUser
     * @return
     * @throws SQLException
     * @description 用户注册
     */
    @Override
    public boolean doCreate(Users aUser) throws SQLException {
       if(checkPhone(aUser.getPhone())){
           return false;
       }
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

    /**
     *
     * @param phone
     * @param UserPwd
     * @return
     * @throws SQLException
     * @description  登录检查
     */
    @Override
    public boolean doCheckLogin(String phone, String UserPwd) throws SQLException {
       String sql = "SELECT password FROM users WHERE phone = ?";
       Boolean flag = false;
       try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
           stmt.setString(1,phone);
           ResultSet set = stmt.executeQuery();
           if(set.next()){
               String pwd = set.getString(1);
               if(pwd.equals(UserPwd)){
                   flag = true;
               }
           }
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }

        return flag;
    }

    /**
     *
     * @param userName
     * @return boolean
     * @description 更新昵称
     */

    @Override
    public boolean updateName(int id, String userName) {
        String sql = "UPDATE users SET name=? WHERE id=?";
        Boolean flag = false;
        try(PreparedStatement stmt  = this.conn.prepareStatement(sql)){
            stmt.setString(1, userName);
            stmt.setInt(2,id);
            int count = stmt.executeUpdate();
            if(count > 0){
                flag = true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean updatePassword(int id, String pwd) {
        String sql = "UPDATE users SET password=? WHERE id=?";
        Boolean flag = false;
        try(PreparedStatement stmt  = this.conn.prepareStatement(sql)){
            stmt.setString(1,pwd);
            stmt.setInt(2,id);
            int count = stmt.executeUpdate();
            if(count > 0){
                flag = true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean updatePhone(int id, String userPhone) {
        String sql = "UPDATE users SET phone=? WHERE id=?";
        Boolean flag = false;
        try(PreparedStatement stmt  = this.conn.prepareStatement(sql)){
            stmt.setString(1,userPhone);
            stmt.setInt(2,id);
            int count = stmt.executeUpdate();
            if(count > 0){
                flag = true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    /**
     *
     * @param phone
     * @return
     * @throws SQLException
     * @description 按照id查找用户，即登录
     */
    @Override
    public Users selectByPhone(String phone)  {
        String sql = "SELECT * FROM users WHERE phone = ?";
        Users aUser = null;
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setString(1,phone);
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                aUser = new Users();
                aUser.setId(res.getInt(1));
                aUser.setName(res.getString(2));
                aUser.setPassword(res.getString(3));
                aUser.setPhone(res.getString(4));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return aUser;
    }

    /**
     *
     * @param aId
     * @return
     * @description 用户注销
     */
    @Override
    public boolean doDelete(int aId) {
        String sql = "DELETE FROM users WHERE id=?";
        Boolean flag = false;
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,aId);
            int count = stmt.executeUpdate();
            if(count > 0){
                flag = true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    /**
     * @param phone
     * @return 如果已存在用户返回true，不存在该用户返回false
     */
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

}
