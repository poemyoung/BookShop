package com.xzp.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xzp.entity.Books;
import com.xzp.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/findbook")
public class FindBook extends HttpServlet {
    private DAOFactory factory;
    @Override
    public void init() throws ServletException {
        factory = DAOFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //获取参数
        String idString = req.getParameter("bookId");
        int id = Integer.parseInt(idString);
        PrintWriter out = resp.getWriter();
        Books bks = null;
        try{
           bks =  this.factory.getBooksDeal().selectById(id);
        }catch (SQLException e){
            out.write("fail");
        }
        JSONObject jobj = new JSONObject();
        jobj.put("name",bks.getName());
        jobj.put("id",bks.getId());
        jobj.put("imgUrl",bks.getImage());
        jobj.put("publisher",bks.getPublisher());
        jobj.put("price",bks.getPrice());
        out.write(jobj.toJSONString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
