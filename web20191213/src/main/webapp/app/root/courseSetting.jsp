<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/14
  Time: 21:32
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
               <th scope="col">course_name</th>
               <th scope="col">course_id</th>
               <th scope="col">course_dept</th>
               <th scope="col">teacher_name</th>
               <th scope="col">teacher_id</th>
               <th scope="col">rank_name</th>
               <c:if test="${requestScope.func != 'add'}">
                   <th scope="col">op</th>
               </c:if>
           </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.teacher_course_list}" var="teacherCourse">
                <c:if test="${requestScope.func == 'change'}" >
                    <c:url value="/ChangeCourseServlet" var="changeCourse">
                        <c:param name="course_id" value="${teacherCourse.course_id}" />
                        <c:param name="teacher_id" value="${teacherCourse.teacher_id}"/>
                    </c:url>
                </c:if>
                <c:if test="${requestScope.func == 'delete'}">
                    <c:url value="/DeleteCourseServlet" var="deleteCourse">
                        <c:param name="teacher_id" value="${teacherCourse.teacher_id}"/>
                        <c:param name="course_id" value="${teacherCourse.course_id}" />
                    </c:url>
                </c:if>
                <tr>
                    <th scope="row"></th>
                    <td>${teacherCourse.course_name}</td>
                    <td>${teacherCourse.course_id}</td>
                    <td>${teacherCourse.course_dept}</td>
                    <td>${teacherCourse.teacher_name}</td>
                    <td>${teacherCourse.teacher_id}</td>
                    <td>${teacherCourse.rank_name}</td>
                    <c:if test="${requestScope.func == 'change'}">
                        <td><a href="${changeCourse}" class="btn btn-primary">change</a></td>
                    </c:if>
                    <c:if test="${requestScope.func == 'delete'}">
                        <td><a href="${deleteCourse}" class="btn btn-danger">delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<c:if test="${requestScope.func == 'add'}">
    <div class="container mb-3">
        <form action="${pageContext.request.contextPath}/AddCourseServlet" method="post">
            <div class="form-group">
                <label for="id">id: </label>
                <input type="text" class="form-control" id="id" name="id" />
            </div>

            <div class="form-group">
                <label for="name">name:</label>
                <input type="text" class="form-control" id="name" name="name" />
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
                <label for="teacher_id">teacher_id</label>
                <select class="form-control" id="teacher_id" name="teacher_id">
                    <c:forEach items="${requestScope.teacher_list}" var="teacher">
                        <option>${teacher.id}:${teacher.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="introduction">
                    introduction
                </label>
                <textarea class="form-control" id="introduction" name="introduction" rows="3" cols="20" ></textarea>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
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
