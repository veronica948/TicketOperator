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
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
    <title><fmt:message key="ticket.order"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
    <table>
        <tr><td>
<fmt:message key="login"/>:
    </td>
            <td>
     ${user.login}
    </td></tr><tr><td>
<fmt:message key="registration.firstname"/>:  </td>
        <td>
     ${firstName}
        </td></tr><tr><td>
<fmt:message key="registration.lastname"/>:  </td>
        <td>
     ${lastName}
        </td></tr><tr><td>
<fmt:message key="movie.name"/>:</td><td>
     ${seance.movieName}
    </td></tr><tr><td>
<fmt:message key="seance.date"/>:  </td>
        <td>
<fmt:formatDate value="${seance.date}"/>
        </td></tr><tr><td>

<fmt:message key="seance.time"/>:  </td><td>
<fmt:formatDate value="${seance.time}" type="time" timeStyle="short"/>
    </td></tr><tr><td>

<fmt:message key="ticket.row"/>:   </td><td>
     ${ticket.row}
    </td></tr><tr><td>
<fmt:message key="ticket.place"/>:   </td>
        <td>
     ${ticket.place}
        </td></tr><tr><td>
<fmt:message key="seance.price"/>:   </td><td>
     ${ticket.price}
    </td></tr></table>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="make_order"/>
    <input type="hidden" name="ticketId" value="${ticket.ticketId}">
    <input type="submit" value="<fmt:message key="ticket.order.make"/>">
</form>
 </article>
</body>
</html>
