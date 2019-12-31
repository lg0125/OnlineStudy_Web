<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/13
  Time: 14:33
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
<c:if test="${sessionScope.user_info.type != 'root'}">
    <%@include file="../include/msgBoardMenu.jsp"%>
</c:if>
<c:if test="${sessionScope.user_info.type == 'root'}">
    <%@include file="../include/menu.jsp"%>
</c:if>
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
    <c:if test="${requestScope.func != 'avail' && (sessionScope.user_info.type == 'teacher'|| sessionScope.user_info.type == 'root')}">
        <c:forEach items="${requestScope.teacher_course_list}" var="teacherCourse" varStatus="status" >
            <c:url var="courseURL" value="/MsgCourseServlet">
                <c:param name="teacher_id"  value="${teacherCourse.teacher_id}" />
                <c:param name="course_id"  value="${teacherCourse.course_id}" />
            </c:url>
            <tr>
                <th scope="row">${status.index + 1}</th>
                <td>${teacherCourse.course_id}</td>
                <td>${teacherCourse.course_name}</td>
                <td>${teacherCourse.course_dept}</td>
                <td>${teacherCourse.teacher_id}</td>
                <td>${teacherCourse.teacher_name}</td>
                <td>${teacherCourse.rank_name}</td>
                <td><a href="${courseURL}" class="btn btn-primary">goCourse</a></td>
            </tr>
        </c:forEach>
    </c:if>

    <c:if test="${requestScope.func != 'avail' && sessionScope.user_info.type == 'student'}">
        <c:forEach items="${requestScope.student_course_list}" var="studentCourse" varStatus="status" >
            <c:url var="courseURL" value="/MsgCourseServlet">
                <c:param name="teacher_id"  value="${studentCourse.teacher_id}" />
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
                <c:if test="${studentCourse.status == 1}">
                    <td><a href="${courseURL}" class="btn btn-primary">goCourse</a></td>
                </c:if>
                <c:if test="${studentCourse.status == 0}">
                    <td><a href="${courseURL}" class="btn btn-primary disabled">goCourse</a></td>
                </c:if>
            </tr>
        </c:forEach>
    </c:if>

    <c:if test="${requestScope.func == 'avail'}">
        <c:forEach items="${requestScope.teacher_course_list}" var="teacherCourse" varStatus="status"  step="1">
            <c:url var="courseAvailURL" value="/ListCourseStudentServlet">
                <c:param name="course_id"  value="${teacherCourse.course_id}" />
            </c:url>
            <tr>
                <th scope="row">${status.index + 1}</th>
                <td>${teacherCourse.course_id}</td>
                <td>${teacherCourse.course_name}</td>
                <td>${teacherCourse.course_dept}</td>
                <td>${teacherCourse.teacher_id}</td>
                <td>${teacherCourse.teacher_name}</td>
                <td>${teacherCourse.rank_name}</td>
                <td><a href="${courseAvailURL}" class="btn btn-primary">Avail</a></td>
            </tr>
        </c:forEach>
    </c:if>
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