
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
<c:import url="fragment/header.jsp"/>
<c:if test="${user.role == 1}">
    <c:import url="../client/fragment/clientMenu.jsp"/>
</c:if>
<c:if test="${user.role == 0}">
    <c:import url="../admin/fragment/adminMenu.jsp"/>
</c:if>
<article>
    <table><tr><td>
<fmt:message key="order.number"/>:
        </td>
        <td>
${order.orderId}
</td> </tr><tr>
        <td>
<fmt:message key="order.date"/>: </td><td>
<fmt:formatDate value="${order.orderDate}"/>
    </td> </tr><tr>
        <td>

<fmt:message key="order.status"/>: </td><td>
${order.orderStatus}
    </td> </tr><tr>
        <td>
<fmt:message key="login"/>:   </td><td>
${user.login}
    </td> </tr><tr>
        <td>
<fmt:message key="registration.firstname"/>:   </td><td>
${firstName}
    </td> </tr><tr>
        <td>
<fmt:message key="registration.lastname"/>:  </td><td>
${lastName}
    </td> </tr><tr>
        <td>
<fmt:message key="movie.name"/>:  </td><td>
${seance.movieName}
    </td> </tr><tr>
        <td>
<fmt:message key="seance.date"/>:    </td><td>
<fmt:formatDate value="${seance.date}"/>
    </td> </tr><tr>
        <td>
<fmt:message key="seance.time"/>: </td><td>
<fmt:formatDate value="${seance.time}" type="time" timeStyle="short"/>
    </td> </tr><tr>
        <td>
<fmt:message key="ticket.row"/>: </td><td>
${ticket.row}
    </td> </tr><tr>
        <td>
<fmt:message key="ticket.place"/>:  </td><td>
${ticket.place}
    </td> </tr><tr>
        <td>
<fmt:message key="seance.price"/>:  </td><td>
${ticket.price}
</td></tr></table>
<c:if test="${order.orderStatus.equals('Забронирован')}">
    <c:if test="${user.role == 1}">
    <form action="${pageContext.request.contextPath}/controller" method="POST">
        <input type="hidden" name="command" value="change_order_status"/>
        <input type="hidden" name="orderId" value="${order.orderId}"/>
        <input type="hidden" name="orderStatus" value="Отменен"/>
        <input type="submit" value="<fmt:message key="order.cancel"/>">
    </form>
    </c:if>
    <c:if test="${user.role == 0}">
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="change_order_status"/>
            <input type="hidden" name="orderId" value="${order.orderId}"/>
            <input type="hidden" name="orderStatus" value="Получен"/>
            <input type="submit" value="<fmt:message key="order.take"/>">
        </form>
        <br/>
        <form action="${pageContext.request.contextPath}/controller" method="POST">
            <input type="hidden" name="command" value="change_order_status"/>
            <input type="hidden" name="orderId" value="${order.orderId}"/>
            <input type="hidden" name="orderStatus" value="Аннулирован"/>
            <input type="submit" value="<fmt:message key="order.revoke"/>">
        </form>
    </c:if>
</c:if>
    </article>
</body>
</html>
