package com.xzp.servlet.info;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description 注销操作，删除相关cookie即可
 */
@WebServlet("/info/logout")
public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //获取cookie
        Cookie[] cookies = req.getCookies();
        PrintWriter out = resp.getWriter();
        if(cookies == null){
            out.write("success");
        }
        for(int i = 0;i < cookies.length;i++){
            //构造一个name相同的cookie并把MaxAge设置为0,表示删除cookie
            Cookie aCookie = new Cookie(cookies[i].getName(),cookies[i].getValue());
            aCookie.setMaxAge(0);
            aCookie.setPath("/BookShop_war_exploded/index.html");
            resp.addCookie(aCookie);
        }
        out.write("success");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
