package com.xzp.imp;

import com.xzp.dao.MySQLConnect;
import com.xzp.dao.XAddressDAO;
import com.xzp.entity.Addresses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class XAddressDAOImp implements XAddressDAO {

    private boolean flag;
    private Connection conn = null;
   public XAddressDAOImp(Connection conn){
       this.conn = conn;
   }
    @Override
    public boolean doCreate(Addresses aAddr) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO address (province,city,county,village,detail) VALUES" +
                "(?,?,?,?,?)";
        try{
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,aAddr.getProvince());
            stmt.setString(2,aAddr.getCity());
            stmt.setString(3,aAddr.getCounty());
            stmt.setString(4,aAddr.getVillage());
            stmt.setString(5,aAddr.getDetail());
            int count = stmt.executeUpdate();
            if(count > 0){
                flag = true;
            }else {
                flag = false;
            }
        }catch (SQLException e){

        }finally {
            stmt.close();
        }
        return flag;
    }

    @Override
    public Addresses selectAddrById(int aId) throws SQLException {
       PreparedStatement stmt = null;
       String sql = "SELECT * FROM address where id = ?";
        Addresses res = null;
       try{
           stmt = this.conn.prepareStatement(sql);
           stmt.setInt(1,aId);
          res = (Addresses) stmt.executeQuery();
       }catch (SQLException e){
           Logger.getGlobal().warning(e.getMessage());
       }finally {
           stmt.close();
       }
        return res;
    }

    @Override
    public ArrayList<Addresses> selectAddrSById(int[] ids) throws SQLException {
        return null;
    }

    @Override
    public boolean doUpdate(Addresses aAddr) throws SQLException {
        return false;
    }

    @Override
    public boolean doDelete(int aId) throws SQLException {
        return false;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Addresses addr = new Addresses();
        addr.setId(8);
        addr.setCity("成都市");
        addr.setCounty("双流县");
        addr.setProvince("四川省");
        addr.setVillage("文星镇");
        addr.setDetail("四川大学江安校区");
        XAddressDAO  a= new XAddressDAOImp(MySQLConnect.getConnection());
       try {
           Addresses b = a.selectAddrById(11);
           System.out.println(b.getDetail());
       }catch (SQLException e){
           System.out.println("hash");
       }
    }
}
