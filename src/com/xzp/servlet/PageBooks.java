package com.xzp.servlet;

import com.alibaba.fastjson.*;
import com.xzp.dao.XBooksDAO;
import com.xzp.entity.Books;
import com.xzp.factory.DAOFactory;
import com.xzp.imp.XBooksDAOImp;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class PageBooks extends HttpServlet {
    private DAOFactory factory = null;
    private ArrayList<Books> bks = null;
    @Override
    public void init() throws ServletException {
       factory = DAOFactory.getInstance();
       bks = new ArrayList<Books>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try{
            String pageNo= req.getParameter("pageNo");
           String pageNum = req.getParameter("pageNum");
           int pN = Integer.parseInt(pageNo);
           int pNum = Integer.parseInt(pageNum);
           //设置返回前端的格式
           resp.setContentType("text/html;charset=UTF-8");
           resp.setCharacterEncoding("UTF-8");
           XBooksDAO bookDeal = factory.getBooksDeal();
           //计算数据库的书本总数
           int allBooks = bookDeal.doCountAll();
           PrintWriter out = resp.getWriter();
           if(pN<1){
               out.write("null");
               return;
           }
           if((pN-1)*pNum >= allBooks){
                out.write("null");
           }
           bks = bookDeal.selectByIdRange((pN-1)*pNum+1,(pN)*pNum);
           JSONArray jArr = new JSONArray();
           for(int i=0;i<bks.size();i++){
            JSONObject jObj = new JSONObject();
            jObj.put("name",bks.get(i).getName());
            jObj.put("publisher",bks.get(i).getPublisher());
            jObj.put("image",bks.get(i).getImage());
            jObj.put("stock",bks.get(i).getStock());
            jObj.put("price",bks.get(i).getPrice());
            jObj.put("id",bks.get(i).getId());
            jArr.add(jObj);
           }
           out.write(jArr.toJSONString());
           bks.clear();
           out.close();
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
    }
}
