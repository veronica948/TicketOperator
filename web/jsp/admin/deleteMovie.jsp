<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 11.11.2014
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <title><fmt:message key="admin.movie.delete" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/adminMenu.jsp"/>
<article>
${noMovies}
<c:if test="${not empty movieList}">
     <form action="${pageContext.request.contextPath}/controller" METHOD="POST">
         <input type="hidden" name="command" value="delete_movie"/>
             <fmt:message key="movie.name" bundle="${ rb }"/>
             <ctg:movieList list="${movieList}"/>
         <input type="submit" value="<fmt:message key="admin.movie.delete" bundle="${ rb }"/>"/>
     </form>
 </c:if>
 </article>

</body>
</html>
