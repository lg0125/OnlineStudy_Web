<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/13
  Time: 20:17
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
<%@include file="../include/msgBoardMenu.jsp"%>

<table class="table table-dark">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">course_id</th>
        <th scope="col">course_name</th>
        <th scope="col">course_dept</th>
        <th scope="col">teacher_id</th>
        <th scope="col">teacher_name</th>
        <th scope="col">teacher_rank</th>
        <th scope="col">goInto</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.student_course_list}" var="studentCourse" varStatus="status">
        <c:url var="courseURL" value="/TransIdWriteMsgServlet">
            <c:param name="teacher_id" value="${studentCourse.teacher_id}" />
            <c:param name="course_id"  value="${studentCourse.course_id}" />
        </c:url>
        <tr>
            <th scope="row">${status.index + 1}</th>
            <td>${studentCourse.course_id}</td>
            <td>${studentCourse.course_name}</td>
            <td>${studentCourse.course_dept}</td>
            <td>${studentCourse.teacher_id}</td>
            <td>${studentCourse.teacher_name}</td>
            <td>${studentCourse.rank_name}</td>
            <td><a href="${courseURL}">writeMsg</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="../include/htmlFoot.jsp"%>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${pageContext.request.contextPath}/bootstrap/others/jquery.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/others/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
