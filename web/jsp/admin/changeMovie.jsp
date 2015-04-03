<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 01.12.2014
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <title><fmt:message key="admin.movie.change" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/adminMenu.jsp"/>
<article>
<h3><fmt:message key="admin.movie.change" bundle="${ rb }"/></h3>
<form action="${pageContext.request.contextPath}/controller" METHOD="POST">
    <input type="hidden" name="command" value="change_movie"/>
    <table>
        <tr><td><fmt:message key="movie.name" bundle="${rb}"/>*
            </td>
            <td>
    <input type="text" name="movieName" value="${movie.movieName}">
            </td>
        </tr><tr><td>
    <fmt:message key="movie.country" bundle="${rb}"/>  </td>
        <td>
    <input type = "text" name="country" value="${movie.country}"/>
        </td>
    </tr><tr><td>
    <fmt:message key="movie.actors" bundle="${rb}"/>
        </td>
        <td>
    <input type = "text" name="actors" value="${movie.actors}"/>
        </td>
    </tr><tr><td>
    <fmt:message key="movie.description" bundle="${rb}"/>   </td>
        <td>
    <textarea name="description" rows="10" cols="50">
        ${movie.description}
    </textarea>
        </td>
    </tr><tr><td>
    <fmt:message key="movie.release.date" bundle="${rb}"/>
        </td><td>
    <input type = "date" name="releaseDate" value="${movie.releaseDate}"/>
    </td>
        <td>
    <input type = "hidden" name="movieId" value="${movie.id}"/>
            </td>
        </tr>
        </table>
    <input type="submit" value="<fmt:message key="admin.movie.change" bundle="${rb}"/>">
</form>
    </article>
</body>
</html>
