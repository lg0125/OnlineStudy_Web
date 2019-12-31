<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/14
  Time: 0:51
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

<div class="container mb-3">
    <p class="text-center">允许清单</p>
    <table class="table table-dark">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">student_id</th>
                <th scope="col">student_name</th>
                <th scope="col">course_name</th>
                <th scope="col">teacher_name</th>
                <th scope="col">op</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.course_studentAvail_list}" var="courseStudentAvail" varStatus="status" step="1">
                <c:url value="/ChangeAvailServlet" var="changeAvail">
                    <c:param name="course_id" value="${courseStudentAvail.course_id}" />
                    <c:param name="status" value="${courseStudentAvail.status}"/>
                    <c:param name="student_id" value="${courseStudentAvail.student_id}"/>
                </c:url>
                <tr>
                    <th scope="row">${status.index}</th>
                    <td>${courseStudentAvail.student_id}</td>
                    <td>${courseStudentAvail.student_name}</td>
                    <td>${courseStudentAvail.course_name}</td>
                    <td>${courseStudentAvail.teacher_name}</td>
                    <td><a href="${changeAvail}" class="btn btn-danger">禁用</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<div class="container mt-3">
    <p class="text-center">禁止清单</p>
    <table class="table table-dark">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">student_id</th>
            <th scope="col">student_name</th>
            <th scope="col">course_name</th>
            <th scope="col">teacher_name</th>
            <th scope="col">op</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.course_studentNotAvail_list}" var="courseStudentNotAvail" varStatus="status" step="1">
            <c:url value="/ChangeAvailServlet" var="changeAvail">
                <c:param name="course_id" value="${courseStudentNotAvail.course_id}"/>
                <c:param name="status" value="${courseStudentNotAvail.status}"/>
                <c:param name="student_id" value="${courseStudentNotAvail.student_id}"/>
            </c:url>
            <tr>
                <th scope="row">${status.index}</th>
                <td>${courseStudentNotAvail.student_id}</td>
                <td>${courseStudentNotAvail.student_name}</td>
                <td>${courseStudentNotAvail.course_name}</td>
                <td>${courseStudentNotAvail.teacher_name}</td>
                <td><a href="${changeAvail}" class="btn btn-primary"></a>允许</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="../include/htmlFoot.jsp"%>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${pageContext.request.contextPath}/bootstrap/others/jquery.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/others/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
