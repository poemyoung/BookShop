package com.xzp.imp;

import com.xzp.dao.MySQLConnect;
import com.xzp.dao.XItemsDAO;
import com.xzp.entity.Items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class XItemsDAOImp implements XItemsDAO {
   private Connection conn = null;
   private Boolean flag = null;
    public  XItemsDAOImp(Connection connection){
        this.conn = connection;
    }
    /**
     *
     * @param array
     * @return boolean
     * @description 一个订单可以创建多个清单项
     * @throws SQLException
     */
    @Override
    public boolean doCreateMany(ArrayList<Items> array) throws SQLException {
       String sql = "INSERT INTO items VALUES (?,?,?,?,?)";
        int count = 0;
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            for (Items a : array) {
                stmt.setInt(1, a.getOrderId());
                stmt.setInt(2, a.getBookId());
                stmt.setInt(3, a.getCount());
                stmt.setString(4, a.getPrice());
                stmt.setString(5, a.getTotal_price());
                count = stmt.executeUpdate();
                if (count == 0) {
                    return false;
                }
            }
            this.flag = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.flag = false;
        }
        return flag;
    }

    @Override
    public boolean doDelete(int orderId) throws SQLException {
        String sql = "DELETE FROM items WHERE orderid = ?";
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,orderId);
            int count  = stmt.executeUpdate();
            if(count > 0){
                this.flag = true;
            }else {
                this.flag = false;
            }
        }catch (SQLException e){
            this.flag = false;
        }
        return this.flag;
    }

    @Override
    public ArrayList<Items> selectByOrderId(int orderId) throws SQLException {
        String sql = "SELECT * FROM items WHERE orderid = ?";
        ArrayList<Items> items = new ArrayList<Items>();
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,orderId);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Items itm  = new Items();
                itm.setOrderId(res.getInt(1));
                itm.setBookId(res.getInt(2));
                itm.setCount(res.getInt(3));
                itm.setPrice(res.getString(4));
                itm.setTotal_price(res.getString(5));
                items.add(itm);
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return items.size() > 0?items:null;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ArrayList<Items> itms = new ArrayList<Items>();
        Items aItem = new Items();
        aItem.setOrderId(1);
        aItem.setBookId(1);
        aItem.setCount(2);
        aItem.setPrice("119");
        aItem.setTotal_price("238");
        itms.add(aItem);
        Items bItem = new Items();
        bItem.setOrderId(1);
        bItem.setBookId(5);
        bItem.setCount(8);
        bItem.setPrice("79");
        itms.add(bItem);
        XItemsDAOImp imp = new XItemsDAOImp(MySQLConnect.getConnection());
        try{
            System.out.println(imp.selectByOrderId(1).get(0).getTotal_price());
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {

        }
    }
}
