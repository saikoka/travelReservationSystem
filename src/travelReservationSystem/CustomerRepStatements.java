package travelReservationSystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;

public class CustomerRepStatements {
	
	//Yogesh Patel ymp20
	public static int addTickets(Connection con,double bookingFee, String seatClass, int flightNumber, Date flightDate, int numTickets) throws SQLException {
		String day = getDay(flightDate);
		String daycheck = "Select FlightNumber From"
				+ "(Select FlightNumber from Operates Where " + day+ "= 1) AS f "
				+ "Where FlightNumber = ? ";
		System.out.println(daycheck);
		PreparedStatement psDay = con.prepareStatement(daycheck);
		
		psDay.setInt(1, flightNumber);
		
		ResultSet days = psDay.executeQuery();
		boolean dayCorrect = false;
		if(days.next()) {
			dayCorrect = true;
		} else {
			return -1;
		}
		
		String statement = ""
				+ "Insert Into Ticket (BookingFee,Class,FlightNumber,FlightDate)"
				+ "Values(?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setDouble(1, bookingFee);
		ps.setString(2, seatClass);
		ps.setInt(3, flightNumber);
		ps.setDate(4, flightDate);
		for(int i = 0; i <numTickets; i++) {
			ps.executeUpdate();
		}
		
		return 1;
	}
	

	//Yogesh Patel ymp20
	public static int createReservation(Connection con,int reservationID, String UserID) throws SQLException {
		String statement = "Insert Into Reservation Values (?,?)";
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setInt(1, reservationID);
		ps.setString(2, UserID);
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static int addFlightToReservation(Connection con, int reservationID, int ticketID) throws SQLException {
		String statement = 
				"UPDATE TABLE Ticket "
				+ "Set ReservationID = ? "
				+ "Where TicketID = ? ";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, reservationID);
		ps.setInt(2, ticketID);
		
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static int addAircraft(Connection con, int aircraftID, int airlineID) throws SQLException {
		String statement = 
				"INSERT INTO Aircraft "
				+ "Values(?,?)";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, aircraftID);
		ps.setInt(2, aircraftID);
		
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static int addAirport(Connection con, String airportID) throws SQLException {
		String statement = 
				"INSERT INTO Airport "
				+ "Values(?)";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, airportID);
		
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static int addFlight(Connection con,int flightNumber, Time arrival, Time departure, String arrivalAirport, String departureAirport) throws SQLException {
		String statement = 
				"INSERT INTO flight "
				+ "Values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, flightNumber);
		ps.setTime(2, arrival);
		ps.setTime(3, departure);
		ps.setString(4,arrivalAirport);
		ps.setString(5, departureAirport);
		
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static int editAircraft(Connection con,int aircraftID, int airlineID) throws SQLException {
		String statement = 
				"UPDATE TABLE Aircraft "
				+ "Set AirlineID = ? "
				+ "Where AircraftID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, airlineID);
		ps.setInt(2, aircraftID);
		
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static int editAirports(Connection con, String oldID, String newID) throws SQLException {
		String statement = 
				"UPDATE TABLE Airport "
				+ "Set AirportID = ? "
				+ "Where AirportID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, newID);
		ps.setString(2, oldID);
		
		return ps.executeUpdate();
	}
//	public static int editFlights(Connection con) throws SQLException {
//		
//	}
	//Yogesh Patel ymp20
	public static int deleteAircraft(Connection con, int aircraftID) throws SQLException {
		String statement = "DELETE FROM Aircraft WHERE AircraftID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, aircraftID);
		
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static int deleteAirport(Connection con, String airportID) throws SQLException {
		String statement = "DELETE FROM Airport WHERE AirportID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, airportID);
		
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static int deleteFlight(Connection con, int flightNumber) throws SQLException {
		String statement = "DELETE FROM Flight WHERE FlightNumber = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, flightNumber);
		
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static int deleteFlightFromReservation(Connection con, int reservationID, int ticketID) throws SQLException {
		String statement = 
				"UPDATE TABLE Ticket "
				+ "Set ReservationID = ? "
				+ "Where TicketID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, reservationID);
		ps.setInt(2, ticketID);
		
		return ps.executeUpdate();
	}
	//Yogesh Patel ymp20
	public static ResultSet getWaitingList(Connection con, int flightNumber) throws SQLException {
		String statement = ""
				+ "Select ReservationID from WaitingList "
				+ "Where FlightNumber = ? Order by queue ASC";
				
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setInt(1, flightNumber);
		
		return ps.executeQuery();
	}
	
	//Yogesh Patel ymp20
	private static String getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String day;
		switch(cal.get(Calendar.DAY_OF_WEEK)){
			case Calendar.SUNDAY: day = "Sun";
			break;
			case Calendar.MONDAY: day = "Mon";
			break;
			case Calendar.TUESDAY: day = "Tues";
			break;
			case Calendar.WEDNESDAY: day = "Wed";
			break;
			case Calendar.THURSDAY: day = "Thur";
			break;
			case Calendar.FRIDAY: day = "Fri";
			break;
			case Calendar.SATURDAY: day = "Sat";
			break;
			default: day = null;
			break;
		}
		return day;
	}
	
}
