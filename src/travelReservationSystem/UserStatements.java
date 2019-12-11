package travelReservationSystem;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.*;
import javax.servlet.*;

public final class UserStatements {
	
	//Yogesh Patel ymp20
	public static PreparedStatement createReservation(Connection con, String UserID) throws SQLException {
		String statement = "Insert Into Reservation(UserID) Values (?)";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, UserID);
		
		return ps;
	}

	//Yogesh Patel ymp20
	public static PreparedStatement getPastReservations(Connection con, String UserID) throws SQLException {
		String statement = "Select ReservationID From Reservation Where UserID = ? & ReservationID in ("
				+ "Select ReservationID From Ticket Where UserID = ? & DATE(FlightDate) <= ?";
		PreparedStatement ps = con.prepareStatement(statement);
		Calendar cal = Calendar.getInstance();
		Date now = new Date(cal.getTimeInMillis());
		ps.setString(1, UserID);
		ps.setString(2, UserID);
		ps.setDate(3, now);
		return ps;
	}
	//Yogesh Patel ymp20
	public static PreparedStatement getFutureReservations(Connection con, String UserID) throws SQLException {
		String statement = "Select ReservationID From Reservation Where UserID = ? & ReservationID in ("
				+ "Select ReservationID From Ticket Where UserID = ? & DATE(FlightDate) >= ?";
		PreparedStatement ps = con.prepareStatement(statement);
		Calendar cal = Calendar.getInstance();
		Date now = new Date(cal.getTimeInMillis());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MMM-dd");
		ps.setString(1, UserID);
		ps.setString(2, UserID);
		ps.setDate(3, now);
		return ps;
	}
	//Yogesh Patel ymp20
	public static PreparedStatement getReservations(Connection con, String UserID, Date date) throws SQLException {
		String statement = "Select ReservationID From Reservation Where UserID = ? ";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, UserID);
		
		return ps;
	}
	//Yogesh Patel ymp20
	public static PreparedStatement buyTicket(Connection con, int reservationID, int ticketID) throws SQLException {
		String statement = "Update Ticket Set ReservationID = ? Where TicketID = ?";
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, Integer.toString(reservationID));
		ps.setString(2, Integer.toString(ticketID));
		
		return ps;
	}
	//Yogesh Patel ymp20
	public static PreparedStatement getFlightListByDepartureTime(Date Depart) {
		return null;
	}
	//Yogesh Patel ymp20
	public static PreparedStatement getFlightList(Connection con, Date departure, String departureAirport, String arrivalAirport) throws SQLException {
		String departureDay = getDay(departure);
		String statement = "Select * "
				+ "From Flight "
				+ "where DepartureAirport = ? AND ArrivalAirport = ? AND FlightNumber in "
				+ "(Select FlightNumber From Operates Where ? = 1)";
		
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, departureAirport);
		ps.setString(2, arrivalAirport);
		ps.setString(3, departureDay);
		return ps;
	}
	//Yogesh Patel ymp20
	public static PreparedStatement getFlexibleFlightList(Connection con, Date departure, String departureAirport, String arrivalAirport) throws SQLException {
		int departureDay = getDayInt(departure);
		String statement = "Select * "
				+ "From Flight "
				+ "where DepartureAirport = ? AND ArrivalAirport = ? AND FlightNumber in "
				+ "(Select FlightNumber From Operates Where ? = 1 OR ? = 1 OR ? = 1 OR ? = 1 OR ? = 1 OR ? = 1 OR ? = 1)";
		
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, departureAirport);
		ps.setString(2, arrivalAirport);
		ps.setString(3, "Sun");
		ps.setString(4, "Mon");
		ps.setString(5, "Tues");
		ps.setString(6, "Wed");
		ps.setString(7, "Thur");
		ps.setString(8, "Fri");
		ps.setString(9, "Sat");
//		ps.setString(3, dayIntToString(departureDay - 3));
//		ps.setString(4, dayIntToString(departureDay - 2));
//		ps.setString(5, dayIntToString(departureDay - 1));
//		ps.setString(6, dayIntToString(departureDay));
//		ps.setString(7, dayIntToString(departureDay + 1));
//		ps.setString(8, dayIntToString(departureDay + 2));
//		ps.setString(9, dayIntToString(departureDay + 3));

		return ps;
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
	
	
	//Yogesh Patel ymp20
	private static int getDayInt(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	//Yogesh Patel ymp20
	private static String dayIntToString(int dayInt) {
		Calendar cal = Calendar.getInstance();
		String day;
		switch(dayInt){
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
	public static PreparedStatement sortByPrice(Connection con, Date Departure) throws SQLException {
		String DepartureDay= getDay(Departure);
		String statement= "SELECT * FROM Flight NATURAL JOIN Ticket WHERE FlightNumber in (SELECT FlightNumber FROM Operates WHERE ?=1) ORDER BY BookingFee";
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1, DepartureDay);
		return ps;
			
	}
	
	public static PreparedStatement sortByTakeOff(Connection con, Date Departure) throws SQLException {
		String DepartureDay= getDay(Departure);
		String statement= "SELECT * FROM Flight WHERE FlightNumber in (SELECT FlightNumber FROM Operates WHERE ?=1) ORDER BY DepartureTime" ;
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1,DepartureDay);
		return ps;
	}
	
	public static PreparedStatement sortByLandingTime(Connection con, Date Arrival) throws SQLException {
		String ArrivalDay = getDay(Arrival);
		String statement = "SELECT * FROM FLIGHT WHERE FlightNumber in (SELECT FlightNumber FROM Operates WHERE ?=1) ORDER BY ArrivalTime";
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1,ArrivalDay);
		return ps;
	}
	
	public static PreparedStatement getFlightListByPrice(Connection con, Date departure, String departureAirport, String arrivalAirport, int price) throws SQLException {
		String departureDay = getDay(departure);
		String statement = "Select * "
				+ "From Flight NATURAL JOIN Ticket"
				+ "where DepartureAirport = ? AND ArrivalAirport = ? AND BookingFee < ? AND FlightNumber in "
				+ "(Select FlightNumber From Operates Where ? = 1) ";
		
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, departureAirport);
		ps.setString(2, arrivalAirport);
		ps.setInt(3, price);
		ps.setString(4, departureDay);
		return ps;
	}
	public static PreparedStatement filterbyNumStops(Connection con) {
		
	}
	public static PreparedStatement filterbyAirline(Connection con, int Airline, Date departure, String DepartureAirport, String ArrivalAirport) throws SQLException{
		String departureDay = getDay(departure);
		String statement = "SELECT * FROM FLIGHT INNER JOIN Aircraft ON Flight.AircraftNumber=Aircraft.AircraftID WHERE DepartureAirport=? "
				+ "AND ArrivalAirport= ? AND AirlineID=? AND FlightNumber in (SELECT FlightNumber FROM Operates WHERE ?=1)";
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1,DepartureAirport);
		ps.setString(2, ArrivalAirport);
		ps.setInt(3, Airline);
		ps.setString(4, departureDay);
		return ps;
	}
	public static PreparedStatement cancelReservation(Connection con, String UserID, String ReservationID ) throws SQLException{
		String statement= "DELETE FROM Reservation WHERE UserID = ? AND ReservationID= ?";
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setString(1, UserID);
		ps.setString(2, ReservationID);
		return ps;
	}
	public static PreparedStatement enterWaitingList(Connection con, int reservationID, int FlightNumber, int queue) throws SQLException{
		String statement= "INSERT INTO WaitingList(FlightNumber, ReservationID, queue) VALUES(?,?,?)";
		PreparedStatement ps = con.prepareStatement(statement);
		ps.setInt(1, FlightNumber);
		ps.setInt(2, reservationID);
		ps.setInt(3, queue);
		return ps;
	}

}
