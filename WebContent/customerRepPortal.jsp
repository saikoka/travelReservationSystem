<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="travelReservationSystem.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

Create Tickets:
<br>

	<form method="post" action="Actions/createTickets.jsp">
	<table>
	<tr>
	<td>BookingFee:</td><td><input type="number" name="BookingFeeForNewTicket" step = ".01"></td>
	</tr>
	<tr>
	<td>Class (first, business, economy):</td><td><input type="text" name="ClassfornewTickets"></td>
	</tr>
	<tr>
	<td>FlightNumber:</td><td><input type="number" name="FlightnumberfornewTIckets"></td>
	</tr>
	<tr>
	<td>FlightDate:(yyyy-mm-dd):</td><td><input type="text" name="FlightdatefornewTIckets"></td>
	</tr>
	<tr>
	<td>NumTickets:</td><td><input type="Number" name="NumTickets"></td>
	</tr>
	</table>
	<input type="submit" value="Create">
	</form>
<br>


Create Reservation:
<br>

	<form method="post" action="Actions/createReservation.jsp">
	<table>
	<tr>
	<td>ReservationID:</td><td><input type="number" name="resIDForNewRes"></td>
	</tr>
	<tr>
	<td>User Name:</td><td><input type="text" name="createResForCust"></td>
	</tr>
	</table>
	<input type="submit" value="Create">
	</form>
<br>

Add Ticket to Reservation:
<br>

	<form method="post" action="Actions/addTicketToReservation.jsp">
	<table>
	<tr>
	<td>ReservationID:</td><td><input type="number" name="addTickToThisRes"></td>
	</tr>
	<tr>
	<td>TicketID:</td><td><input type="number" name="ticktoAdd"></td>
	</tr>
	</table>
	<input type="submit" value="Add">
	</form>
<br>

Delete Ticket From Reservation:
<br>

	<form method="post" action="Actions/deleteFlightFromReservation.jsp">
	<table>
	<tr>
	<td>ReservationID:</td><td><input type="number" name="rmTickFromThisRes"></td>
	</tr>
	<tr>
	<td>TicketID:</td><td><input type="number" name="ticktorm"></td>
	</tr>
	</table>
	<input type="submit" value="Delete">
	</form>
<br>

Add Aircraft:
<br>

	<form method="post" action="Actions/addAircraft.jsp">
	<table>
	<tr>
	<td>AircraftID:</td><td><input type="number" name="aircraftToAdd"></td>
	</tr>
	<tr>
	<td>AirlineID:</td><td><input type="number" name="airlineToAddAircraftTo"></td>
	</tr>
	</table>
	<input type="submit" value="Add">
	</form>
<br>

Edit Aircraft Owner:
<br>

	<form method="post" action="Actions/editAircraft.jsp">
	<table>
	<tr>
	<td>AircraftID:</td><td><input type="number" name="aircraftIDToSell"></td>
	</tr>
	<tr>
	<td>AirlineID:</td><td><input type="number" name="newAirline"></td>
	</tr>
	</table>
	<input type="submit" value="Transfer Ownership">
	</form>
<br>

Delete Aircraft:
<br>

	<form method="post" action="Actions/deleteAircraft.jsp">
	<table>
	<tr>
	<td>AircraftID:</td><td><input type="number" name="aircraftIDToDelete"></td>
	</tr>
	</table>
	<input type="submit" value="Remove Aircraft">
	</form>
<br>

Add Airport:
<br>

	<form method="post" action="Actions/addAirport.jsp">
	<table>
	<tr>
	<td>AirportID:</td><td><input type="text" name="airportTOAdd"></td>
	</tr>
	</table>
	<input type="submit" value="Add">
	</form>
<br>

Delete Airport:
<br>

	<form method="post" action="Actions/deleteAirport.jsp">
	<table>
	<tr>
	<td>AirportID:</td><td><input type="text" name="airportTODelete"></td>
	</tr>
	</table>
	<input type="submit" value="Delete">
	</form>
<br>

Add Flight:
<br>

	<form method="post" action="Actions/addFlight.jsp">
	<table>
	<tr>
	<td>FlightNumber:</td><td><input type="number" name="FlightNumberTOAdd"></td>
	</tr>
	<tr>
	<td>ArrivalTime:</td><td><input type="text" name="ArrivalTimeTOAdd"></td>
	</tr>
	<tr>
	<td>DepartureTime: (hh:mm:ss)</td><td><input type="text" name="DepartureTimeTOAdd"></td>
	</tr>
	<tr>
	<td>ArrivalAirport:(hh:mm:ss)</td><td><input type="text" name="ArrivalairportTOAdd"></td>
	</tr>
	<tr>
	<td>DepartureAirport:</td><td><input type="text" name="DepartureairportTOAdd"></td>
	</tr>
	</table>
	<input type="submit" value="Add">
	</form>
<br>

Delete Flight:
<br>

	<form method="post" action="Actions/deleteFlight.jsp">
	<table>
	<tr>
	<td>FlightNumber:</td><td><input type="number" name="FlightNumberTODelete"></td>
	</tr>
	</table>
	<input type="submit" value="Delete">
	</form>
<br>

Get Waiting List For Flight:
<br>

	<form method="post" action="Displays/getFlightWaitingList.jsp">
	<table>
	<tr>
	<td>FlightNumber:</td><td><input type="number" name="WaitingListForFlightNumber"></td>
	</tr>
	</table>
	<input type="submit" value="Delete">
	</form>
<br>
</body>
</html>