<%--
  Created by IntelliJ IDEA.
  User: 韩顺平
  Version: 1.0
  Filename: interceptor
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试自定义拦截器</title>
</head>
<body>
<h1>测试自定义拦截器</h1>
<a href="<%=request.getContextPath()%>/hi">测试自定义拦截器-hi</a><br><br>
<a href="<%=request.getContextPath()%>/hello">测试自定义拦截器-hello</a><br/><br/>
<a href="<%=request.getContextPath()%>/ok">测试自定义拦截器-ok</a><br><br>
</body>
</html>