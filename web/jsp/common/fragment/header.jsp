<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 11.11.2014
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>

<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../../../css/style.css"/>
</head>
<body>

<header>
   <div class="locale">
        <a href="${ pageContext.request.contextPath }/controller?command=change_locale&locale=en">EN</a>
        <a href="${ pageContext.request.contextPath }/controller?command=change_locale&locale=ru">RU</a>
    </div>
    <br/>
    <c:if test="${not empty user}">
        <div class = "logout">
            <a href="${ pageContext.request.contextPath }/controller?command=logout"><fmt:message key="logout"/></a>
            <br/>
            <a href="${ pageContext.request.contextPath }/jsp/client/clientPage.jsp">${user.login}</a>
        </div>
        <div class="name">
            <a href="${ pageContext.request.contextPath }/index.jsp"><img class = "logo" src="${ pageContext.request.contextPath }/images/logo2.jpg"></a>
            <h1 class = "siteName"><fmt:message key="site.name.main"/> </h1>
            <h3 class = "siteName"><fmt:message key="site.name.add"/> </h3>
         </div>
    </c:if>
</header>

</body>
</html>
