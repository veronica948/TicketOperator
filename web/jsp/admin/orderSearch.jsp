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
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="admin.order.find"/></title>
</head>
<body>
<c:import url="../common/fragment/header.jsp"/>
<c:import url="fragment/adminMenu.jsp"/>
<article>
    <ctg:orderSearchForm/>
${notFound}
   ${incorrectNumberFormat}
    ${successfulOperation}
    </article>
</body>
</html>
