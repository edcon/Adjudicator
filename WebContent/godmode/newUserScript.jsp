<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%@ page import= "com.erc.java.adjudicator.dbHelper"%>
<jsp:useBean id="link" scope="application" class = "com.erc.java.adjudicator.dbHelper"/>

    <% String newUsername = request.getParameter("newUsername");%>
    <% String newPassword = request.getParameter("newPassword"); %>
    <% String newRole = request.getParameter("roleDropdown"); %>


 <% 
 System.out.println("CREATING USER:");
 System.out.println(newUsername);
 System.out.println(newPassword);
 System.out.println(newRole);
 dbHelper t=new dbHelper();
 boolean insertedUser = t.insertUser(newUsername,newPassword,newRole);
 if(!insertedUser){ %> 
	 <span>Username already in use!</span>
	  <p> <a href="http://localhost:8080/Adjudicator/admin/admin.jsp"> Data </a> </p>
	 
 <%}else{ %>
 	 <span>User successfully added!</span>
  <p> <a href="http://localhost:8080/Adjudicator/admin/admin.jsp"> Data </a> </p>
 <%} %>
  
</body>
</html>