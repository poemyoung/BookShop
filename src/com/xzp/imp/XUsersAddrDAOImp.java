package com.xzp.imp;

import com.xzp.dao.MySQLConnect;
import com.xzp.dao.XUserAddrDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class XUsersAddrDAOImp implements XUserAddrDAO {
    private Connection conn = null;
    public XUsersAddrDAOImp(Connection con){
        this.conn = con;
    }
    /**
     *
     * @param userId
     * @param addrId
     * @return boolean
     * @description 创建用户和数据表的关系
     */
    @Override
    public boolean doCreate(int userId, int addrId) {
        String sql = "INSERT INTO userAddr VALUES (?,?)";
        Boolean flag = false;
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,userId);
            stmt.setInt(2,addrId);
           int count =  stmt.executeUpdate();
           if(count > 0){
               flag = true;
           }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
        }
        return flag;
    }

    @Override
    public boolean doDeleteByUserId(int userId) {
        String sql = "DELETE FROM userAddr WHERE userid = ?";
        int count;
        Boolean flag = false;
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,userId);
            count = stmt.executeUpdate();
            if(count > 0){
                flag = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return flag;
    }

    @Override
    public boolean doDeleteByAddrId(int addrId) {
        String sql = "DELETE FROM userAddr WHERE addrid = ?";
        int count;
        Boolean flag = false;
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,addrId);
            count = stmt.executeUpdate();
            if(count > 0){
                flag = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return flag;
    }

    @Override
    public ArrayList<Integer> selectById(int userId) throws SQLException {
        String sql = "SELECT * FROM userAddr WHERE userid = ?";
        ArrayList<Integer>allInt = new ArrayList<Integer>();
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,userId);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                allInt.add(res.getInt(2));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return allInt.size()>0? allInt : null;
    }
}
