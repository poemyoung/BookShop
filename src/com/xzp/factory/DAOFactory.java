package com.xzp.factory;

import com.xzp.dao.*;
import com.xzp.imp.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DAOFactory {
    private static  Connection conn;
    private DAOFactory() throws SQLException, ClassNotFoundException {
           conn = MySQLConnect.getConnection();
    }
    public static DAOFactory getInstance(){
       try{
           DAOFactory a = new DAOFactory();
           return a;
       }catch (SQLException e){
           Logger.getGlobal().warning("conn有问题");
           return null;
       }catch (ClassNotFoundException m){
           Logger.getGlobal().warning("CLASS NOT FOUND!!");
           return null;
       }
    }
    public XAddressDAO getAddressDeal(){
           XAddressDAO addr = new XAddressDAOImp(conn);
           return addr;
       }
       public  XBooksDAO getBooksDeal(){
            XBooksDAO bk = new XBooksDAOImp(conn);
            return bk;
       }
       public  XItemsDAO getItemsDeal(){
            XItemsDAO itm = new XItemsDAOImp(conn);
            return itm;
       }
       public  XUsersDAO getUsersDeal(){
            XUsersDAO usr = new XUsersDAOImp(conn);
            return usr;
       }
       public  XUserAddrDAO getUserAddrDeal(){
            XUserAddrDAO uAddr = new XUsersAddrDAOImp(conn);
            return uAddr;
       }
}
