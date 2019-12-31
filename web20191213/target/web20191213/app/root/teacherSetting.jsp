<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/15
  Time: 1:21
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
    <table class="table table-dark">
        <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">id</th>
                <th scope="col">password</th>
                <th scope="col">name</th>
                <th scope="col">dept_name</th>
                <th scope="col">rank_name</th>
                <th scope="col">introduction</th>
                <c:if test="${requestScope.func != 'add'}">
                    <th scope="col">op</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${requestScope.teacher_list}" var="teacher" varStatus="status">
                    <c:if test="${requestScope.func == 'change'}">
                        <c:url value="/ChangeTeacherServlet" var="changeTeacher">
                            <c:param name="id" value="${teacher.id}" />
                        </c:url>
                    </c:if>
                    <c:if test="${requestScope.func == 'delete'}">
                        <c:url value="/DeleteTeacherServlet" var="deleteTeacher">
                            <c:param name="id" value="${teacher.id}"/>
                        </c:url>
                    </c:if>
                    <tr>
                        <th scope="row">${status.index}</th>
                        <td>${teacher.id}</td>
                        <td>${teacher.password}</td>
                        <td>${teacher.name}</td>
                        <td>${teacher.dept_name}</td>
                        <td>${teacher.rank_name}</td>
                        <td>${teacher.introduction}</td>
                        <c:if test="${requestScope.func == 'change'}">
                            <td><a href="${changeTeacher}" class="btn btn-primary">change</a></td>
                        </c:if>
                        <c:if test="${requestScope.func == 'delete'}">
                            <td><a href="${deleteTeacher}" class="btn btn-danger">delete</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
        </tbody>
    </table>
</div>

<c:if test="${requestScope.func == 'add'}">
    <div class="container mb-3">
        <form action="${pageContext.request.contextPath}/AddTeacherServlet" method="post">
            <div class="form-group">
                <label for="id">id</label>
                <input type="text" class="form-control" name="id" id="id" />
            </div>

            <div class="form-group">
                <label for="password">password</label>
                <input type="text" class="form-control" name="password" id="password" />
            </div>

            <div class="form-group">
                <label for="name">name</label>
                <input type="text" class="form-control" name="name" id="name" />
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
                <textarea class="form-control" id="introduction" name="introduction" cols="20" rows="3"></textarea>
            </div>

            <button class="btn btn-primary" type="submit">Submit</button>
        </form>
    </div>
</c:if>

<%@include file="../include/htmlFoot.jsp"%>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="${pageContext.request.contextPath}/bootstrap/others/jquery.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/others/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
