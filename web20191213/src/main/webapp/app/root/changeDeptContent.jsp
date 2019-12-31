<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/15
  Time: 15:59
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
    <form action="${pageContext.request.contextPath}/ChangeDeptContentServlet" method="post">


        <div class="form-group">
            <label for="building">building: </label>
            <input type="text" class="form-control" id="building" name="building" placeholder="${requestScope.department.building}"/>
        </div>


        <div class="form-group">
            <label for="budget">budget: </label>
            <input type="text" class="form-control" id="budget" name="budget" placeholder="${requestScope.department.budget}"/>
        </div>

        <input type="hidden" name="dept_name" value="${requestScope.department.name}" />

        <button type="submit" class="btn btn-danger">Submit</button>
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