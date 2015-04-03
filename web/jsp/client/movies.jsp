<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 30.11.2014
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <title><fmt:message key="movies.title" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
<c:if test="${not empty movieList}">
    <h3><fmt:message key="movies.title" bundle="${ rb }"/></h3>
    <table border="1">
        <tr>
            <th><fmt:message key="movie.name" bundle="${ rb }"/></th>
            <th><fmt:message key="movie.country" bundle="${ rb }"/></th>
            <th><fmt:message key="movie.release.date" bundle="${ rb }"/></th>
            <th><fmt:message key="movie.actors" bundle="${ rb }"/></th>
            <th><fmt:message key="movie.description" bundle="${ rb }"/></th>
        </tr>
        <c:forEach var="elem" items="${movieList}">
            <tr>
                <td><a href="${ pageContext.request.contextPath }/controller?command=show_movie_page&movieId=${elem.id}"> ${elem.movieName}</a></td>
                <td><c:out value="${elem.country}"/></td>
                <td><fmt:formatDate value="${elem.releaseDate}"/></td>
                <td><c:out value="${elem.actors}"/></td>
                <td><c:out value="${elem.description}"/></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
    </article>
</body>
</html>
