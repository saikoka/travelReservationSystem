<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="travelReservationSystem.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>

<!-- Add new username and Password combo to database if doesnt exsist -->
	<%
	try{
		
		//Get the database connection
		ApplicationDB db = new ApplicationDB();	
		Connection con = db.getConnection();
		
		//Create a SQL statement
		Statement stmt = con.createStatement();
		
		//Get parameters from the HTML form at the login.jsp
		String username = request.getParameter("rusername");
		String password = request.getParameter("rpassword");
		
		String createUser = "Insert into Users(UserID, Pass)" 
			+ "Values (?,?)";
		String addUserToCustomer = "Insert Into Customer(UserID)"
				+ "Values (?)";
		
		//Create a Prepared SQL statement allowing you to introduce the parameters of the query
		PreparedStatement psCreateUser = con.prepareStatement(createUser);
		PreparedStatement psAddUserToCustomers = con.prepareStatement(addUserToCustomer);
		
		//Add parameters of the query. Start with 1, the 0-parameter is the INSERT statement itself
		psCreateUser.setString(1, username);
		psCreateUser.setString(2, password);
		psAddUserToCustomers.setString(1,username);
		
		//Run the query against the DB
		psCreateUser.executeUpdate();
		psAddUserToCustomers.executeUpdate();

		//Close the connection. Don't forget to do it, otherwise you're keeping the resources of the server allocated.
		con.close();

		out.print("Created New User!");
		
		
		
	} catch (Exception e){
		out.println(e);
		out.println("User Add Failed!!");
	}
	
	%>
	
	<form method="post" action="login.jsp">
	<input type = "submit" value="Back to Login Page">
	</form>
</body>
</html>