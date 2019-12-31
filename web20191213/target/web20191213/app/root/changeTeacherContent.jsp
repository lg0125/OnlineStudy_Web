<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/15
  Time: 9:42
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
    <form action="${pageContext.request.contextPath}/ChangeTeacherContentServlet" method="post">


        <div class="form-group">
            <label for="password">password</label>
            <input type="text" class="form-control" name="password" id="password" value="${requestScope.teacher.password}"/>
        </div>

        <div class="form-group">
            <label for="name">name</label>
            <input type="text" class="form-control" name="name" id="name" value="${requestScope.teacher.name}"/>
        </div>

        <div class="form-group">
            <label for="rank_name">rank_name</label>
            <select class="form-control" id="rank_name" name="rank_name">
                <c:forEach items="${requestScope.rank_list}" var="rank">
                    <option>${rank.rank_name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="dept_name">dept_name</label>
            <select class="form-control" id="dept_name" name="dept_name">
                <c:forEach items="${requestScope.department_list}" var="department">
                    <option>${department.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="introduction">introduction</label>
            <textarea class="form-control" id="introduction" name="introduction" cols="20" rows="3" placeholder="${requestScope.teacher.introduction}"></textarea>
        </div>

        <input type="hidden" name="teacher_id" value="${requestScope.teacher.id}">
        <button class="btn btn-primary" type="submit">Submit</button>
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
