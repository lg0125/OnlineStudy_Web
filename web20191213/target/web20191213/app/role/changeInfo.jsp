<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/15
  Time: 21:03
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

<%@include file="../include/htmlHead.jsp"%>
<%@include file="../include/menu.jsp"%>

<div class="container mb-3">
    <form method="post" action="${pageContext.request.contextPath}/ChangeInfoServlet">
        <div class="form-group">
            <label for="oldPassword">oldPassword</label>
            <input type="text" id="oldPassword" name="oldPassword" placeholder="Enter old Password" />
        </div>

        <div class="form-group">
            <label for="newPassword">newPassword</label>
            <input type="text" id="newPassword" name="newPassword" placeholder="Enter new Password" />
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<%@include file="../include/htmlFoot.jsp"%>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${pageContext.request.contextPath}/bootstrap/others/jquery.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/others/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
