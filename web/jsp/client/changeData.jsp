<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 08.12.2014
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
    <title><fmt:message key="client.data.change"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
<form method="POST" action="${ pageContext.request.contextPath }/controller">
    <input type="hidden" name="command" value="change_client_data" />
    <fmt:message key="registration.lastname" />*
    <br/>
    <input type="text" name="lastname" value="${lastname}"/>
    <br/>
    <fmt:message key="registration.firstname"/>*
    <br/>
    <input type="text" name="firstname" value="${firstname}"/>
    <br/>
    <fmt:message key="email" />:<br/>
    <input type="email" name="email" value="${email}"/>
    <br/>
    <br/>
    <input type="submit" value="<fmt:message key="client.data.change"/>"/>
    <br/>
</form>
    </article>
</body>
</html>
