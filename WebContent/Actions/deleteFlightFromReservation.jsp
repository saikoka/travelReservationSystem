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
	int reservationID = Integer.parseInt(request.getParameter("rmTickFromThisRes"));
	int ticketID = Integer.parseInt(request.getParameter("ticktorm"));
	ApplicationDB db = new ApplicationDB();	
	Connection con = db.getConnection();
	Statement stmt = con.createStatement();
	CustomerRepStatements.deleteFlightFromReservation(con, reservationID, ticketID);
	con.close();
	out.print("Success! Redirecting back to portal");
	//TimeUnit.SECONDS.sleep(3);
	response.sendRedirect(request.getContextPath() + "/customerRepPortal.jsp");
} catch(Exception e){
	out.println(e);
	out.print("Could remove ticket from reservation");
}

%>

</body>
</html>