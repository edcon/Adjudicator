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
	
		
		String query;
		
        switch (role) {
            case "PEON":  	query = "SELECT * FROM login.data WHERE role = 'PEON'";
                     		break;
            case "USER":  	query = "SELECT * FROM login.data WHERE role= 'PEON' or role ='USER'";
            				break;
            case "ADMIN":  	query = "SELECT * FROM login.data WHERE role= 'PEON' or role ='USER' or role ='ADMIN'";
                     		break;            
            default: 		query = "SELECT * FROM login.data";
                     		break;
        }
		Connection connection = null; // manages connection
	    PreparedStatement pt = null; // manages prepared statement

	    
	    
	        // connect to database usernames and query database
	        try {
	        	String url = "jdbc:mysql://localhost:3306/login";
	            // establish connection to database
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection(url, "root", "youbleedSupes");

	            // query database
	            pt = con.prepareStatement(query);
	            
	            
	            // process query results
	            ResultSet rs = pt.executeQuery();
	            
	            while(rs.next()){
	         
	                String dbAccess;
	                String dbRoom;
	                String dbRole;
	                dbRoom = rs.getString("room");
	                dbAccess = rs.getString("accesscode");
	                dbRole = rs.getString("role");
	                
	                returnData.add(new Room (dbRoom, dbAccess,dbRole));

	                
	           } 
		        rs.close();

	        }//end try
	        catch (SQLException ex) {
	        	ex.printStackTrace();
	        } //end catch  
	        catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return returnData;
	}

	public boolean insertUser(String username, String password, String role){
		
		if(nameExists(username)){
			return false;
		}
		Connection connection = null; // manages connection
	    PreparedStatement pt = null; // manages prepared statement

	    
	    
	        // connect to database 
	        try {
	        	String url = "jdbc:mysql://localhost:3306/login";
	            // establish connection to database
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection(url, "root", "youbleedSupes");

	            // query database
	            pt = con.prepareStatement("INSERT INTO login.users(username, password, role) VALUES (?,?,?);");
	            pt.setString(1, username);
	            pt.setString(1, password);
	            pt.setString(1, role);
	            
	        }//end try
	        catch (SQLException ex) {
	        	ex.printStackTrace();
	        } //end catch  
	        catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}
	
	public boolean nameExists(String username){
		
		Connection connection = null; // manages connection
	    PreparedStatement pt = null; // manages prepared statement

	    
	    
	        // connect to database 
	        try {
	        	String url = "jdbc:mysql://localhost:3306/login";
		Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, "root", "youbleedSupes");

        // query database
        pt = con.prepareStatement("SELECT username FROM login.users WHERE username=?");
        pt.setString(1, username);
        // process query results
        ResultSet rs = pt.executeQuery();
        
        if(rs.next()){
        	rs.close();
            return true;
       } 
        rs.close();
       
    }//end try
    catch (SQLException ex) {
    	ex.printStackTrace();
    } //end catch  
    catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
		return false;
	}
	
}

