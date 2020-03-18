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
        //接收参数
        String addrId = req.getParameter("addrId");
        String province = req.getParameter("province");
        String city = req.getParameter("city");
        String county = req.getParameter("county");
        String village = req.getParameter("village");
        String detail  = req.getParameter("detail");
        //获取cookies
        Cookie[] cookies = req.getCookies();
        //用于判断用户权限
        boolean flag = false;
        //无权限，跳转至登录界面
        if(cookies == null){
            out.write(req.getContextPath());
        }
        //获取cookie中的地址，以下划线分割
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
        //没有权限，请求失败
        if(flag == false){
            out.write("fail");
            return;
        }
        //组装地址的javaBean类存入数据库
        Addresses aAddr = new Addresses();
        aAddr.setProvince(province);
        aAddr.setCounty(county);
        aAddr.setCity(city);
        aAddr.setVillage(village);
        aAddr.setDetail(detail);
        aAddr.setId(Integer.parseInt(addrId));
        Boolean f = false;
        //存入地址的操作
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
