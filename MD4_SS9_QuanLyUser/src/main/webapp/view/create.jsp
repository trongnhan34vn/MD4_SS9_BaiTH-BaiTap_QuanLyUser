<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhan
  Date: 03/05/2023
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New User</title>
</head>
<body>
<h1>Create New User</h1>
<c:if test="${message != null}">
  <h3 style="color: green;">${message}</h3>
</c:if>
<h4>
  <a href="user-servlet?action=show">Back To Home</a>
</h4>
<form method="post">
  <table border="1">
    <tr>
      <td>UserName: </td>
      <td><input name="name" type="text"></td>
    </tr>
    <tr>
      <td>User Email: </td>
      <td><input name="email" type="text"></td>
    </tr>
    <tr>
      <td>Country: </td>
      <td><input name="country" type="text"></td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="save">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
