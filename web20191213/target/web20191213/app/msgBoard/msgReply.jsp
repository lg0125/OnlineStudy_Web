<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/13
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="zh-cmn-Hans">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-table/dist/bootstrap-table.min.css">--%>
</head>
<body>

<%@include file="../include/htmlHead.jsp"%>

<c:if test="${sessionScope.user_info.type != 'root'}">
    <%@include file="../include/msgBoardMenu.jsp"%>
</c:if>
<c:if test="${sessionScope.user_info.type == 'root'}">
    <%@include file="../include/menu.jsp"%>
</c:if>

<c:if test="${!empty sessionScope.course}">
   <div class="container mb-3">
       <h3>${sessionScope.course.name}</h3>
       <p>${sessionScope.course.id}</p>
       <p>${sessionScope.course.dept_name}</p>
       <p>${sessionScope.course.introduction}</p>
   </div>
</c:if>

<c:if test="${sessionScope.user_info.type == 'student' && sessionScope.func == 'scan'}">
    <p class="text-center">MessageSearch</p>
    <div class="container mb-3">
        <form action="${pageContext.request.contextPath}/ScanMsgReplyServlet" method="post">
            <div class="form-group">
                <label>course_name</label>
                <label>
                    <select name="course_name" class="form-control">
                        <c:forEach items="${requestScope.course_list}" var="course">
                            <option>${course.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="form-group">
                <label>course_dept</label>
                <label>
                    <select name="course_dept" class="form-control">
                        <c:forEach items="${requestScope.department_list}" var="department">
                            <option>${department.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="form-group">
                <label>teacher_name</label>
                <label>
                    <select name="teacher_name" class="form-control">
                        <c:forEach items="${requestScope.teacher_list}" var="teacher">
                            <option>${teacher.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div class="form-group">
                <label>title</label>
                <label>
                    <input type="text" class="form-control" name="title" placeholder="Enter title" />
                </label>
            </div>

            <div class="form-group">
                <label>content</label>
                <label>
                    <input type="text" class="form-control" name="content" placeholder="Enter content" />
                </label>
            </div>

            <button type="submit" class="btn btn-primary mb-3">Submit</button>
        </form>
    </div>
</c:if>

<div class="container mt-3">
    <c:set var="end" value="${requestScope.current + sessionScope.splitPage.pageSize}"/>
    <c:forEach items="${requestScope.message_list}" var="message" begin="${requestScope.current}" end="${end - 1}" step="1" varStatus="status">
        <c:url value="/TransMsgChangeServlet" var="changeMsg">
            <c:param name="message_id" value="${message.id}" />
        </c:url>
        <c:url value="/DeleteMsgServlet" var="deleteMsg">
            <c:param name="message_id" value="${message.id}" />
        </c:url>
        <c:url value="/TransWriteReplyServlet" var="writeReply">
            <c:param name="message_id" value="${message.id}" />
        </c:url>
        <div class="media border p-3">
            <c:if test="${message.photo != ''}">
                <img src="${pageContext.request.contextPath}${message.photo}" alt="${message.student_id}" class="mr-3 mt-3 rounded-circle" style="width:60px;">
            </c:if>
            <c:if test="${message.photo == ''}">
                <img src="https://static.runoob.com/images/mobile-icon.png" alt="Jane Doe" class="mr-3 mt-3 rounded-circle" style="width:60px;">
            </c:if>
            <div class="media-body">
                <h4>Title:${message.title}</h4>
                <p>Course:${message.course_name}</p>
                <blockquote class="blockquote">
                    <p>${message.content}</p>
                    <footer class="blockquote-footer">
                        Time:<fmt:formatDate value="${message.time}" pattern="yyyy-MM-dd"/>
                        &nbsp;
                        Name:${message.student_name}
                        &nbsp;
                        CourseDept:${message.course_dept}
                    </footer>
                </blockquote>
                <c:if test="${sessionScope.user_info.type == 'root' || (sessionScope.user_info.type == 'student' && sessionScope.func != 'scan')}">
                    <a href="${changeMsg}" class="btn btn-primary">修改</a>
                    <a href="${deleteMsg}" class="btn btn-danger">删除</a>
                </c:if>
                <c:if test="${sessionScope.user_info.type == 'teacher'}">
                    <a href="${writeReply}" class="btn btn-primary">回答</a>
                    <a href="${deleteMsg}" class="btn btn-danger">删除</a>
                </c:if>

                <c:set var="message_id" value="${message.id + 0}"/>
                <c:set var="reply_list" value="${requestScope.reply_map[message_id]}"/>
                <c:forEach items="${reply_list}" var="reply">
                    <c:url value="/TransReplyChangeServlet" var="changeReply">
                        <c:param name="reply_id" value="${reply.id}" />
                    </c:url>
                    <c:url value="/DeleteReplyServlet" var="deleteReply">
                        <c:param name="reply_id" value="${reply.id}" />
                    </c:url>
                    <div class="media p-3">
                        <c:if test="${reply.photo != ''}">
                            <img src="${pageContext.request.contextPath}${reply.photo}" alt="${reply.teacher_id}" class="mr-3 mt-3 rounded-circle" style="width:45px;">
                        </c:if>
                        <c:if test="${reply.photo == ''}">
                            <img src="https://static.runoob.com/images/mobile-icon.png" alt="Jane Doe" class="mr-3 mt-3 rounded-circle" style="width:45px;">
                        </c:if>
                        <div class="media-body">
                            <blockquote class="blockquote">
                                <p>${reply.content}</p>
                                <footer class="blockquote-footer">
                                    Time:<fmt:formatDate value="${reply.time}" pattern="yyyy-MM-dd"/>
                                    &nbsp;
                                    Name:${reply.teacher_name}
                                    &nbsp;
                                    rank:${reply.rank_name}
                                </footer>
                            </blockquote>
                            <c:if test="${sessionScope.user_info.type != 'student'}">
                                <a href="${changeReply}" class="btn btn-primary">修改</a>
                                <a href="${deleteReply}" class="btn btn-danger">删除</a>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>

<c:if test="${!empty requestScope.message_list}">
<div class="container">
    <ul class="pagination">
        <c:if test="${end > 3}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/SplitServlet?current=${requestScope.current - sessionScope.splitPage.pageSize}">Previous</a></li>
        </c:if>
        <c:if test="${end == 3}">
            <li class="page-item disabled"><a class="page-link" href="${pageContext.request.contextPath}/SplitServlet?current=${requestScope.current - sessionScope.splitPage.pageSize}">Previous</a></li>
        </c:if>

        <c:forEach begin="1" end="${sessionScope.splitPage.pageCount}" varStatus="status">
            <c:if test="${end / sessionScope.splitPage.pageSize == status.index}">
                <li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/SplitServlet?current=${(status.index - 1) * sessionScope.splitPage.pageSize}">${status.index}</a></li>
            </c:if>
            <c:if test="${end / sessionScope.splitPage.pageSize != status.index}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/SplitServlet?current=${(status.index - 1) * sessionScope.splitPage.pageSize}">${status.index}</a></li>
            </c:if>
        </c:forEach>
        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/SplitServlet?current=${end}">Next</a></li>
    </ul>
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
