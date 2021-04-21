package model;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Researcher { //A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useTimezone=true&serverTimezone=UTC", "root", "");
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();
		 }
	 return con;
	 }
	public String insertResearcher(String name, String email, String number, String address, String productType, String reDate)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
		 return "Error while connecting to the database for inserting.";
	 }
	 // create a prepared statement
	 String query = " insert into researcher(`researcherID`,`researcherName`,`researcherEmail`,`researcherNumber`,`researcherAddress`,`researcherProductType`,`researcherReDate`)" + " values (?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, email);
	 preparedStmt.setString(4, number);
	 preparedStmt.setString(5, address);
	 preparedStmt.setString(6, productType);
	 preparedStmt.setString(7, reDate);
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the researcher.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	//
	//
	// read
	public String readResearchers()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Researcher Name</th><th>Researcher Email</th>" +
	 "<th>Researcher Number</th>" +
	 "<th>Researcher Address</th>" +
	 "<th>Researcher Product Type</th>" +
	 "<th>Researcher Registed Date</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from researcher";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String researcherID = Integer.toString(rs.getInt("researcherID"));
		 String researcherName = rs.getString("researcherName");
		 String researcherEmail = rs.getString("researcherEmail");
		 String researcherNumber = rs.getString("researcherNumber");
		 String researcherAddress = rs.getString("researcherAddress");
		 String researcherProductType = rs.getString("researcherProductType");
		 String researcherReDate = rs.getString("researcherReDate");
	 // Add into the html table
		 output += "<tr><td>" + researcherName + "</td>";
		 output += "<td>" + researcherEmail + "</td>";
		 output += "<td>" + researcherNumber + "</td>";
		 output += "<td>" + researcherAddress + "</td>";
		 output += "<td>" + researcherProductType + "</td>";
		 output += "<td>" + researcherReDate + "</td>";
	 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='researchers.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
				 + "<input name='researcherID' type='hidden' value='" + researcherID
				 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the researcher.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	// update
	public String updateResearcher(String ID, String name, String email, String number, String address, String productType, String reDate)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE researcher SET researcherName=?,researcherEmail=?,researcherNumber=?,researcherAddress=?,researcherProductType=?,researcherReDate=?WHERE researcherID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, name);
		 preparedStmt.setString(2, email);
		 preparedStmt.setString(3, number);
		 preparedStmt.setString(4, address);
		 preparedStmt.setString(5, productType);
		 preparedStmt.setString(6, reDate);
		 preparedStmt.setInt(7, Integer.parseInt(ID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the researcher.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteResearcher(String researcherID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from researcher where researcherID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(researcherID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the researcher.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		} 