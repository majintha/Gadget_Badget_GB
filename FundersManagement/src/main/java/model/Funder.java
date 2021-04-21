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
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useTimezone=true&serverTimezone=UTC", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	
	public String insertFunder(String name, String email, String tel, String gender, String donation, String desc)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into funders(`funderID`,`funderName`,`funderEmail`, `funderTel`, `funderGender`,`funderDonation`,`funderDesc`)"
			+ " values (?, ?, ?, ?, ?, ?, ?)";
			
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, tel);
			preparedStmt.setString(5, gender);
			preparedStmt.setDouble(6, Double.parseDouble(donation));
			preparedStmt.setString(7, desc);
			
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
			output = "<table border='1'><tr><th>Funder Name</th><th>Funder Email</th><th>Funder Telephone No</th><th>Funder Gender</th>" +
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
				String funderName = rs.getString("funderName");
				String funderEmail = rs.getString("funderEmail");
				String funderTel = rs.getString("funderTel");
				String funderGender = rs.getString("funderGender");
				String funderDonation = Double.toString(rs.getDouble("funderDonation"));
				String funderDesc = rs.getString("funderDesc");
				
				// Add into the html table
				output += "<tr><td>" + funderName + "</td>";
				output += "<td>" + funderEmail + "</td>";
				output += "<td>" + funderTel + "</td>";
				output += "<td>" + funderGender + "</td>";
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
	public String updateFunder(String ID, String name, String email, String tel, String gender, String donation, String desc)
	{
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE funders SET funderName=?,funderEmail=?,funderTel=?,funderGender=?,funderDonation=?,funderDesc=? WHERE funderID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, tel);
			preparedStmt.setString(4, gender);
			preparedStmt.setDouble(5, Double.parseDouble(donation));
			preparedStmt.setString(6, desc);
			preparedStmt.setInt(7, Integer.parseInt(ID));
			
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
