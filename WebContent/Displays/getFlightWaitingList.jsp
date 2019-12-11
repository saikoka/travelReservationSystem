<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="travelReservationSystem.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="java.sql.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight List</title>
</head>
<body>

	<%

	try{
		//Get the database connection
		ApplicationDB db = new ApplicationDB();	
		Connection con = db.getConnection();
		
		//Create a SQL statement
		Statement stmt = con.createStatement();
		
		//Get parameters from the HTML form at the login.jsp
		int flightNumber = Integer.parseInt(request.getParameter("WaitingListForFlightNumber"));
		
		ResultSet list = CustomerRepStatements.getWaitingList(con, flightNumber);
		
		out.print("ReservationID:");
		while(list.next()){
			out.print(list.getInt(1) + "\n" );
		}
		
		
	} catch(Exception e){
		out.println(e);
		out.println("\nFailed to Authenticate User!");
	}
	
	
	%>



</body>
</html>