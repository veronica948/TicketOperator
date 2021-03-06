<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 07.12.2014
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="client.orders"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
      <h4><fmt:message key="client.orders"/></h4>
${notFound}
<c:if test="${not empty orders}">
    <table border="1">
        <tr>
            <th><fmt:message key="order.number"/></th>
            <th><fmt:message key="order.date"/></th>
            <th><fmt:message key="order.status"/></th>
            <th><fmt:message key="order.show"/></th>
        </tr>
        <c:forEach var="elem" items="${orders}">
            <tr>
                <td><c:out value="${elem.orderId}"/></td>
                <td><fmt:formatDate value="${elem.orderDate}"/></td>
                <td><c:out value="${elem.orderStatus}"/></td>
                <td><a href="${ pageContext.request.contextPath }/controller?command=find_order&orderId=${elem.orderId}">
                    <fmt:message key="order.show"/>
                </a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
    </article>
</body>
</html>
