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
		String from = request.getParameter("fromTextBox");
		String to = request.getParameter("toTextBox");
		String type = request.getParameter("type");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		ResultSet flights;
		
		if(type.equals("oneway")){
			PreparedStatement ps = UserStatements.getFlightList(con, Date.valueOf(startDate), from, to);
			flights = ps.executeQuery();
			String headers = "FlightNumber\tArrivalTime\tDepartureTime\tArrivalAirport\tDeparetureAirport\tAircraftNumber\n";
			out.print(headers);
			while(flights.next()){
				out.print(flights.getInt(1) + "\t" 
				+ flights.getTime(2) + "\t"
				+ flights.getTime(3) + "\t"
				+ flights.getTime(4) + "\t"
				+ flights.getTime(5) + "\t"
				+ flights.getInt(6) + "\t\n");
			}
		} else if(type.equals("roundtrip")){
			out.print("First Flight");
			PreparedStatement ps = UserStatements.getFlexibleFlightList(con, Date.valueOf(startDate), from, to);
			flights = ps.executeQuery();
			String headers = "FlightNumber\tArrivalTime\tDepartureTime\tArrivalAirport\tDeparetureAirport\tAircraftNumber\n";
			out.print(headers);
			while(flights.next()){
				out.print(flights.getInt(1) + "\t" 
				+ flights.getTime(2) + "\t"
				+ flights.getTime(3) + "\t"
				+ flights.getTime(4) + "\t"
				+ flights.getTime(5) + "\t"
				+ flights.getInt(6) + "\t\n");
			}
			
			out.print("Return Flight");
			ps = UserStatements.getFlexibleFlightList(con, Date.valueOf(startDate), from, to);
			flights = ps.executeQuery();
			headers = "FlightNumber\tArrivalTime\tDepartureTime\tArrivalAirport\tDeparetureAirport\tAircraftNumber\n";
			out.print(headers);
			while(flights.next()){
				out.print(flights.getInt(1) + "\t" 
				+ flights.getTime(2) + "\t"
				+ flights.getTime(3) + "\t"
				+ flights.getTime(4) + "\t"
				+ flights.getTime(5) + "\t"
				+ flights.getInt(6) + "\t\n");
			}

		} else if(type.equals("flexible")){
			PreparedStatement ps = UserStatements.getFlexibleFlightList(con, Date.valueOf(startDate), from, to);
			flights = ps.executeQuery();
			String headers = "FlightNumber\tArrivalTime\tDepartureTime\tArrivalAirport\tDeparetureAirport\tAircraftNumber\n";
			out.print(headers);
			while(flights.next()){
				out.print(flights.getInt(1) + "\t" 
				+ flights.getTime(2) + "\t"
				+ flights.getTime(3) + "\t"
				+ flights.getTime(4) + "\t"
				+ flights.getTime(5) + "\t"
				+ flights.getInt(6) + "\t\n");
			}
		}
		
		
	} catch(Exception e){
		out.println(e);
		out.println("\nFailed to Authenticate User!");
	}
	
	
	%>



</body>
</html>