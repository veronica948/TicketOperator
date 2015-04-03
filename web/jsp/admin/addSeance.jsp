<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 11.11.2014
  Time: 12:51
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
    <title><fmt:message key="admin.seance.add" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/adminMenu.jsp"/>
<article>
${noMovies}
<c:if test="${not empty movieList}">
<form action="${pageContext.request.contextPath}/controller" METHOD="POST">
    <input type="hidden" name="command" value="add_seance"/>
        <table>
            <tr><td>
        <fmt:message key="movie.name" bundle="${ rb }"/>
                </td>
                <td>
        <ctg:movieList list="${movieList}"/>
                    </td>
                </tr><tr><td>
        <fmt:message key="seance.date" bundle="${ rb }"/>*
            </td>
            <td>
        <input type="date" name="seanceDate"/>
            </td>
        </tr><tr><td>
        <fmt:message key="seance.time" bundle="${ rb }"/>*
            </td>
            <td>
        <input type="time" name="seanceTime"/>
            </td>
        </tr><tr><td>
        <fmt:message key="seance.price" bundle="${ rb }"/>*
            </td><td>
        <input type="number" name="price"/>
            </td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="admin.seance.add" bundle="${ rb }"/>"/>
</form>
    </c:if>


<br/>
${emptyFields}
    </article>
</body>
</html>
