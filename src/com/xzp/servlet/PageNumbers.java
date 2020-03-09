package com.xzp.servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xzp.factory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/pagenumbers")
public class PageNumbers extends HttpServlet {
    private DAOFactory factory = null;
    @Override
    public void init() throws ServletException {
        factory = DAOFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("pageNum");
        double pageNum = Double.parseDouble(page);
        PrintWriter out = resp.getWriter();
        int allBooks = 0;
        try{
             allBooks = factory.getBooksDeal().doCountAll();
        }catch (SQLException e){
            out.write("null");
        }
        JSONArray jArr = new JSONArray();
        for(int i=1;i<=Math.ceil(allBooks/pageNum);i++){
            JSONObject jObj = new JSONObject();
            jObj.put("num",i+"");
            jArr.add(jObj);
        }
        out.write(jArr.toJSONString());
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
