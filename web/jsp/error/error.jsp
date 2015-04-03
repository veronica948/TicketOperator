<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 07.10.2014
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
    <head>
        <title><fmt:message key="page.error"/></title>
    </head>
    <body>
    <fmt:message key="page.error.info"/> ${pageContext.errorData.requestURI} <fmt:message key="page.error.failed"/>
        <br/>
    <fmt:message key="page.error.servlet.info"/>: ${pageContext.errorData.servletName}
        <br/>
    <fmt:message key="page.error.code"/>: ${pageContext.errorData.statusCode}
        <br/>
    <fmt:message key="page.error.exception"/>: ${pageContext.errorData.throwable} ${exception}
    </body>
</html>
