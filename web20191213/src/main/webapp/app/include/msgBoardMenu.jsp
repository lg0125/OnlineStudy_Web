<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/13
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-cmn-Hans">
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">首页</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <c:if test="${sessionScope.user_info.type == 'student'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ListMsgCourseServlet">发表留言</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ListMsgRepliedServlet">
                        查看已回复留言
                        <span class="badge badge-pill badge-warning">${sessionScope.ReplyNum}</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ListMsgStudentServlet">留言修改删除</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ListMsgScanServlet">留言搜索浏览</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                        首页课程显示设置
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/GoMsgBoardServlet?setting=department">学院</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/GoMsgBoardServlet?setting=teacher">教师</a>
                    </div>
                </li>
            </c:if>

            <c:if test="${sessionScope.user_info.type == 'teacher'}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ListMsgNotRepliedServlet">
                        查看待回复留言
                        <span class="badge badge-pill badge-warning">${sessionScope.notReplyNum}</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ListMsgTeacherServlet">回复留言删除修改</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/ListCourseAvailServlet">访问权限设置</a>
                </li>
            </c:if>
        </ul>

    </div>
</nav>

</body>
</html>
