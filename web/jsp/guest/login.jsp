
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <title><fmt:message key="login" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<article>
    <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="login" />
        <fmt:message key="login" bundle="${ rb }"/>
        <br/>
        <input type="text" name="login" value=""/>
        <br/><fmt:message key="password" bundle="${ rb }"/><br/>
        <input type="password" name="password" value=""/>
        <br/>
        ${errorLoginPassMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
    <input type="submit" value="<fmt:message key="log.in" bundle="${ rb }"/>"/>
</form><hr/>
<a class = "withLine" href="/jsp/guest/registration.jsp"><fmt:message key="registration.title" bundle="${ rb }"/></a>
    </article>
</body>
</html>
