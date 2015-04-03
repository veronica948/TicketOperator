<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 24.11.2014
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <title><fmt:message key="admin.title" bundle="${ rb }"/></title>
</head>
<body>
    <c:import url="../common/fragment/header.jsp"/>
    <c:import url="fragment/adminMenu.jsp"/>
    <article>
    ${successfulOperation}
    ${emptyFields}
    ${incorrectPrice}
    ${impossibleOperation}
    ${incorrectDateTime}
    ${incorrectDataFormat}
        </article>
</body>
</html>
