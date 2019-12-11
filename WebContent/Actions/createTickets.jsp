<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="travelReservationSystem.*, java.util.concurrent.TimeUnit"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,java.sql.Date"%>
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
	double bookingFee = Double.parseDouble(request.getParameter("BookingFeeForNewTicket"));
	String seatClass = request.getParameter("ClassfornewTickets");
	int flightNumber = Integer.parseInt(request.getParameter("FlightnumberfornewTIckets"));
	Date flightDate = Date.valueOf(request.getParameter("FlightdatefornewTIckets"));
	int numTickets = Integer.parseInt(request.getParameter("NumTickets"));
	
	ApplicationDB db = new ApplicationDB();	
	Connection con = db.getConnection();
	Statement stmt = con.createStatement();
	
	CustomerRepStatements.addTickets(con, bookingFee, seatClass, flightNumber, flightDate, numTickets);
	
	con.close();
	out.print("Success! Redirecting back to portal");
	//TimeUnit.SECONDS.sleep(3);
	response.sendRedirect(request.getContextPath() + "/customerRepPortal.jsp");
} catch(Exception e){
	out.println(e);
	out.print("Could not add ticket to reservation");
}

%>

</body>
</html>