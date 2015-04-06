<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 03.12.2014
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
    <title><fmt:message key="seance.tickets" /></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
<h3><fmt:message key="seance.tickets" /></h3>
${notFound}
${movieName}
${seanceDate}
${seanceTime}


<c:if test="${not empty tickets}">
    <table border="1">
        <tr>
            <th><fmt:message key="ticket.row" /></th>
            <th><fmt:message key="ticket.place" /></th>
            <th><fmt:message key="seance.price"/></th>
            <th><fmt:message key="ticket.order.make" /></th>
        </tr>
        <c:forEach var="elem" items="${tickets}">
            <tr>
                <td><c:out value="${elem.row}"/></td>
                <td><c:out value="${elem.place}"/></td>
                <td><c:out value="${elem.price}"/></td>
                <td>
                    <form action="${ pageContext.request.contextPath }/controller" method="get">
                        <input type="hidden" name="command" value="show_order_page">

                        <input type="hidden" name="ticketId" value="${elem.ticketId}"/>
                        <input type="submit" value="<fmt:message key="ticket.order.make"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
 </article>
</body>
</html>
