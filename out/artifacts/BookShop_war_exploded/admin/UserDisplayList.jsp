<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.xt.entity.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: xzp
  Date: 2020/2/12
  Time: 10:11 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示客户列表</title>
</head>
<body>
    <table border="1" cellpadding="3" cellpadding="3" width="784" height="138">
        <tr>
            <td>用户名</td>
            <td>密码</td>
            <td>邮箱</td>
        </tr>
        <%
            ArrayList list = (ArrayList)request.getAttribute("userinfo");
            Iterator it = list.iterator();
            while (it.hasNext()){
               UserInfo map = (UserInfo) it.next();
        %>
        <tr>
            <td><%=map.getUsername()%></td>
            <td><%=map.getPassword()%></td>
            <td><%=map.getEmail()%></td>
        </tr>
        <%
            }
        %>
    </table>

</body>
</html>
