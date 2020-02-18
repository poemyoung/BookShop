package com.xt.servlet;
import com.xt.bll.UserBll;
import com.xt.dao.UserDao;
import com.xt.entity.UserInfo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserinfoAction extends HttpServlet{
    public void destory(){
        super.destroy();
        //here is my code???
    }
   private UserBll userbiz;
    private UserDao userdao = null;
    //接受一个请求，并回以一个响应
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");  //get请求获取参数
        String jsp = "/admin/Userinfo.jsp";
        if((action == "null")||(action.length()<1)){
            action = "default";
        }else if ("default".equals(action)){
            jsp = "/admin/userinfo.jsp";
        }else if("xt".equals(action)){
            ArrayList <UserInfo> list = new ArrayList<UserInfo>();
            try {
                list = userbiz.userExists();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            request.setAttribute("userinfo",list);
            jsp = "/admin/UserDisplayList.jsp";
            System.out.println(action + "action");
            System.out.println(list + "list");
        }

        RequestDispatcher re = this.getServletContext().getRequestDispatcher(jsp); //页面切换
        re.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        this.doGet(req,resp);
    }

    public void init() throws ServletException {
        UserDao userdao = new UserDao();
        userbiz = new UserBll();
        userbiz.setUserDao(userdao);
        System.out.println(userbiz);
    }
}
