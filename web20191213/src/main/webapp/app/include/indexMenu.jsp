<%--
  Created by IntelliJ IDEA.
  User: 1737783319
  Date: 2019/12/13
  Time: 13:23
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
            <c:if test="${!empty sessionScope.user_info}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/app/role/roleContent.jsp">主页</a>
                </li>
            </c:if>

            <c:if test="${(!empty sessionScope.user_info) && (sessionScope.user_info.type != 'root')}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/GoMsgBoardServlet">留言板</a>
                </li>
            </c:if>
        </ul>

    </div>
</nav>

</body>
</html>
