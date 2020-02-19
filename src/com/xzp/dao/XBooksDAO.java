package com.xzp.dao;
import com.xzp.entity.Books;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *@description 对书本的操作，有增、删、改、查（书名、价格）
 */
public interface XBooksDAO {
    public boolean doCreate(Books aBook) throws SQLException;
    public boolean doDelete(int aId) throws SQLException;
    public boolean doUpdate(Books aBook) throws SQLException;
    public Books selectById(int aId) throws SQLException;
    public ArrayList<Books>selectByName(String name) throws SQLException;
    public ArrayList<Books>selectByPublisher(String publishName) throws SQLException;
    public ArrayList<Books>selectByPriceScope(double min, double max) throws SQLException;
}
