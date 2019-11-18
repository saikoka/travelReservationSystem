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
		
		
		
		String getUserTuple = "Select * from Users Where UserID = ? AND Pass = ?";
		
		PreparedStatement psGetUserTuple = con.prepareStatement(getUserTuple);
		
		psGetUserTuple.setString(1, username);
		psGetUserTuple.setString(2, password);
		
		ResultSet userTuple = psGetUserTuple.executeQuery();
		
		if(userTuple.next()){
			session.setAttribute("user", username);
			response.sendRedirect(request.getContextPath() + "/customerUI.jsp");
		}
		else{
			out.println("Invalid password <a href='login.jsp'>try again</a>");
		}
		
		
	} catch(Exception e){
		out.println(e);
		out.println("\nFailed to Authenticate User!");
	}
	
	
	%>



</body>
</html>