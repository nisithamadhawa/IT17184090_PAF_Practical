package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital
{
	
public Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital", "root", "");     
		
	  //For testing       
			System.out.print("Successfully connected"); 
			}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
		
		return con;
		}
	

public String insertHospital(String  no, String name, String address, String phone, String email, String password) 
	{
		String output = "";  
	
	 try 
	 {  
		 Connection con = connect();  
	 
	  if (con == null) 
	  {
		  return "Error while connecting to the database";     
	  }
	  // create a prepared statement
	  String query = " insert into hospital(`hospitalID`,`hospitalNo`,`hospitalName`,`hospitalAddress`,`hospitalPhone`,`hospitalEmail`,`hospitalPassword`)"  + " values (?, ?, ?, ?, ?,?,?)";  
	  PreparedStatement preparedStmt = con.prepareStatement(query);  
	 
	  // binding values  
	  preparedStmt.setInt(1, 0);
	  preparedStmt.setString(2, no);
	  preparedStmt.setString(3, name);
	  preparedStmt.setString(4, address);
	  preparedStmt.setString(5,phone ); 
	  preparedStmt.setString(6,email ); 
	  preparedStmt.setString(7,password); 
	  
	//execute the statement
	  preparedStmt.execute();
	  con.close(); 
	  
	  String newHospitals = readHospitals();
	  output = "{\"status\":\"success\", \"data\": \"" +   newHospitals + "\"}";  
	 }  
	 catch (Exception e) 
	 {  
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the hospital.\"}"; 
		 System.err.println(e.getMessage()); 
	}  
	  return output; 
}   


public String updateHospital(String ID, String no, String name, String address, String phone, String email, String password) {
	
	String output = "";
	try {
		
		Connection con = connect();
		
		if (con == null) {
			return "Error while connecting to the database for updating.";
		}
		
		// create a prepared statement
		String query = "UPDATE hospital SET hospitalNo=?,hospitalName=?,hospitalAddress=?,hospitalPhone=?,hospitalEmail=?,hospitalPassword=? WHERE hospitalID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		// binding values
		preparedStmt.setString(1, no);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, address);
		preparedStmt.setString(4, phone);
		preparedStmt.setString(5, email);
		preparedStmt.setString(6, password);
		preparedStmt.setInt(7, Integer.parseInt(ID));
		
		
		//execute the statement
		
		preparedStmt.execute();
		con.close();
		
		String newHospitals = readHospitals();
		output = "{\"status\":\"success\", \"data\": \"" +  newHospitals + "\"}"; 
		}
	catch (Exception e)  
	{    
		output = "{\"status\":\"error\", \"data\": \"Error while updating the hosptals.\"}";  
		System.err.println(e.getMessage());  
	}  
	  return output; 
}  
	 


public String readHospitals()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			if (con == null)  
			{
				return "Error while connecting to the database for reading."; 
			}
			
			// Prepare the html table to be displayed  
			output = "<table border=\'1\'><tr><th>Hospital No</th><th>Hospital Name</th><th>Hospital Address</th><th>Hospital PhoneNo</th><th>Hospital Email</th><th>Hospital Password</th><th>Update</th><th>Remove</th></tr>";
			
			  String query = "select * from hospital";
			  Statement stmt = con.createStatement();  
			  ResultSet rs = stmt.executeQuery(query);
			  
			  // iterate through the rows in the result set
			  while (rs.next())
			  {
				  String hospitalID = Integer.toString(rs.getInt("hospitalID")); 
				  String hospitalNo = rs.getString("hospitalNo");
				  String hospitalName = rs.getString("hospitalName");
				  String hospitalAddress =rs.getString("hospitalAddress");  
				  String hospitalPhone = rs.getString("hospitalPhone");  
				  String hospitalEmail =rs.getString("hospitalEmail");  
				  String hospitalPassword = rs.getString("hospitalPassword");  
			  
			   // Add into the html table
				  output += "<tr><td><input id=\'hidHospitalIDUpdate\' name=\'hidHospitalIDUpdate\' type=\'hidden\' value=\'" + hospitalID
							+ "'>" + hospitalNo + "</td>";
				  output += "<td>" + hospitalName + "</td>"; 
				  output += "<td>" + hospitalAddress + "</td>";  
				  output += "<td>" + hospitalPhone+ "</td>";  
				  output += "<td>" + hospitalEmail + "</td>";  
				  output += "<td>" + hospitalPassword+ "</td>";  
				  
				  // buttons
				  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					 		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospitalid='"      
					 		+ hospitalID + "'>" + "</td></tr>";
				  } 
			  
				  con.close();  
				  
				  // Complete the html table 
				  output += "</table>";  
			}
		catch (Exception e)
		{
			output = "Error while reading the hospitals.";
			System.err.println(e.getMessage()); 
		}
		
		return output; 
	}


public String deleteHospital(String hospitalID) 
{
	String output = ""; 
	

try 
{
	Connection con = connect(); 
	if (con == null)
	{ 
		return "Error while connecting to the database for deleting.";
	} 
	
	// create a prepared statement   
	String query = "delete from hospital where hospitalID=?"; 
	
    PreparedStatement preparedStmt = con.prepareStatement(query);  
    
    // binding values  
    preparedStmt.setInt(1, Integer.parseInt(hospitalID));    
    // execute the statement 
    preparedStmt.execute(); 
    con.close(); 
	
    String newHospitals = readHospitals(); 
    output = "{\"status\":\"success\", \"data\": \"" +  newHospitals + "\"}";   
    }
catch (Exception e)
{  
	output = "{\"status\":\"error\", \"data\": \"Error while deleting the hospital.\"}";
	System.err.println(e.getMessage()); 
}  
return output;  
    }
}  

