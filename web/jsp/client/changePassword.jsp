<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 08.12.2014
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
    <title><fmt:message key="client.password.change"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
    <form action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="change_password"/>
        <br/>
        <fmt:message key="client.old.password"/>
        <br/>
        <input type="password" name="oldPassword"/>
        <br/>
        <fmt:message key="client.new.password"/>
        <br/>
        <input type="password" name="newPassword"/>
        <br/>
        <br/>
        <input type="submit" value="<fmt:message key="client.password.change"/>"/>
    </form>
${passError}
    </article>
</body>
</html>
