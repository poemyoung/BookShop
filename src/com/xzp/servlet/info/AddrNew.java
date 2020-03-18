package com.xzp.servlet.info;

import com.xzp.entity.Addresses;
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

@WebServlet("/info/addrnew")
public class AddrNew extends HttpServlet {
    private DAOFactory factory = null;

    @Override
    public void init() throws ServletException {
        factory = DAOFactory.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String phone = req.getParameter("phone");
        String province = req.getParameter("province");
        String city = req.getParameter("city");
        String county = req.getParameter("county");
        String village = req.getParameter("village");
        String detail = req.getParameter("detail");
        Cookie[] cookies = req.getCookies();
        boolean flag = false;
        for(int i =0; i<cookies.length;i++){
            String name = cookies[i].getName();
            String value = cookies[i].getValue();
            if(name.equals("phone")){
                if(value.equals(phone)){
                    flag = true;
                }
            }
        }
        if (flag == false) {
            out.write("fail");
            return;
        }
        int userId = 0;
        try{
            userId = factory.getUsersDeal().selectByPhone(phone).getId();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        Addresses aAddr = new Addresses();
        aAddr.setProvince(province);
        aAddr.setCounty(county);
        aAddr.setCity(city);
        aAddr.setVillage(village);
        aAddr.setDetail(detail);
        int id = 0;
        try {
            id =factory.getAddressDeal().doCreate(aAddr);
            boolean f = factory.getUserAddrDeal().doCreate(userId,id);
            if(!f){
                out.write("fail");
                return;
            }else {
                out.write("success");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
