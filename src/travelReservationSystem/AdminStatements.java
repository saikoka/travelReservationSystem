package travelReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminStatements {
	static PreparedStatement addCustomer(Connection con, String UserID) throws SQLException {
		String statement= "INSERT INTO Customer VALUES(?)"
				+ "SELECT @UserID WHERE NOT EXISTS (SELECT UserID FROM Customer WHERE ?=@UserID)";
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1, UserID);
		ps.setString(2, UserID);
		return ps;
	}
	static PreparedStatement addCustomerRep(Connection con, String UserID) throws SQLException {
		String statement= "INSERT INTO CustomerRep VALUES(?) SELECT @UserID WHERE NOT EXISTS (SELECT UserID FROM CustomerRep WHERE ?=@UserId)";
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1, UserID);
		ps.setString(2, UserID);
		return ps;
	}
	static PreparedStatement editCustomer(Connection con) throws SQLException {

	}
	static PreparedStatement editCustomerRep(Connection con) throws SQLException {

	}
	static PreparedStatement deleteCustomer(Connection con, String UserID) throws SQLException {
			String statement= "DELETE FROM Customer WHERE UserID=?";
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, UserID);
			return ps;
	}
	static PreparedStatement deleteCustomerRep(Connection con, String UserID) throws SQLException {
			String statement= "DELETE FROM CustomerRep WHERE UserID=?";
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, UserID);
			return ps;
	}
	public static PreparedStatement obtainSalesReport(Connection con, String userID) throws SQLException {
		String statement = "SELECT r.ReservationID, SUM(t.BookingFee) AS Revenue"
				 + "FROM Ticket AS t JOIN Reservation AS r JOIN CustomerRep AS c"
				 + "ON t.ReservationID = r.ReservationID AND r.UserID = c.UserID"
				 + "WHERE c.UserID = ?"
				 + "GROUP BY r.ReservationID"
				 + "ORDER BY Revenue DESC";
		
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1, userID);
		
		return ps;
	}
	public static PreparedStatement getReservationByFlightNumber(Connection con, int flightNumber) throws SQLException {
		String statement = "SELECT r.ReservationID, r.UserID"
				 + "FROM Ticket AS t JOIN Reservation AS r"
				 + "ON t.ReservationID = r.ReservationID"
				 + "WHERE t.FlightNumber = ?";
		
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setInt(1, flightNumber);
		
		return ps;
	}
	public static PreparedStatement getReservationByCustomerName(Connection con, String userID) throws SQLException {
		String statement = "SELECT *"
				 + "FROM Reservation AS r"
				 + "WHERE r.UserID = ?"
			
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1, userID);
		
		return ps;	
	}
	public static PreparedStatement getRevenueByFlight(Connection con, int flightNumber) throws SQLException {
		String statement = "SELECT t.FlightNumber, t.BookingFee"
				 + "FROM Ticket AS t"
				 + "WHERE t.FlightNumber = ?";
		
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setInt(1, flightNumber);
		
		return ps;
	}
	public static PreparedStatement getRevenueByAirline(Connection con, String airlineID) throws SQLException {
		String statement = "SELECT a.AirlineID, f.FlightNumber, SUM(t.BookingFee) as Revenue"
				 + "FROM Ticket AS t JOIN Flight AS f JOIN Aircraft AS a"
				 + "ON t.FlightNumber = f.FlightNumber AND f.AircraftID = a.AircraftID"
				 + "WHERE a.AirlineID = ?"
				 + "GROUP BY f.FlightNumber"
				 + "ORDER BY a.AirlineID, f.FlightNumber";
				    
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1, airlineID);
		
		return ps;
	}
	public static PreparedStatement getRevenueByCustomerName(Connection con, String userID) throws SQLException {
		String statement = "SELECT c.UserID, r.ReservationID, SUM(t.BookingFee) AS Revenue"
				 + "FROM Ticket AS t JOIN Reservation AS r JOIN Customer AS c"
				 + "ON t.ReservationID = r.ReservationID AND r.UserID = c.UserID"
				 + "WHERE c.UserID = ?"
				 + "GROUP BY r.ReservationID"
				 + "ORDER BY t.BookingFee DESC";
		
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1, userID);
		
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
						   + "LIMIT 1)";
			
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
