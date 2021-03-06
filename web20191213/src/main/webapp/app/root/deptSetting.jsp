<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/15
  Time: 10:46<
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
                <th scope="col">name</th>
                <th scope="col">building</th>
                <th scope="col">budget</th>
                <c:if test="${requestScope.func != 'add'}">
                    <th scope="col">op</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.department_list}" var="department" varStatus="status" begin="1" step="1">
                <c:if test="${requestScope.func == 'delete'}">
                    <c:url value="/DeleteDeptServlet" var="deleteDept">
                        <c:param name="name" value="${department.name}" />
                    </c:url>
                </c:if>
                <c:if test="${requestScope.func == 'change'}">
                    <c:url value="/ChangeDeptServlet" var="changeDept">
                        <c:param name="name" value="${department.name}" />
                    </c:url>
                </c:if>
                <tr>
                    <th scope="row">${status.index}</th>
                    <td>${department.name}</td>
                    <td>${department.building}</td>
                    <td>${department.budget}</td>
                    <c:if test="${requestScope.func == 'change'}">
                        <td><a href="${changeDept}" class="btn btn-primary">change</a></td>
                    </c:if>
                    <c:if test="${requestScope.func == 'delete'}">
                        <td><a href="${deleteDept}" class="btn btn-danger">delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<c:if test="${requestScope.func == 'add'}">
    <div class="container mb-3">
        <form action="${pageContext.request.contextPath}/AddDeptServlet" method="post">
            <div class="form-group">
                <label for="name">name</label>
                <input type="text" class="form-control" name="name" id="name" />
            </div>

            <div class="form-group">
                <label for="building">building</label>
                <input type="text" class="form-control" name="building" id="building" />
            </div>

            <div class="form-group">
                <label for="budget">budget</label>
                <input type="text" class="form-control" name="budget" id="budget" />
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
