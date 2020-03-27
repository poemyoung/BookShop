package com.xzp.imp;

import com.xzp.dao.MySQLConnect;
import com.xzp.dao.XOrdersDAO;
import com.xzp.entity.Orders;

import java.sql.*;

public class XOrdersDAOImp implements XOrdersDAO {
    private Connection conn = null;

    /**
     *
     * 构造函数
     */
    public XOrdersDAOImp(Connection conn){
        this.conn = conn;
    }

    /**
     * 添加图书
     * @param order
     * @return
     */
    @Override
    public int doCreate(Orders order) {
        String sql = "INSERT INTO orders(userid,addrid,count,state,total_price) " +
                "VALUES (?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setInt(1,order.getUserId());
            stmt.setInt(2,order.getAddrId());
            stmt.setInt(3,order.getCount());
            stmt.setInt(4,order.getState());
            stmt.setString(5,order.getTotal_price());
            int count = stmt.executeUpdate();
            if(count > 0){
                ResultSet res = stmt.getGeneratedKeys();
                if(res.next()){
                    return res.getInt(1);
                }
            }else {
                return 0;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public boolean doDelete(int id) {
        String sql = "DELETE FROM orders WHERE id=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            int count = stmt.executeUpdate();
            if(count > 0){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Orders doSelect(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        Orders aOrder = new Orders();
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1,id);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                aOrder.setId(res.getInt(1));
                aOrder.setUserId(res.getInt(2));
                aOrder.setAddrId(res.getInt(3));
                aOrder.setCount(res.getInt(4));
                aOrder.setState(res.getInt(5));
                aOrder.setTotal_price(res.getString(6));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        return aOrder;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        XOrdersDAO dao = new XOrdersDAOImp(MySQLConnect.getConnection());
        Orders a =  new Orders();
        a.setAddrId(1);
        a.setCount(2);
        a.setId(2);
        a.setState(0);
        a.setTotal_price("32.5");
        a.setUserId(1);
       System.out.println(dao.doSelect(1).toString());
    }
}
