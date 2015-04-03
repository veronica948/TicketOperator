
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
    <c:if test="${not empty user}">
        <c:if test="${user.role == 0}">
            <c:redirect url="jsp/admin/administration.jsp"/>
        </c:if>
        <c:redirect url="jsp/client/main.jsp"/>
    </c:if>
    <jsp:forward page="/jsp/guest/login.jsp"/>
  </body>
</html>
