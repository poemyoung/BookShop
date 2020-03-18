package com.xzp.imp;

import com.xzp.dao.MySQLConnect;
import com.xzp.dao.XAddressDAO;
import com.xzp.entity.Addresses;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @Author XZP FROM SCU
 */
public class XAddressDAOImp implements XAddressDAO {

    private boolean flag;
    private Connection conn = null;
   public XAddressDAOImp(Connection conn){
       this.conn = conn;
   }

    /**
     *
     * @param aAddr
     * @return boolean
     * @throws SQLException
     * @description 插入数据
     */
    @Override
    public int doCreate(Addresses aAddr) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO address (province,city,county,village,detail) VALUES" +
                "(?,?,?,?,?)";
        try{
            stmt = this.conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,aAddr.getProvince());
            stmt.setString(2,aAddr.getCity());
            stmt.setString(3,aAddr.getCounty());
            stmt.setString(4,aAddr.getVillage());
            stmt.setString(5,aAddr.getDetail());
            int count = stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()){
                id = generatedKeys.getInt(1);
            }
            if(count > 0){
                flag = true;
            }else {
                flag = false;
            }
        }catch (SQLException e){

        }finally {
            stmt.close();
        }
        return id;
    }

    /**
     *
     * @param aId
     * @return Address
     * @throws SQLException
     * @description 单个ID选择
     */
    @Override
    public Addresses selectAddrById(int aId) throws SQLException {
       PreparedStatement stmt = null;
       String sql = "SELECT * FROM address where id = ?";
        Addresses resAddr = null;
       try{
           stmt = this.conn.prepareStatement(sql);
           stmt.setInt(1,aId);
          ResultSet res  = stmt.executeQuery();
         if(res.next()){
             resAddr = new Addresses();
             resAddr.setId(res.getInt(1));
             resAddr.setProvince(res.getString(2));
             resAddr.setCity(res.getString(3));
             resAddr.setCounty(res.getString(4));
             resAddr.setVillage(res.getString(5));
             resAddr.setDetail(res.getString(6));
         }
       }catch (SQLException e){
           Logger.getGlobal().warning(e.getMessage());
       }finally {
           stmt.close();
       }
        return resAddr;
    }

    /**
     *
     * @param ids
     * @return ArrayList
     * @throws SQLException
     * @description 多个id选择
     */
    @Override
    public ArrayList<Addresses> selectAddrSById(int[] ids) throws SQLException {
      ArrayList<Addresses> res = new ArrayList<Addresses>();
       for(int i : ids){
           res.add(selectAddrById(i));
       }
        return res;
    }

    /**
     *
     * @param aAddr
     * @return boolean
     * @throws SQLException
     * @
     */
    @Override
    public boolean doUpdate(Addresses aAddr) throws SQLException {
        String sql1="DELETE FROM address WHERE id = ?";
        String sql2 = "INSERT INTO address (id,province,city,county,village,detail) VALUES" +
                "(?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try{
            conn.setAutoCommit(false);
            Savepoint save = conn.setSavepoint();
            stmt = this.conn.prepareStatement(sql1);
            stmt.setInt(1,aAddr.getId());
            stmt.executeUpdate();
            stmt = this.conn.prepareStatement(sql2);
            stmt.setInt(1,aAddr.getId());
            stmt.setString(2,aAddr.getProvince());
            stmt.setString(3,aAddr.getCity());
            stmt.setString(4,aAddr.getCounty());
            stmt.setString(5,aAddr.getVillage());
            stmt.setString(6,aAddr.getDetail());
            stmt.executeUpdate();
           try {
               conn.commit();
               this.flag = true;
           }catch (Exception e){
               this.conn.rollback();
               this.flag = false;
           }
        }catch (SQLException e) {
            this.flag = false;
           conn.rollback();
        }finally {
            conn.setAutoCommit(true);
            stmt.close();
        }
        return flag;
    }

    /**
     *
     * @param aId
     * @return boolean
     * @throws SQLException
     * @description 删除地址记录
     */
    @Override
    public boolean doDelete(int aId) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM address WHERE id = ?";
       try {
           stmt = this.conn.prepareStatement(sql);
           stmt.setInt(1, aId);
           int count = stmt.executeUpdate();
           if(count > 0)
           this.flag = true;
           else{
               this.flag = false;
           }
       }catch (SQLException e){
           stmt.close();
           System.out.println(e.getMessage());
       }
        return this.flag;
    }
}
