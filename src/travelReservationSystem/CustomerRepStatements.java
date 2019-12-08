package travelReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class CustomerRepStatements {
	//TODO: Add cascades to the database

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
	static PreparedStatement addFlight(Connection con,int flightNumber, Time arrival, Time departure, String arrivalAirport, String departureAirport,int aircraftNumber ) throws SQLException {
		String statement = 
				"INSERT INTO flight"
				+ "Values(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, flightNumber);
		ps.setTime(2, arrival);
		ps.setTime(3, departure);
		ps.setString(4,arrivalAirport);
		ps.setString(5, departureAirport);
		ps.setInt(6,aircraftNumber);
		
		return ps;
	}

	static PreparedStatement editAircraft(Connection con,int aircraftID, int airlineID) throws SQLException {
		String statement = 
				"UPDATE TABLE Aircraft"
				+ "Set AirlineID = ?"
				+ "Where AircraftID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, airlineID);
		ps.setInt(2, aircraftID);
		
		return ps;
	}
	static PreparedStatement editAirports(Connection con, String oldID, String newID) throws SQLException {
		String statement = 
				"UPDATE TABLE Airport"
				+ "Set AirportID = ?"
				+ "Where AirportID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, newID);
		ps.setString(2, oldID);
		
		return ps;
	}
	static PreparedStatement editFlights(Connection con) throws SQLException {
		
	}
	static PreparedStatement deleteAircraft(Connection con, String aircraftID) throws SQLException {
		String statement = "DELETE FROM Aircraft WHERE AircraftID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, aircraftID);
		
		return ps;
	}
	static PreparedStatement deleteAirport(Connection con, String airportID) throws SQLException {
		String statement = "DELETE FROM Airport WHERE AirportID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, airportID);
		
		return ps;
	}
	static PreparedStatement deleteFlight(Connection con, int flightNumber) throws SQLException {
		String statement = "DELETE FROM Flight WHERE FlightNumber = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, flightNumber);
		
		return ps;
	}
}
