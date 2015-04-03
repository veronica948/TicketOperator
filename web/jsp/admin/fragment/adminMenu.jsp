<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 30.11.2014
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${locale}" scope="session" />
<fmt:setBundle basename="resources.pagecontent" />
<html>
<head>
    <title></title>
</head>
<body>
  <nav>
      <ul>
          <li>
          <a href="${ pageContext.request.contextPath }/jsp/admin/administration.jsp"><fmt:message key="admin.title"/></a>

          </li>
          <li class="title"> <a href="#" ><fmt:message key="data.change"/> </a>
              <ul class="submenu">
                  <li>
                      <a href="${ pageContext.request.contextPath }/jsp/admin/addMovie.jsp"><fmt:message key="admin.movie.add"/></a>
                  </li>
                  <li>
                      <a href="${pageContext.request.contextPath}/controller?command=get_movies&pageNumber=0"><fmt:message key="admin.movie.delete" /></a>
                  </li>
                  <li>
                      <a href ="${pageContext.request.contextPath}/controller?command=get_movies&pageNumber=2"><fmt:message key="admin.movie.change"/></a>
                  </li>
                  <li>
                      <a href ="${pageContext.request.contextPath}/controller?command=get_movies&pageNumber=1"><fmt:message key="admin.seance.add"/></a>
                  </li>
              </ul>

          </li>

          <li>
    <a href="${ pageContext.request.contextPath }/controller?command=show_all_seances"><fmt:message key="seance.title"/></a>
          </li>
          <li>
    <a href="${pageContext.request.contextPath}/jsp/admin/orderSearch.jsp"><fmt:message key="admin.order.find"/></a>
          </li>
          <li>
    <a href="${pageContext.request.contextPath}/controller?command=show_orders"><fmt:message key="orders"/></a>
          </li>
          <li>
    <a href="${ pageContext.request.contextPath }/jsp/client/main.jsp"><fmt:message key="admin.site.view"/></a>
          </li>
      </ul>

</nav>
</body>
</html>
