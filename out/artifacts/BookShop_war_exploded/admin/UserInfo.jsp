<%--
  Created by IntelliJ IDEA.
  User: xzp
  Date: 2020/2/12
  Time: 8:55 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basepath = request.getScheme() + "://"
            +request.getServerName()+":"+request.getServerPort()
            +path+"/";
%>
<html>
<head>
    <title>用户信息注册</title>
</head>
<body>
    <a href="../UserinfoAction?action=xt">点击获取一些信息</a>
    <p><%=basepath%></p>
</body>
</html>
