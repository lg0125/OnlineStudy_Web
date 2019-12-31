<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/13
  Time: 14:37
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

<c:if test="${sessionScope.user_info.type == 'root'}">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">首页</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop1" data-toggle="dropdown">
                        课程信息设置
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ListAddCourseServlet">增加课程</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ListChangeCourseServlet">修改课程</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ListDeleteCourseServlet">删除课程</a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop2" data-toggle="dropdown">
                        教师信息设置
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ListAddTeacherServlet">教师注册</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ListDeleteTeacherServlet">删除教师</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ListChangeTeacherServlet">修改教师</a>
                    </div>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop3" data-toggle="dropdown">
                        学院信息设置
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ListAddDeptServlet">增加学院</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ListDeleteDeptServlet">删除学院</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ListChangeDeptServlet">修改学院</a>
                    </div>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/GoMsgBoardServlet">留言回复之删除修改</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/role/changeInfo.jsp">管理员个人账号设置</a>
                </li>
            </ul>
        </div>
    </nav>
</c:if>

<c:if test="${sessionScope.user_info.type == 'student'}">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">首页</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" >
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/role/changeInfo.jsp">个人信息修改</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/role/upload.jsp">图片头像上传</a>
                </li>
            </ul>
        </div>
    </nav>
</c:if>

<c:if test="${sessionScope.user_info.type == 'teacher'}">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">首页</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/role/changeInfo.jsp">个人信息修改</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/role/upload.jsp">图片头像上传</a>
                </li>
            </ul>
        </div>
    </nav>
</c:if>

</body>
</html>
