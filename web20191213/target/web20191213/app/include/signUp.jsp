<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/17
  Time: 22:26
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

<%@include file="htmlHead.jsp" %>
<%@include file="indexMenu.jsp" %>

<div class="container">
    <h2>学生账号注册</h2>
    <form action="${pageContext.request.contextPath}/SignUpServlet" method="post">
        <div class="form-group">
            <label for="id">id:</label>
            <input type="text" class="form-control" id="id"  name="id" placeholder="Enter Student id">
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password"  name="password" placeholder="Enter Student password">
        </div>

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name"  name="name" placeholder="Enter your name">
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
            <textarea class="form-control" id="introduction" name="introduction" cols="20" rows="3"></textarea>
        </div>

        <input type="hidden" name="type" value="student" />

        <button type="submit" class="btn btn-primary mb-3">Submit</button>
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
