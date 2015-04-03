<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 07.10.2014
  Time: 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
    <title><fmt:message key="main.title"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
    <div class = "main">
         <h3><fmt:message key="site.name.main"/> </h3>
         <img class = "mainPhoto" src="${ pageContext.request.contextPath }/images/cinema.jpg">
    </div>
</article>

</body>
</html>
