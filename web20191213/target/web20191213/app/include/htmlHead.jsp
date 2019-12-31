<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/13
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cmn-Hans">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
</head>
<body>

<div class="jumbotron text-center"  style="margin-bottom:0;background-size: cover;-moz-background-size: cover;width: 100%;height: 171px;background: url('https://img.tukuppt.com/bg_grid/00/03/30/DSTRZ3a11t.jpg!/fh/350') no-repeat center;background-size: cover;">
    <%--<h1>在线学校网站</h1>--%>
    <p>在线答疑</p>
    <span><a href="${pageContext.request.contextPath}/app/include/signIn.jsp" target="_blank" class="btn btn-primary btn-lg">登录</a></span>
    <span><a href="${pageContext.request.contextPath}/SignOutServlet" target="_blank" class="btn btn-danger btn-lg">注销</a></span>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${pageContext.request.contextPath}/bootstrap/others/jquery.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/others/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
