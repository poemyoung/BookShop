package com.xzp.servlet.info;

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

@WebServlet("/info/addrdel")
public class AddrDel extends HttpServlet {
    private DAOFactory factory = null;

    @Override
    public void init() throws ServletException {
        factory = DAOFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String id = req.getParameter("addrId");
        boolean flag = false;
        Cookie[] cookies = req.getCookies();
        for (int i=0;i < cookies.length; i++){
            String name = cookies[i].getName();
            String value = cookies[i].getValue();
            if(name.equals("addrs")){
                String[] args = value.split("_");
                for (int j = 0;j < args.length;j++){
                    if(args[j].equals(id)){
                        flag = true;
                    }
                }
            }
        }
        if(flag == false){
            out.write("fail");
            return;
        }
        try{
            boolean isDel = factory.getAddressDeal().doDelete(Integer.parseInt(id));
            boolean isAddrDel = factory.getUserAddrDeal().doDeleteByAddrId(Integer.parseInt(id));
            if(isDel == false||isAddrDel==false){
                out.write("fail");
                return;
            }else if(isDel&&isAddrDel){
                out.write("success");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
