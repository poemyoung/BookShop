package com.xzp.dao;
import com.xzp.entity.Items;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @description 增(先删再增）删查（orderId)
 */
public interface XItemsDAO {
    public boolean doCreateMany(ArrayList<Items> array) throws SQLException;
    public boolean doDelete(int orderId) throws SQLException;
    public ArrayList<Items> selectByOrderId(int orderId) throws SQLException;
}
