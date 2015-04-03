<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 30.11.2014
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <title></title>
</head>
<body>
<nav>
    <ul>
        <li>
            <a href="${ pageContext.request.contextPath }/jsp/client/main.jsp"><fmt:message key="main.title" bundle="${ rb }"/></a>
        </li>
        <li>
            <a href="${ pageContext.request.contextPath }/controller?command=show_movies"><fmt:message key="movies.title" bundle="${ rb }"/></a>
        </li>
        <li>
            <a href="${ pageContext.request.contextPath }/controller?command=show_all_seances"><fmt:message key="seance.title" bundle="${ rb }"/></a>
        </li>
        <c:if test="${user.role == 0}">
            <li>
                <a href="${ pageContext.request.contextPath }/jsp/admin/administration.jsp"><fmt:message key="admin.title" bundle="${ rb }"/></a>
            </li>
        </c:if>
    </ul>
</nav>
</body>
</html>
