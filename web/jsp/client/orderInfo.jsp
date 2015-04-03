<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 04.12.2014
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
    <title><fmt:message key="ticket.order"/> </title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
<c:if test="${not empty orderNumber}">
      <fmt:message key="order.number"/>: ${orderNumber}
</c:if>
    <br/>
${existOrder}
${impossibleOperation}
<a href="${ pageContext.request.contextPath }/jsp/client/main.jsp"><fmt:message key="page.to.main"/></a>
    </article>
</body>
</html>
