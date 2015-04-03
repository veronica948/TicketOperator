
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var = "rb"/>
<html>
<head>
    <title><fmt:message key="seance.title" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<c:if test="${user.role == 1}">
    <c:import url="../client/fragment/clientMenu.jsp"/>
</c:if>
<c:if test="${user.role == 0}">
    <c:import url="../admin/fragment/adminMenu.jsp"/>
</c:if>
<article>
    <br/>
<form action="${ pageContext.request.contextPath }/controller" method="GET">
    <input type="hidden" name="command" value="search">
    <table><tr><td>
    <fmt:message key="movie.name" bundle="${ rb }"/>
        </td><td>
    <input type="text" name="movieName"/>
        </td>
        </tr>
        <tr><td>
    <fmt:message key="seance.date" bundle="${ rb }"/>
            </td>
            <td>
    <input type="date" name="seanceDate"/>
                </td>
            </tr>
        </table>
    <input type="submit" value="<fmt:message key="search.title" bundle="${ rb }"/>"/>
</form>
${emptyFields}
${notFound}
${oldSeance}
    ${incorrectDateFormat}
    ${noSeances}
<c:if test="${not empty seances}">
    <table border="1">
        <tr>
            <th><fmt:message key="movie.name" bundle="${ rb }"/></th>
            <th><fmt:message key="seance.date" bundle="${ rb }"/></th>
            <th><fmt:message key="seance.time" bundle="${ rb }"/></th>
            <th><fmt:message key="seance.price" bundle="${ rb }"/></th>
            <th><fmt:message key="seance.show.tickets" bundle="${ rb }"/></th>
            <c:if test="${user.role == 0}">
                <th><fmt:message key="admin.seance.delete" bundle="${ rb }"/></th>
            </c:if>
        </tr>
        <c:forEach var="elem" items="${seances}">
            <tr>
                <td><a href="${ pageContext.request.contextPath }/controller?command=show_movie_page&movieId=${elem.movieId}">
                    ${elem.movieName}
                </a></td>
                <td><fmt:formatDate value="${elem.date}"/></td>
                <td> <fmt:formatDate value="${elem.time}" type="time" timeStyle="short"/></td>
                <td><c:out value="${elem.price}"/></td>
                    <td><a href="${ pageContext.request.contextPath }/controller?command=show_tickets&seanceId=${elem.seanceId}">
                        <fmt:message key="seance.tickets" bundle="${ rb }"/></a></td>
                <c:if test="${user.role == 0}">
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="POST">
                            <input type="hidden" name="command" value="delete_seance"/>
                            <input type="hidden" name="seanceId" value="${elem.seanceId}"/>
                            <input type="submit" value="<fmt:message key="admin.seance.delete" bundle="${ rb }"/>">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</c:if>
</article>
</body>
</html>
