<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>


Login:
<br>

	<form method="post" action="authenticateUser.jsp">
	<table>
	<tr>
	<td>User Name:</td><td><input type="text" name="username"></td>
	</tr>
	<tr>
	<td>Password:</td><td><input type="text" name="password"></td>
	</tr>
	</table>
	<input type="submit" value="Login">
	</form>






</body>
</html>