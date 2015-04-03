<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 07.12.2014
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
    <title>${user.login}</title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/clientMenu.jsp"/>
<article>
     <a href="${pageContext.request.contextPath}/controller?command=show_client_orders"><fmt:message key="client.orders"/></a>
    <br/>
     <a href="${pageContext.request.contextPath}/controller?command=get_client_data"><fmt:message key="client.data.change"/></a>
    <br/>
     <a href="${pageContext.request.contextPath}/jsp/client/changePassword.jsp"><fmt:message key="client.password.change"/></a>
<br/>
${successfulOperation}
${dataChanged}
<br/>
${nameEmpty}
${incorrectEmail}
    ${incorrectNumberFormat}
<br/>
    </article>
</body>
</html>
