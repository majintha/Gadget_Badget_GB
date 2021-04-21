package model;

import java.sql.*;

public class buyer 
{
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
	
	public String insertBuyer(String name, String contactNo, String email) 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			
			// create a prepared statement
			String query = " insert into buyers(`buyerID`,`buyerName`,`buyerContactNo`,`buyerEmail`)" + " values (?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2,name);
			preparedStmt.setString(3,contactNo);
			preparedStmt.setString(4,email);
			 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			 output = "Error while inserting the buyer."; 
			 System.err.println(e.getMessage()); 
		} 
		return output; 

	}
	
	public String readBuyers() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Buyer Name</th>" +
					"<th>Buyer Contact Number</th>" + 
					"<th>Buyer Email</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from buyers"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String buyerID = Integer.toString(rs.getInt("buyerID"));  
				String buyerName = rs.getString("buyerName");  
				String buyerContactNo = rs.getString("buyerContactNo");
				String buyerEmail = rs.getString("buyerEmail");
				
				// Add into the html table
				output += "<tr><td>" + buyerName + "</td>"; 
				output += "<td>" + buyerContactNo + "</td>"; 
				output += "<td>" + buyerEmail + "</td>"; 
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
								+ "<td><form method='post' action='buyers.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
										+ "<input name='buyerID' type='hidden' value='" + buyerID 
										+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			
			// Complete the html table
			output += "</table>"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the buyres."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 

	}
	
	public String updateBuyer(String ID, String name, String contactNo, String email)
    {
         String output = "";
         try
         {
        	 Connection con = connect();
        	 if (con == null)
        	 {return "Error while connecting to the database for updating."; }
         
        	 // create a prepared statement
        	 String query = "UPDATE buyers SET buyerName=?,buyerContactNo=?,buyerEmail=?,WHERE buyerID=?";
        	 PreparedStatement preparedStmt = con.prepareStatement(query);
         
        	 // binding values
        	 preparedStmt.setString(1, name);
        	 preparedStmt.setString(2, contactNo);
        	 preparedStmt.setString(3, email);
        	 preparedStmt.setInt(4, Integer.parseInt(ID));
         
        	 // execute the statement
        	 preparedStmt.execute();
        	 con.close();
        	 output = "Updated successfully";
         	}
         catch (Exception e)
         {
        	 output = "Error while updating the buyer.";
        	 System.err.println(e.getMessage());
         }
         return output;
      }
	
	public String deleteBuyer(String buyerID) 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			
			// create a prepared statement
			String query = "delete from buyers where buyerID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(buyerID)); 
			
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the buyer."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
}




