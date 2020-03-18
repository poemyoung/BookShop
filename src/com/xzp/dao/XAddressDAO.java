package com.xzp.dao;

import com.xzp.entity.Addresses;

import java.sql.SQLException;
/**
 * @description 对地址的操作，有增删改查（一个id,一堆id)。
 */
import java.util.ArrayList;

public interface XAddressDAO {
    public int doCreate(Addresses aAddr) throws SQLException;
    public Addresses selectAddrById(int aId) throws SQLException;
    public ArrayList<Addresses> selectAddrSById(int[] ids) throws SQLException;
    public boolean doUpdate(Addresses aAddr) throws SQLException;
    public boolean doDelete(int aId) throws SQLException;
}
