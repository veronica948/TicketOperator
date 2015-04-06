
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
<head>
    <title><fmt:message key="admin.movie.add" bundle="${ rb }"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/adminMenu.jsp"/>
<article>
     <h3><fmt:message key="admin.movie.add" bundle="${ rb }"/></h3>
    <form action="${pageContext.request.contextPath}/controller" METHOD="POST">


        <input type="hidden" name="command" value="add_movie"/>
        <table>
            <tr><td>
        <fmt:message key="movie.name" bundle="${rb}"/>*
            </td>
                <td>
        <input type="text" name="movieName">  </td>
            </tr>
                <tr><td>
        <fmt:message key="movie.country" bundle="${rb}"/>
            </td><td>
        <input type = "text" name="country"/>
            </td>  </tr>

            <tr><td>
        <fmt:message key="movie.actors" bundle="${rb}"/>
            </td>
            <td>
        <input type = "text" name="actors"/>
        </td></tr>
            <tr><td>
        <fmt:message key="movie.description" bundle="${rb}"/>
        </td>
            <td>
                <textarea name="description" rows="10" cols="50"></textarea>
       </td>
            </tr>
                <tr><td>
        <fmt:message key="movie.release.date" bundle="${rb}"/>
                    </td>
                    <td>
        <input type = "date" name="releaseDate"/>
                        </td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="movie.seance.add" bundle="${rb}"/>">
    </form>
    ${incorrectDataFormat}
    ${emptyFields}
    ${impossibleOperation}
    </article>
</body>
</html>
