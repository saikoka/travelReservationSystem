package travelReservationSystem;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import javax.servlet.http.*;
import javax.servlet.*;

public final class UserStatements {
	//TODO: Add NumSeatsAvailable to flight in mysql 
	//TODO: Add auto increment to ReservationID and TicketID in mysql
	//TODO: Add random data to Database to run queries against
	//TODO: Rest of queries lol
	
	//Yogesh Patel ymp20
	static PreparedStatement getFlightList(Connection con, Date departure, String departureAirport, String arrivalAirport) throws SQLException {
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
	static PreparedStatement getFlexibleFlightList(Connection con, Date departure, String departureAirport, String arrivalAirport) throws SQLException {
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

}
