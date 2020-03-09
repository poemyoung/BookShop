package com.xzp.servlet.login;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

@WebServlet("/login/UserMsg")
public class UserMsg extends HttpServlet {
    private DAOFactory factory = null;
    @Override
    public void init() throws ServletException {
        factory = DAOFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setCharacterEncoding("UTF-8");
       resp.setContentType("text/html;charset=utf-8");
        String phone = req.getParameter("phone");
        PrintWriter out = resp.getWriter();
        Cookie[] cookies = req.getCookies();
        if(cookies == null){
            out.write("fail");
            return;
        }
        for (int i = 0;cookies != null&&i < cookies.length;i++){
          String name = cookies[i].getName();
           String value = cookies[i].getValue();
           if(name == "phone"){
               if(value==phone){
                   continue;
               }else {
                   out.write("fail");
                   return;
               }
           }
        }
        Users aUser = new Users();
       try{
           aUser = factory.getUsersDeal().selectByPhone(phone);
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
        JSONObject jObj = new JSONObject();
       jObj.put("name",aUser.getName());
       jObj.put("phone",aUser.getPhone());
       JSONArray jArr = new JSONArray();
        ArrayList<Integer>addrId = new ArrayList<Integer>();
        try {
            addrId = factory.getUserAddrDeal().selectById(aUser.getId());
        }catch (SQLException e){
            System.out.println(e.getMessage());
            out.write(-1);
        }finally {

        }
        int[] iArr = new int[addrId.size()];
        for (int i = 0;i < addrId.size();i++){
            iArr[i] = addrId.get(i).intValue();
        }
        ArrayList<Addresses> addrs = new ArrayList<Addresses>();
        try{
            addrs = factory.getAddressDeal().selectAddrSById(iArr);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        for (Addresses a:addrs){
            JSONObject aObj = new JSONObject();
            aObj.put("province",a.getProvince());
            aObj.put("city",a.getCity());
            aObj.put("county",a.getCounty());
            aObj.put("village",a.getVillage());
            aObj.put("detail",a.getDetail());
            aObj.put("addrId",a.getId());
            jArr.add(aObj);
        }
        jObj.put("address",jArr);
        out.write(jObj.toJSONString());
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
