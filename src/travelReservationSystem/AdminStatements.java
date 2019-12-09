package travelReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminStatements {
	static PreparedStatement addCustomer(Connection con) throws SQLException {
		
	}
	static PreparedStatement addCustomerRep(Connection con) throws SQLException {

	}
	static PreparedStatement editCustomer(Connection con) throws SQLException {

	}
	static PreparedStatement editCustomerRep(Connection con) throws SQLException {

	}
	static PreparedStatement deleteCustomer(Connection con) throws SQLException {

	}
	static PreparedStatement deleteCustomerRep(Connection con) throws SQLException {

	}
	static PreparedStatement obtainSalesReport(Connection con) throws SQLException {

	}
	static PreparedStatement getReservationByFlightNumber(Connection con) throws SQLException {

	}
	static PreparedStatement getReservationByCustomerName(Connection con) throws SQLException {

	}
	static PreparedStatement getRevenueByFlight(Connection con) throws SQLException {

	}
	static PreparedStatement getRevenueByAirline(Connection con) throws SQLException {

	}
	static PreparedStatement getRevenueByCustomerName(Connection con) throws SQLException {

	}
	static PreparedStatement getMostRevenueCustomerName(Connection con) throws SQLException {

	}
	static PreparedStatement getFlightWithMostTicketsSold(Connection con) throws SQLException {
		String statement = (SELECT s.flightNumber
				    FROM (SELECT t.flightNumber, COUNT(*) AS count
					  FROM Ticket AS t
					  GROUP BY t.flightNumber
					  HAVING count = (SELECT COUNT(*) AS count
							    FROM docs AS u
							    GROUP BY u.flightNumber
							    LIMIT 1)) AS s)

		PreparedStatement ps = con.prepareStatement(statement);
		
		return ps;
	}
	static PreparedStatement getFlightsByAirport(Connection con, String airportID) throws SQLException {
		String statement = "SELECT * "
				 + "FROM Flight " 
				 + "WHERE DepartureAirport = ? AND ArrivalAirport = ?";
		
		PreparedStatement ps = con.prepareStatement(statement);
		
		ps.setString(1, airportID);
		ps.setString(2, airportID);
		return ps;		    
	}
	
}
