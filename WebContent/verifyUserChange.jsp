<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Permissions Result</title>
<link href="css/theme.css" rel="stylesheet"/>
</head>
<body>


<%@ page import= "com.erc.java.adjudicator.dbHelper"%>
<jsp:useBean id="link" scope="application" class = "com.erc.java.adjudicator.dbHelper"/>
	<% String executor = request.getRemoteUser();%>
    <% String userToUpdate = request.getParameter("Username");%>
    <% String newRole = request.getParameter("Role"); %>

<div id="messagebox">
 <% 
 System.out.println("attempting to change role of " + userToUpdate + " to " + newRole);
 dbHelper t=new dbHelper();
 int changedRole = t.changeRole(executor, userToUpdate, newRole);
 if(changedRole==1){ %> 
 	 <img src="img/success.png"/><br/>
	 <span>0x01: SUCCESS! You modified the role of <%=userToUpdate%> to <%=newRole%>.</span>	 
 <%}else if(changedRole==2){ %>
 	<img src="img/error.png"/><br/>
 	 <span>Error 0x02: Nice try, but you cannot change your own role.</span>
 <%}else if(changedRole==3){
	 %><img src="img/error.png"/><br/>
	 <span>Error 0x03: Sorry, but you cannot modify the access level of <%=userToUpdate%>.<br/>
	 		This user has a higher access level than you.</span>
<%}else if(changedRole==4){%>
<img src="img/error.png"/><br/>
	<span>Error 0x04: You didn't change anything.</span>
<%}else if (changedRole==5){
	 %>
	 <img src="img/error.png"/><br/><span>Fatal Error 0x05: You really messed something up.</span>
<%}else{%>
<img src="img/error.png"/><br/><span>Error 0x06: Database Connection Error. This isn't your fault.</span>
<%}%>
<br/>
<ul>
<li><a href="userlist.jsp">Return to User List</a></li>
<li><a href="modifyUser.jsp">Return to User Config</a></li>
</ul>
</div>
  
</body>
</html>