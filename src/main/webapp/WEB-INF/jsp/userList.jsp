<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/17
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user</title>
</head>
<body>
    <c:forEach items="${userLiset}" var="user">

        id:${user.id}<br/>
        email:${user.email}<br/>
        username:${user.username}<br/>
        role:${user.role}<br/>
        <%--mobile:${requestScope.user.mobile}<br/>--%>
    </c:forEach>

</body>
</html>
