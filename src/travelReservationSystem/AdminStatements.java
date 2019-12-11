package travelReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminStatements {
	public static PreparedStatement addCustomer(Connection con) throws SQLException {
		
	}
	public static PreparedStatement addCustomerRep(Connection con) throws SQLException {

	}
	public static PreparedStatement editCustomer(Connection con) throws SQLException {

	}
	public static PreparedStatement editCustomerRep(Connection con) throws SQLException {

	}
	public static PreparedStatement deleteCustomer(Connection con) throws SQLException {

	}
	public static PreparedStatement deleteCustomerRep(Connection con) throws SQLException {

	}
	public static PreparedStatement obtainSalesReport(Connection con) throws SQLException {

	}
	public static PreparedStatement getReservationByFlightNumber(Connection con) throws SQLException {

	}
	public static PreparedStatement getReservationByCustomerName(Connection con) throws SQLException {

	}
	public static PreparedStatement getRevenueByFlight(Connection con) throws SQLException {

	}
	public static PreparedStatement getRevenueByAirline(Connection con) throws SQLException {

	}
	public static PreparedStatement getRevenueByCustomerName(Connection con, String customerName) throws SQLException {
		String statement = "SELECT c.UserID, SUM(BookingFee) AS Revenue"
				 + "FROM Ticket AS t JOIN Reservation AS r JOIN Customer AS c
				 + "ON t.ReservationID = r.ReservationID AND r.UserID = c.UserID
				 + "WHERE c.UserID = ?";
		
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, customerName);
		return ps;
	}
	public static PreparedStatement getMostRevenueCustomerName(Connection con) throws SQLException {
		String statement = "SELECT c.UserID, SUM(BookingFee) AS Revenue"
				 + "FROM Ticket AS t JOIN Reservation AS r JOIN Customer AS c"
				 + "ON t.ReservationID = r.ReservationID AND r.UserID = c.UserID"
				 + "GROUP BY c.UserID"
				 + "HAVING Revenue = (SELECT SUM(BookingFee) AS Revenue"
						   + "FROM Ticket AS t JOIN Reservation AS r JOIN Customer AS c"
						   + "ON t.ReservationID = r.ReservationID AND r.UserID = c.UserID"
						   + "GROUP BY c.UserID"
						   + "ORDER BY Revenue DESC"
						   + "LIMIT 1)"
			
		PreparedStatement ps = con.prepareStatement(statement);
		
		return ps;
	}	
	public static PreparedStatement getFlightWithMostTicketsSold(Connection con) throws SQLException {
		String statement = "SELECT s.flightNumber, COUNT(*) AS numberOfTickets"
				 + "FROM Ticket AS s"
				 + "GROUP BY s.flightNumber"
				 + "HAVING numberOfTickets = (SELECT COUNT(*) AS count"
							   + "FROM Ticket AS t"
							   + "GROUP BY t.flightNumber"
							   + "LIMIT 1)";

		PreparedStatement ps = con.prepareStatement(statement);
		
		return ps;
	}
	public static PreparedStatement getFlightsByAirport(Connection con, String airportID) throws SQLException {
		String statement = "SELECT * "
				 + "FROM Flight " 
				 + "WHERE DepartureAirport = ? AND ArrivalAirport = ?";
		
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, airportID);
		ps.setString(2, airportID);
		return ps;		    
	}
	
}
