package com.xzp.servlet.login;

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

@WebServlet("/login/logincheck")
public class LoginCheck extends HttpServlet {
    private DAOFactory factory = null;

    @Override
    public void init() throws ServletException {
        factory = DAOFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = new Users();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
    PrintWriter out = resp.getWriter();
        String phone = req.getParameter("phone");
        String pwd = req.getParameter("password");
        Boolean isRemember = false;
        try{
            String remember = req.getParameter("isRemember");
            if(remember == null){
                isRemember = false;
            }else {
                isRemember = true;
            }
        }catch (NullPointerException e) {
            out.write("Not ok");
        }
        if(!phone.matches("[0-9]{11}")){
            out.write("手机号格式不对");
            return;
        }
        try {
            if (factory.getUsersDeal().doCheckLogin(phone, pwd)){
                out.write("success");
            }else {
                out.write("fail");
                return;
            }
        }catch (SQLException e){
            out.write(e.getMessage());
            return;
        }
        try{
            user = factory.getUsersDeal().selectByPhone(phone);
        }catch (SQLException e){
            out.write(e.getMessage());
            return;
        }
        if(isRemember){
            Cookie cookie1 = new Cookie("phone",user.getPhone()+"");
            cookie1.setMaxAge(1000);
            resp.addCookie(cookie1);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
