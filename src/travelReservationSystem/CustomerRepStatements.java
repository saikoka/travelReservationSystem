package travelReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepStatements {
	//hello
	//Yogesh Patel ymp20
	static PreparedStatement createReservation(Connection con, String UserID) throws SQLException {
		String statement = "Insert Into Reservation(UserID) Values (?)";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, UserID);
		
		return ps;
	}
	//Yogesh Patel ymp20
	static PreparedStatement addFlightToReservation(Connection con, int reservationID, int ticketID) throws SQLException {
		String statement = 
				"UPDATE TABLE Ticket"
				+ "Set ReservationID = ?"
				+ "Where TicketID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, reservationID);
		ps.setInt(2, ticketID);
		
		return ps;
	}
	//Yogesh Patel ymp20
	static PreparedStatement addAircraft(Connection con, int aircraftID, int airlineID) throws SQLException {
		String statement = 
				"INSERT INTO Aircraft"
				+ "Values(?,?)";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, aircraftID);
		ps.setInt(2, aircraftID);
		
		return ps;
	}
	static PreparedStatement addAirport(Connection con, String airportID) throws SQLException {
		String statement = 
				"INSERT INTO Airport"
				+ "Values(?)";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, airportID);
		
		return ps;
	}
	static PreparedStatement addFlight(Connection con) throws SQLException {
		
	}
	static PreparedStatement editAircraft(Connection con) throws SQLException {

	}
	static PreparedStatement editAirports(Connection con) throws SQLException {

	}
	static PreparedStatement editFlights(Connection con) throws SQLException {

	}
	static PreparedStatement deleteAircraft(Connection con) throws SQLException {

	}
	static PreparedStatement deleteAirport(Connection con) throws SQLException {

	}
	static PreparedStatement deleteFlight(Connection con) throws SQLException {

	}
}
