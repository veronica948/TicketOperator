<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 12.10.2014
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
    <title><fmt:message key="registration.title"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<article>
<form name="registrationForm" action="${ pageContext.request.contextPath }/controller" method="POST">
    <input type="hidden" name="command" value="registration" />
    <fmt:message key="registration.lastname" />*
    <br/>
    <input type="text" name="lastname" value=""/>
    <br/>
    <fmt:message key="registration.firstname"/>*
    <br/>
    <input type="text" name="firstname" value=""/>
    <br/>
    <fmt:message key="email" />:<br/>
    <input type="email" name="email" value=""/>
    <br/>
    <fmt:message key="login" />*
    <br/>
    <input type="text" name="login" value=""/>
    <br/>
    <fmt:message key="password" />*
    <br/>
    <input type="password" name="password" value=""/>
    <br/>
    <br/>
    <input type="submit" value="<fmt:message key="registration.button" />"/>
    <br/>

</form>
    <br/>
<a class = "withLine" href="${ pageContext.request.contextPath }/jsp/guest/login.jsp"><fmt:message key="registration.back.login" /></a>
    <br/>
${loginExistError}
<br/>
${loginPassError}
<br/>
${emailError}
<br/>
${nameEmpty}
    <br/>
${shortLogin}
    <br/>
    ${shortPassword}
  <br/>
 ${passError}
    <br/>
    ${loginError}
    <br/>
    ${incorrectEmail}
</article>
</body>
</html>
