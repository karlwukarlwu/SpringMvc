<%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  Version: 1.0
  Filename: login
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h3>登录页面</h3>

<%--老韩解读
1. <%=request.getContextPath()%>/user/login => /springmvc/user/login
2. /springmvc/user/login => http://localhost:8080/springmvc/user/login
3. 如果小伙伴前面学习的非常扎实，理解没有问题, 如果有点懵, 就回去复习javaweb->web工程路径
--%>
<form action="<%=request.getContextPath()%>/user/login" method="post">
    u:<input name="username" type="text"> <br/>
    p:<input name="password" type="password"><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>