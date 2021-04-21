package model;
import java.sql.*;

public class Product { //A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, user name, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useTimezone=true&serverTimezone=UTC", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	 
	
	public String insertProduct(String name, String description, String location)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }

	 
	 // create a prepared statement
	 String query = " insert into products(`productID`,`productName`,`productDescription`,`productLocation`)"
	 + " values (?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);

	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, description);
	 preparedStmt.setString(4, location);
	
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readProducts()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }

	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Product Name</th><th>Product Description</th>" +
	 "<th>Product location</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from products";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String productID = Integer.toString(rs.getInt("productID"));
	 String productName = rs.getString("productName");
	 String productDescription = rs.getString("productDescription");
	 String productLocation = rs.getString("productLocation");


	 // Add into the html table
	 output += "<tr><td>" + productName + "</td>";
	 output += "<td>" + productDescription + "</td>";
	 output += "<td>" + productLocation + "</td>";


	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='products.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='productID' type='hidden' value='" + productID
	 + "'>" + "</form></td></tr>";
	 }


	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the products.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String updateProduct(String ID,String name, String description, String location)
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 
		 // create a prepared statement
		 String query = "UPDATE products SET productName=?,productDescription=?,productLocation=?WHERE productID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 // binding values
		 preparedStmt.setString(1, name);
		 preparedStmt.setString(2, description);
		 preparedStmt.setString(3, location);
		 preparedStmt.setInt(4, Integer.parseInt(ID));

		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the product.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }

	
		public String deleteProduct(String productID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }

		 
		 // create a prepared statement
		 String query = "delete from products where productID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);

		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(productID));

		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the product.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		} 