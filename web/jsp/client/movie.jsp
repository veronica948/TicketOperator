<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 11.11.2014
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <title>${movie.movieName}</title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
${noMovie}
<c:if test="${not empty movie}" >
<div class="movieDescription">
    <fmt:message key="movie.name" bundle="${ rb }"/>: ${movie.movieName}
<br/>
    <fmt:message key="movie.description" bundle="${ rb }"/>: ${movie.description}
<br/>
    <fmt:message key="movie.country" bundle="${ rb }"/>: ${movie.country}
<br/>
    <fmt:message key="movie.actors" bundle="${ rb }"/>: ${movie.actors}
<br/>
</div>
<fmt:message key="movie.seances" bundle="${ rb }"/>:
${noSeances}
<br/>
<c:if test="${empty noSeances}">
<table border="1">
    <tr>
        <th><fmt:message key="seance.date" bundle="${ rb }"/></th>
        <th><fmt:message key="seance.time" bundle="${ rb }"/></th>
        <th><fmt:message key="seance.price" bundle="${ rb }"/></th>
        <th><fmt:message key="seance.show.tickets" bundle="${ rb }"/></th>
    </tr>
    <c:forEach var="elem" items="${seances}">
        <tr>
            <td><fmt:formatDate value="${elem.date}"/></td>
            <td><c:out value="${elem.time}"/></td>
            <td><c:out value="${elem.price}"/></td>
            <td><a href="controller?command=show_tickets&seanceId=${elem.seanceId}">
                <fmt:message key="seance.tickets" bundle="${ rb }"/>
            </a></td>
        </tr>
    </c:forEach>
</table>
</c:if>
</c:if>
    </article>
</body>
</html>
