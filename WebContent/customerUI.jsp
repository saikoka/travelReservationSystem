<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer UI</title>
</head>
<body>

	<%
	    if ((session.getAttribute("user") == null)) {
	%>
	You are not logged in<br/>
	<a href="login.jsp">Please Login</a>
	<%} else {
	%>
	Welcome <%=session.getAttribute("user")%> 
	<a href='logout.jsp'>Log out</a>
	<%
	    }
	%>
</body>
</html>