package com.xt.bll;

import com.xt.dao.*;
import com.xt.entity.UserInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.logging.Logger;

public class UserBll {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String sql = "select * from userinfo";
        UserBll a = new UserBll();
        UserDao b = new UserDao();
        a.setUserDao(b);
        ArrayList<UserInfo> list = a.userExists();
    }

    public UserBll() {

    }

    public UserDao getUserDao() {
        return userdao;
    }

    public void setUserDao(UserDao userfao) {
        this.userdao = userfao;
        System.out.println(userdao);
    }

    private UserDao userdao;

    /**
     * 查询所有用户
     */
    public ArrayList<UserInfo> userExists() throws SQLException, ClassNotFoundException {
        String sql = "select * from userinfo";
        ArrayList<UserInfo> list;
        System.out.println(userdao);
        list = userdao.query(sql);
        System.out.println("this list is " + list.size());
        return list;
    }

    /**
     * 登陆
     */
    public boolean checkLogin(String username, String userpassword) throws SQLException, ClassNotFoundException {
        String sql = "select * from userinfo" + "where name =" + username + "and password=" + userpassword + ";";
        List<UserInfo> list = userdao.query(sql);
        return list.size() > 0 ? true : false;
    }

    @Override
    public String toString() {
        if (this.userdao == null) {
            return "UserDAO is empty";
        } else {
            return "user Dao is not empty!";
        }
    }
}
