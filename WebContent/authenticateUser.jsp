<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="travelReservationSystem.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Authentication</title>
</head>
<body>

<!-- Access database and validate username and password! -->

	<%
	
	try{
		//Get the database connection
		ApplicationDB db = new ApplicationDB();	
		Connection con = db.getConnection();
		
		//Create a SQL statement
		Statement stmt = con.createStatement();
		
		//Get parameters from the HTML form at the login.jsp
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		
		String getUserTuple = "Select * from Users Where UserID = ?";
		
		PreparedStatement psGetUserTuple = con.prepareStatement(getUserTuple);
		
		psGetUserTuple.setString(1, username);
		
		ResultSet userTuple = psGetUserTuple.executeQuery();
		
		userTuple.next();
		String tupleUsername = userTuple.getString("UserID");
		String tuplePassword = userTuple.getString("Pass");
		
		if(tuplePassword.compareToIgnoreCase(password) == 0
				&& tupleUsername.compareToIgnoreCase(username) == 0){
			out.println();
			response.sendRedirect(request.getContextPath() + "/customerUI.jsp");
		}
		
	} catch(Exception e){
		out.println(e);
		out.println("\nFailed to Authenticate User!");
	}
	
	
	%>



</body>
</html>