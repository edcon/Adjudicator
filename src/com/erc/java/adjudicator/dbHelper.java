package com.erc.java.adjudicator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

public class dbHelper {
	public String getRoles(String username){
		
		
		Connection connection = null; // manages connection
	    PreparedStatement pt = null; // manages prepared statement

	        // connect to database usernames and query database
	        try {
	        	String url = "jdbc:mysql://localhost:3306/login";
	            // establish connection to database
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection(url, "root", "youbleedSupes");

	            // query database
	            pt = con.prepareStatement("SELECT role FROM login.users WHERE username=?");
	            pt.setString(1, username);
	            
	            // process query results
	            ResultSet rs = pt.executeQuery();
	            
	            if(rs.next()){
	         
	                String dbRole;
	                dbRole = rs.getString("role");
	       
	                System.out.println(dbRole);

	                   
	                rs.close();
	                return dbRole;
	           } 
	            
	        }//end try
	        catch (SQLException ex) {
	        	ex.printStackTrace();
	        } //end catch  
	        catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;
	    } //end main
	

	public ArrayList<Room> getData(String role){
		ArrayList<Room> returnData = new ArrayList<Room>();
	
		Connection connection = null; // manages connection
	    PreparedStatement pt = null; // manages prepared statement

	        // connect to database usernames and query database
	        try {
	        	String url = "jdbc:mysql://localhost:3306/login";
	            // establish connection to database
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection(url, "root", "youbleedSupes");

	            // query database
	            pt = con.prepareStatement("SELECT * FROM login.data WHERE role=?");
	            pt.setString(1, role);
	            
	            // process query results
	            ResultSet rs = pt.executeQuery();
	            
	            if(rs.next()){
	         
	                String access;
	                String room;
	                room = rs.getString("room");
	                access = rs.getString("accesscode");
	                
	                returnData.add(new Room (room, access));

	                   
	                rs.close();
	                return returnData;
	           } 
	            
	        }//end try
	        catch (SQLException ex) {
	        	ex.printStackTrace();
	        } //end catch  
	        catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;
		
	}

}

