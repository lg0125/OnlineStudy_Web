<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/13
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-cmn-Hans">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
</head>
<body>
<%@include file="htmlHead.jsp"%>
<%@include file="indexMenu.jsp"%>

<div class="container">
    <h2>登录</h2>
    <form action="${pageContext.request.contextPath}/SignInServlet" method="post">
        <div class="form-group">
            <label for="id">id:</label>
            <input type="text" class="form-control" id="id" name="id" placeholder="Enter id" value="${cookie.id.value}" />
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password"  placeholder="Enter password" value="${cookie.password.value}">
        </div>
        <button type="submit" class="btn btn-primary mb-3">SignIn</button>
        <a href="${pageContext.request.contextPath}/GoSignUpServlet" class="btn btn-danger mb-3">SignUp</a>
    </form>
</div>

<%@include file="htmlFoot.jsp"%>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${pageContext.request.contextPath}/bootstrap/others/jquery.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/others/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>

