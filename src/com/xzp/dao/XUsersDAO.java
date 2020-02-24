package com.xzp.dao;

import com.xzp.entity.Users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * @Description 调用users表相关操作时的接口，有增、删改、查（名字，id)、检查（检查是否存在用户）
 */
public interface XUsersDAO {
    public boolean doCreate(Users aUser) throws SQLException;
    public boolean doCheckLogin(String phone, String UserPwd) throws SQLException;
    public boolean doUpdate(Users aUser) throws SQLException;
    public Users selectById(int aId) throws SQLException;
    public ArrayList <Users> selectAll()throws SQLException;
    public Users selectByName(String aName) throws SQLException;
    public boolean doDelete(int aId);
    public boolean checkPhone(String phone);
}
