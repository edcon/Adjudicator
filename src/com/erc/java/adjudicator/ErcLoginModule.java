package com.erc.java.adjudicator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;


public class ErcLoginModule implements LoginModule {
	private static final Logger log = Logger.getLogger( ErcLoginModule.class.getName() );
	
	  private CallbackHandler handler;
	  private Subject subject;
	  private UserPrincipal userPrincipal;
	  private RolePrincipal rolePrincipal;
	  private String login;
	  private List<String> userGroups;
	  
	  
	  @Override
		public void initialize(Subject subject, CallbackHandler callbackHandler,
				Map<String, ?> sharedState, Map<String, ?> options) {

			handler = callbackHandler;
			this.subject = subject;
		}

		@Override
		public boolean login() throws LoginException {

			Callback[] callbacks = new Callback[2];
			callbacks[0] = new NameCallback("login");
			callbacks[1] = new PasswordCallback("password", true);

			try {
				handler.handle(callbacks);
				String name = ((NameCallback) callbacks[0]).getName();
				String password = String.valueOf(((PasswordCallback) callbacks[1])
						.getPassword());

				// Here we validate the credentials against some
				// authentication/authorization provider.
				// It can be a Database, an external LDAP, a Web Service, etc.
				// For this tutorial we are just checking if user is "user123" and
				// password is "pass123"
				
				
				if (name != null &&	password != null) {
					
					Connection connection = null; // manages connection
				    PreparedStatement pt = null; // manages prepared statement
	                log.log(Level.FINE, "OUTSIDE OF TRY: %@\n", name);

				        // connect to database usernames and query database
				        try {
				        	String url = "jdbc:mysql://localhost:3306/login";
				            // establish connection to database
				            Class.forName("com.mysql.jdbc.Driver");
				            Connection con = DriverManager.getConnection(url, "root", "youbleedSupes");

				            // query database
				            pt = con.prepareStatement("SELECT * FROM login.users WHERE username=?");
				            pt.setString(1, name);
				            
				            // process query results
				            ResultSet rs = pt.executeQuery();
				            
				            if(rs.next()){
				                String dbUser; 
				                String dbPass;
				                String dbRole;
				                dbUser = rs.getString("username");
				                dbPass = rs.getString("password");
				                dbRole = rs.getString("role");
				       
				                System.out.println(dbRole);

				            if (dbPass.equals(password)) {
				            	login = name;
								userGroups = new ArrayList<String>();
								userGroups.add(dbRole);	               
				                rs.close();
				                return true;
				            }
				           
		
				            } 
				        }//end try
				        catch (SQLException ex) {
				        	ex.printStackTrace();
				        } //end catch  
				        catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
				    } //end main
				
				

				// If credentials are NOT OK we throw a LoginException
				throw new LoginException("Authentication failed");

			} catch (IOException e) {
				throw new LoginException(e.getMessage());
			} catch (UnsupportedCallbackException e) {
				throw new LoginException(e.getMessage());
			}

		}

		@Override
		public boolean commit() throws LoginException {

			userPrincipal = new UserPrincipal(login);
			subject.getPrincipals().add(userPrincipal);

			if (userGroups != null && userGroups.size() > 0) {
				for (String groupName : userGroups) {
					rolePrincipal = new RolePrincipal(groupName);
					subject.getPrincipals().add(rolePrincipal);
				}
			}

			return true;
		}

		@Override
		public boolean abort() throws LoginException {
			return false;
		}

		@Override
		public boolean logout() throws LoginException {
		  subject.getPrincipals().remove(userPrincipal);
		  subject.getPrincipals().remove(rolePrincipal);
		  return true;
		}
}
