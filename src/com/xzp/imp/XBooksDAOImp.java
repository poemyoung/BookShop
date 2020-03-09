package com.xzp.imp;

import com.xzp.dao.MySQLConnect;
import com.xzp.dao.XBooksDAO;
import com.xzp.entity.Books;

import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;

public class XBooksDAOImp implements XBooksDAO {
   private Connection conn = null;
   private Boolean flag = null;
    public XBooksDAOImp(Connection conn){
       this.conn = conn;
   }
    /**
     *
     * @param aBook
     * @return boolean
     * @throws SQLException
     * @deSCRIPTION 添加图书
     */
    @Override
    public boolean doCreate(Books aBook) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO books(name,price, image, stock, publisher)" +
                "VALUES (?,?,?,?,?)";
        try{
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,aBook.getName());
            stmt.setString(2,aBook.getPrice());
            stmt.setString(3,aBook.getImage());
            stmt.setInt(4,aBook.getStock());
            stmt.setString(5,aBook.getPublisher());
            int count = stmt.executeUpdate();
            if(count > 0){
                this.flag = true;
            }else {
                this.flag = false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            stmt.close();
        }
        return this.flag;
    }

    /**
     * @param aId
     * @return
     * @throws SQLException
     * @description 删除
     */
    @Override
    public boolean doDelete(int aId) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM books WHERE id = ?";
        try {
            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1,aId);
            int count = stmt.executeUpdate();
            if(count > 0){
                this.flag = true;
            }else {
                this.flag = false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            stmt.close();
        }
        return flag;
    }

    /**
     * @param aBook
     * @return boolean
     * @throws SQLException
     * @description 更新书籍信息
     */
    @Override
    public boolean doUpdate(Books aBook) throws SQLException {
        PreparedStatement stmt = null;
        String sql1 = "DELETE FROM books WHERE id = ?";
        String sql2 = "INSERT INTO books(id, name,price, image, stock, publisher)" +
                "VALUES (?,?,?,?,?,?)";
        try {
            this.conn.setAutoCommit(false);
            Savepoint a = this.conn.setSavepoint();
            stmt = this.conn.prepareStatement(sql1);
            stmt.setInt(1,aBook.getId());
            stmt.executeUpdate();
            stmt = this.conn.prepareStatement(sql2);
            stmt.setInt(1,aBook.getId());
            stmt.setString(2,aBook.getName());
            stmt.setString(3,aBook.getPrice());
            stmt.setString(4,aBook.getImage());
            stmt.setInt(5,aBook.getStock());
            stmt.setString(6,aBook.getPublisher());
            stmt.executeUpdate();
            try{
                this.conn.commit();
                this.flag = true;
            }catch (Exception e){
                conn.rollback();
                this.flag = false;
            }
        }catch (SQLException e){
            this.flag = false;
            conn.rollback();
        }finally {
            this.conn.setAutoCommit(true);
            stmt.close();
        }
        return this.flag;
    }

    @Override
    public Books selectById(int aId) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?";
        PreparedStatement stmt = null;
        Books abk = null;
       try {
           stmt = this.conn.prepareStatement(sql);
           stmt.setInt(1, aId);
           ResultSet res = stmt.executeQuery();
           if(res.next()) {
               abk = new Books();
               abk.setId(res.getInt(1));
               abk.setName(res.getString(2));
               abk.setPrice(res.getString(3));
               abk.setImage(res.getString(4));
               abk.setStock(res.getInt(5));
               abk.setPublisher(res.getString(6));
           }
       }catch (SQLException e){
           e.getMessage();
       }finally {
           stmt.close();
       }

        return abk;
    }

    @Override
    public ArrayList<Books> selectByName(String name) throws SQLException {
        String sql = "SELECT * FROM books WHERE name LIKE ?";
        PreparedStatement stmt = null;
        String para = "%"+name+"%";
        ArrayList<Books>bks = new ArrayList<Books>();
        try {
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,para);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Books abk = new Books();
                abk.setId(res.getInt(1));
                abk.setName(res.getString(2));
                abk.setPrice(res.getString(3));
                abk.setImage(res.getString(4));
                abk.setStock(res.getInt(5));
                abk.setPublisher(res.getString(6));
                bks.add(abk);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            stmt.close();
        }
        return bks;
    }

    @Override
    public ArrayList<Books> selectByPublisher(String publishName) throws SQLException {
        PreparedStatement stmt  = null;
        String sql =  "SELECT * FROM books WHERE publisher = ?";
        ArrayList<Books> bks = new ArrayList<>();
        try{
            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,publishName);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Books abk = new Books();
                abk.setId(res.getInt(1));
                abk.setName(res.getString(2));
                abk.setPrice(res.getString(3));
                abk.setImage(res.getString(4));
                abk.setStock(res.getInt(5));
                abk.setPublisher(res.getString(6));
                bks.add(abk);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            stmt.close();
        }
        return bks;
    }

    @Override
    public ArrayList<Books> selectByIdRange(int start, int end) throws SQLException {
        ArrayList<Books> bks = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE id>=? AND id<=?";
        try(PreparedStatement stmt = this.conn.prepareStatement(sql)){
            stmt.setInt(1,start);
            stmt.setInt(2,end);
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                Books abk = new Books();
                abk.setId(res.getInt(1));
                abk.setName(res.getString(2));
                abk.setPrice(res.getString(3));
                abk.setImage(res.getString(4));
                abk.setStock(res.getInt(5));
                abk.setPublisher(res.getString(6));
                bks.add(abk);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {

        }
        return bks;
    }

    @Override
    public int doCountAll() throws SQLException {
        String sql = "SELECT COUNT(*) FROM books";
        int all = 0;
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                all = res.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return all;
    }
}
