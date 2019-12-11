<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="travelReservationSystem.*, java.util.concurrent.TimeUnit"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Authentication</title>
</head>
<body>

<%
try{
	int flightNumber = Integer.parseInt(request.getParameter("FlightNumberTOAdd"));
	Time arrival = Time.valueOf(request.getParameter("ArrivalTimeTOAdd"));
	Time departure = Time.valueOf(request.getParameter("DepartureTimeTOAdd"));
	String arrivalAirport = request.getParameter("ArrivalairportTOAdd");
	String departureAirport = request.getParameter("DepartureairportTOAdd");
	
	ApplicationDB db = new ApplicationDB();	
	Connection con = db.getConnection();
	Statement stmt = con.createStatement();
	
	CustomerRepStatements.addFlight(con, flightNumber, arrival, departure, arrivalAirport, departureAirport);
	con.close();
	out.print("Success! Redirecting back to portal");
	//TimeUnit.SECONDS.sleep(3);
	response.sendRedirect(request.getContextPath() + "/customerRepPortal.jsp");
} catch(Exception e){
	out.println(e);
	out.print("Could not add Flight");
}

%>

</body>
</html>