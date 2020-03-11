package com.xzp.servlet.info;

import com.xzp.entity.Addresses;
import com.xzp.entity.Users;
import com.xzp.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/info/addrchange")
public class AddrChange extends HttpServlet {
    private DAOFactory factory = null;

    @Override
    public void init() throws ServletException {
        factory = DAOFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String addrId = req.getParameter("addrId");
        String province = req.getParameter("province");
        String city = req.getParameter("city");
        String county = req.getParameter("county");
        String village = req.getParameter("village");
        String detail  = req.getParameter("detail");
        Cookie[] cookies = req.getCookies();
        Boolean flag = false;
        if(cookies == null){
            out.write(req.getContextPath());
        }
        for(int i = 0;cookies!=null&&i<cookies.length;i++){
            String name = cookies[i].getName();
            String value = cookies[i].getValue();
            if(name.equals("addrs")){
               String[] args = value.split("_");
               for(int j=0;j<args.length;j++){
                   if(args[j].equals(addrId)){
                       flag = true;
                       break;
                   }
               }
            }
        }
        if(flag == false){
            out.write("fail");
            return;
        }
        Addresses aAddr = new Addresses();
        aAddr.setProvince(province);
        aAddr.setCounty(county);
        aAddr.setCity(city);
        aAddr.setVillage(village);
        aAddr.setDetail(detail);
        aAddr.setId(Integer.valueOf(addrId));
        Boolean f = false;
       try{
           f = factory.getAddressDeal().doUpdate(aAddr);
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
       if(f == false){
           out.write("fail");
       }else {
           out.write("success");
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
