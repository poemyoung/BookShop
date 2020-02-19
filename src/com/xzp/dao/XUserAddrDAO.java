package com.xzp.dao;
import java.sql.SQLException;

/**
 * @description 根据用户查找地址，可以增、删、查
 */
public interface XUserAddrDAO {
    public boolean doCreate(int userId, int addrId);
    public boolean doDeleteByUserId(int userId);
    public boolean doDeleteByAddrId(int addrId);
    public int[] selectById(int userId)throws SQLException;
}
