<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="travelReservationSystem.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>

.blurb {
  margin: auto;
  width: 50%;
  padding: 10px;

}

.navbar {
  text-align: center;
}

.flightfinder {
  margin: auto;
  width: 50%;
  padding: 10px;
}

.container {
  display:flex;
}

.items {
  margin: auto;
  width: 50%;
  padding: 10px;
}

.results {
 margin: auto;
 width: 80%;
 padding: 10px;
 border-style: solid;
 border: black;
 border-width: medium;
 overflow-x:auto;
}
 
}

h1 {
  text-align: center;
}

h2 {
  text-align: center;
}

h3 {
  text-align: center;
}

h4 {
  text-align: center;
}

ul {
  background-color: black;
  text-align: center;
  overflow: hidden;
  position: relative;
  display: inline-block;
  width: 100%;
}

li {
  float: center;
  display: inline-block;
  width: 48%;
}

li a {
  text-align: center;
  padding: 14px 16px;
  display: block;
  color: white;
  text-decoration: none;
}

li a:hover {
  background-color: gray;
}

body {
  text-align: center;
  background-color: lightblue;
  font-family: Arial, Helvetica, sans-serif;
}




</style>
</head>
<body>

<!-----Header---->

<h1>
<i>Grouptwo Travel Agency</i>
</h1>
<h4>
The world at your fingertips...
</h4>


<!-----Navigation Bar----->
<div class = "navbar">
<ul>
  <li><a class="active" href="#home">Find A Flight</a></li>
  <li><a href="logout.jsp">Logout</a></li>
</ul>
</div>

<!----Blurb---->
<h4>
How to use the Grouptwo Flightfinder:
</h4>
<div class = "blurb">
  Welcome to Grouptwo Travel Agency, where the world is at your fingertips! To use the Flightfinder, simply fill in the values of where you are, where you want to go, and what type of flight you would like to find. We'll take care of the rest! You can also register an account with us to record any flights you take with us. Please enjoy using the Flightfinder!
</div>


<!-----Flightfinder----->
<h2>
Grouptwo Flightfinder:
</h2>

<form method="post" action="flightQueryResult.jsp">
	<h3>
	Where to and from?
	</h3>
	<input type="text" name="fromTextBox" value="Where from?">
	<input type="text" name="toTextBox" value="Where to?">
	
	<h3>
	What type of flight?
	</h3>
	<select name = "type">
	  <option value="oneway">One-Way</option>
	  <option value="roundtrip">Round-Trip</option>
	  <option value="flexible">Flexible Date/Time</option>
	</select>
	
	<h3>
	When is the trip(yyyy-mm-dd))?
	</h3>
	<input value="startdatePicker" name = "startDate" type="date">
	<input value="enddatePicker" name = "endDate" type="date">
	<input type="submit" value="Query">
</form>


<br>
<br>
<h3>
Results
</h3>
 <table class ="results">
    <tr>
      <th>Flight Number</th>
      <th>From</th>
      <th>To</th>
      <th>Flight Type</th>
      <th>Start Date</th>
      <th>End Date</th>
    </tr>
    <tr>
      <td>Test1</td>
      <td>Test2</td>
      <td>Test3</td>
      <td>Test4</td>
      <td>Test5</td>
      <td>Test6</td>
    </tr>
    <tr>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
    </tr>
    <tr>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
      <td>Test</td>
  </tr>
</table>



</body>
</html>
