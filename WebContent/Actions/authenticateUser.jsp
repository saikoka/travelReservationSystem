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
		
		String customerCheck = "Select * from Customer Where UserID = ?";
		String customerRepCheck = "Select * from CustomerRep Where UserID = ?";
		String adminCheck = "Select * from Admin Where UserID = ?";
		
		PreparedStatement psCustomer = con.prepareStatement(customerCheck);
		PreparedStatement psCustomerRep = con.prepareStatement(customerRepCheck);
		PreparedStatement psAdmin = con.prepareStatement(adminCheck);
		
		psCustomer.setString(1, username);
		psCustomerRep.setString(1, username);
		psAdmin.setString(1, username);
		
		ResultSet isCustomer = psCustomer.executeQuery();
		ResultSet isCustomerRep = psCustomerRep.executeQuery();
		ResultSet isAdmin = psAdmin.executeQuery();
		if(userTuple.next()){
			if(isCustomer.next()){
				session.setAttribute("user", username);
				response.sendRedirect(request.getContextPath() + "/home.jsp");
			} else if(isCustomerRep.next()){
				session.setAttribute("user", username);
				System.out.println(request.getContextPath());
				response.sendRedirect(request.getContextPath() + "/customerRepPortal.jsp");
			} else if(isAdmin.next()){
				session.setAttribute("user", username);
				response.sendRedirect(request.getContextPath() + "/adminPortal.jsp");
			}
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