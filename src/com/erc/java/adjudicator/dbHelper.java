package com.erc.java.adjudicator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

public class dbHelper {
	
	public int modifyData(String executor, String room, String newCode, String newRole){
		//note that we have already verified roles via .getRoles() before calling this
		try {
			newCode.trim();
        	String url = "jdbc:mysql://localhost:3306/login";
            // establish connection to database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "LizardTree91");
            String executorRole = getRoles(executor);
    		
    		//if code is not blank and is not 5 digits...
    		if(!newCode.equals("") && !newCode.matches("[0-9]{5}")){
    			//access code must be exactly 5 digits
    			con.close();
    			return 0x02;			
    		}
    		else if(!dominates(executorRole,newRole)){
    			con.close();
    			return 0x03;
    		}
    		else{
    			PreparedStatement pt = 
    					newCode.equals("")?
    					con.prepareStatement("UPDATE login.data SET role=? WHERE room=?"):
    					con.prepareStatement("UPDATE login.data SET accesscode=?,role=? WHERE room=?");
    			if(!newCode.equals("")){
	    			pt.setString(1, newCode);
	    			pt.setString(2, newRole);
	    			pt.setString(3, room);
	    			pt.executeUpdate();
    			}
    			else{
    				pt.setString(1, newRole);
    				pt.setString(2, room);
    				pt.executeUpdate();
    			}
    			con.close();
    			return 0x01;
    		}
    		
    
        }//end try
        catch (SQLException ex) {
        	ex.printStackTrace();
        } //end catch  
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0x04;
		
	}
	
	public int changeRole(String executor, String username, String newRole){
		try {
        	String url = "jdbc:mysql://localhost:3306/login";
            // establish connection to database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "LizardTree91");
    		String executorRole = getRoles(executor);
    		String targetRole = getRoles(username);
    		
    		if(executor.equals(username)){
    			//(error) code 2: no modifying your own access
    			con.close();
    			return 0x02;
    		}
    		
    		else if(targetRole.equals(newRole)){
    			//new role = current role
    			con.close();
    			return 0x04;
    		}
    		else if(!dominates(executorRole, targetRole)){
    			//(error) code 3: user you are trying to modify has higher access than you
    			con.close();
    			return 0x03;
    		}
    		else if (dominates(executorRole, targetRole) && dominates(executorRole, newRole)){
                // change user role in DB
                PreparedStatement pt = con.prepareStatement("UPDATE login.users SET role='"+newRole+"' WHERE username='"+username+"'");
          
                // execute statement
                pt.executeUpdate();
                //code 1: success
                con.close();
                return 0x01;
    		}
    		else{
    			//not even sure
    			con.close();
    			return 0x05;
    		}
    
        }//end try
        catch (SQLException ex) {
        	ex.printStackTrace();
        } //end catch  
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0x06;
		
	}
	
	private boolean dominates(String executorRole, String targetRole){
		return assignRoleNumber(executorRole)>=assignRoleNumber(targetRole);		
	}
	
	private int assignRoleNumber(String role){
		int roleNumber = 0;
		
		switch(role){
		
		case "PEON":
			roleNumber=1;
			break;
		case "USER":
			roleNumber=2;
			break;
		case "ADMIN":
			roleNumber=3;
			break;
		case "GODMODE":
			roleNumber=4;
			break;
		}
		
		return roleNumber;
	}
	
	public ArrayList<String> getUsers(){
		ArrayList<String> users = new ArrayList<String>();
		try {
        	String url = "jdbc:mysql://localhost:3306/login";
            // establish connection to database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "LizardTree91");

            // query database
            PreparedStatement pt = con.prepareStatement("SELECT * FROM login.users");
            
            // process query results
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
         
            	String user = rs.getString("username");
            	users.add(user);
                System.out.println(user);
                
            }
            
            rs.close();
            con.close();
            
        }//end try
        catch (SQLException ex) {
        	ex.printStackTrace();
        } //end catch  
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return users;
	}
	
	public String getRoles(String username){
		
	    PreparedStatement pt = null; // manages prepared statement

	        // connect to database usernames and query database
	        try {
	        	String url = "jdbc:mysql://localhost:3306/login";
	            // establish connection to database
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection(url, "root", "LizardTree91");

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
	                con.close();
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
	            Connection con = DriverManager.getConnection(url, "root", "LizardTree91");

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
	            con.close();

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
	
	public int addData(String executor, String room, String accessCode, String role){
		try {
        	String url = "jdbc:mysql://localhost:3306/login";
            // establish connection to database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "LizardTree91");
            String executorRole = getRoles(executor);
            
            //room consists of only letters and spaces, less than 30 chars, and is not blank
            if(room.matches("[a-zA-Z ]{1,30}") && !room.equals("")){
            	//access code consists of 5 numbers exactly
	            if(accessCode.matches("[0-9]{5}")){
		            if(dominates(executorRole, role)){
		            	PreparedStatement pt = con.prepareStatement("INSERT INTO login.data (room,accesscode,role) VALUES (?,?,?)");
			            pt.setString(1, room);
			            pt.setString(2, accessCode);
			            pt.setString(3, role);
			            pt.executeUpdate();
			            con.close();
			            return 0x01;
		            }
		            else{
		            	return 0x04;
		            }
	            }
	            else{
	            	return 0x03;
	            }
            }
            else{
            	return 0x02;
            }
            
		}

        catch (SQLException ex) {
        	ex.printStackTrace();
        } //end catch  
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0x05;
	}
	
	public int addUser(String executor, String username, String password, String role){
		try {
        	String url = "jdbc:mysql://localhost:3306/login";
            // establish connection to database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, "root", "LizardTree91");
            String executorRole = getRoles(executor);
            username = username.toLowerCase();
            
            //usernames must be 5-15 characters, letters only
            if(username.matches("[a-zA-Z]{5,15}")){
            	//passwords must be 5-16 characters, letters and numbers only
	            if(password.matches("[A-Za-z0-9]{5,16}")){
		            if(dominates(executorRole, role)){
		            	PreparedStatement pt = con.prepareStatement("INSERT INTO login.users (username,password,role) VALUES (?,?,?)");
			            pt.setString(1, username);
			            pt.setString(2, password);
			            pt.setString(3, role);
			            pt.executeUpdate();
			            con.close();
			            return 0x01;
		            }
		            else{
		            	return 0x04;
		            }
	            }
	            else{
	            	return 0x03;
	            }
            }
            else{
            	return 0x02;
            }
            
		}

        catch (SQLException ex) {
        	ex.printStackTrace();
        } //end catch  
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0x05;
	}

}

