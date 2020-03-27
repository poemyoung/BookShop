package com.xzp.servlet.buy;

import com.xzp.entity.Orders;
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

@WebServlet("/pay")
public class Pay extends HttpServlet {
    private DAOFactory factory;
    public Pay(){
        factory = DAOFactory.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        //获取参数
        String bookId = req.getParameter("bookId");
        int id  = Integer.parseInt(bookId);
        String countStr = req.getParameter("count");
        int count = Integer.parseInt(countStr);
        //获取cookie
        Cookie[] cookies = req.getCookies();
        if(cookies == null){
            out.write("fail");
            return;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("phone")){
                out.write("success");
                //订单记录写入数据库
                Orders aOrder = new Orders();
                try{
                    Users a =factory.getUsersDeal().selectByPhone(cookie.getValue());
                    aOrder.setUserId(a.getId());
                    aOrder.setTotal_price(Integer.parseInt(factory.getBooksDeal().selectById(id).getPrice())*count+"");
                    aOrder.setState(1);
                    aOrder.setCount(count);
                    aOrder.setAddrId(1);
                    factory.getOrdersDeal().doCreate(aOrder);
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                return;
            }
        }
        out.write("fail");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
