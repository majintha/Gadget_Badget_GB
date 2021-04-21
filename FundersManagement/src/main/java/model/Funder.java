package model;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Funder {
	
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	
	public String insertFunder(String code, String name, String email, String donation, String desc)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into funders(`funderID`,`funderCode`,`funderName`,`funderEmail`,`funderDonation`,`funderDesc`)"
			+ " values (?, ?, ?, ?, ?, ?)";
			
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, email);
			preparedStmt.setDouble(5, Double.parseDouble(donation));
			preparedStmt.setString(6, desc);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the funder.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String readFunders()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Funder Code</th><th>Funder Name</th><th>Funder Email</th>" +
					"<th>Donations</th>" +
					"<th>Description</th>" +
					"<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from funders";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String funderID = Integer.toString(rs.getInt("funderID"));
				String funderCode = rs.getString("funderCode");
				String funderName = rs.getString("funderName");
				String funderEmail = rs.getString("funderEmail");
				String funderDonation = Double.toString(rs.getDouble("funderDonation"));
				String funderDesc = rs.getString("funderDesc");
				
				// Add into the html table
				output += "<tr><td>" + funderCode + "</td>";
				output += "<td>" + funderName + "</td>";
				output += "<td>" + funderEmail + "</td>";
				output += "<td>" + funderDonation + "</td>";
				output += "<td>" + funderDesc + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='funders.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='funderID' type='hidden' value='" + funderID
						+ "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		
		catch (Exception e)
		{
				output = "Error while reading the funders.";
				System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateFunder(String ID, String code, String name, String email, String donation, String desc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE funders SET funderCode=?,funderName=?,funderEmail=?,funderDonation=?,funderDesc=? WHERE funderID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setDouble(4, Double.parseDouble(donation));
			preparedStmt.setString(5, desc);
			preparedStmt.setInt(6, Integer.parseInt(ID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the funder.";
			System.err.println(e.getMessage());
		}
		return output;
		}
	public String deleteFunder(String funderID)
	{
		String output = "";
		try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from funders where funderID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(funderID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the funder.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		
}
