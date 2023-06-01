<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Admin Page</title>
</head>
<body>
  <div>
    <table>
        <thead>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Roles</th>
        </thead>
        <c:forEach items ="${AllUsers}" var = "user">
            <tr>
              <td>${user.id}</td>
              <td>${user.username}</td>
              <td>${user.password}</td>
              <td>
                <c:forEach items="${user.roles}" var="role">${role.name};</c:forEach>
              </td>
              <td>
                <form action="${pageContext.request.contextPath}/admin" method="POST">
                <input type="hidden" name="userId" value="${user.id}"/>
                <input type="hidden" name="action" value="delete"/>
                <button type="submit">Delete</button>
                </form>
              </td>
            </tr>
        </c:forEach>

    </table>
    <a href="/">Главная</a>
  </div>
</body>
</html>