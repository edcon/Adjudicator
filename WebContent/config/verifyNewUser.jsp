<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Data Result</title>
<link rel="stylesheet" href="../css/theme.css"/>
</head>
<body>

<div id="messagebox">
<%@ page import= "com.erc.java.adjudicator.dbHelper"%>
<jsp:useBean id="link" scope="application" class = "com.erc.java.adjudicator.dbHelper"/>
	<% String executor = request.getRemoteUser();%>
	<% String username = request.getParameter("Username");%>
    <% String password = request.getParameter("Password");%>
    <% String role = request.getParameter("Role"); %>

 <% 
 System.out.println("attempting to add new user: " + username);
 dbHelper t=new dbHelper();
 int changedRole = t.addUser(executor, username, password, role);
 if(changedRole==1){ %> 
		<img src="../img/success.png"/><br/> <span>0x01: SUCCESS! You have added user <%=username %>.</span>	 
 <%}else if(changedRole==2){ %>
 	 	<img src="../img/error.png"/><br/><span>Error 0x02: Usernames must consist of only letters and must be 5-15 characters in length.</span>
 <%}else if(changedRole==3){
	 %>	<img src="../img/error.png"/><br/><span>Error 0x03: Passwords must consist of only letters and numbers and must be 5-16 characters in length.</span>
<%}else if(changedRole==4){%>
	<img src="../img/error.png"/><br/>
	<span>Error 0x04: You cannot assign <%=username%> a role of <%=role%>, as this role is above your access level.</span>
<%}else{%>	<img src="../img/error.png"/><br/><span>Error 0x05: This user already exists.<br/>If you are trying to modify the user, click "Return to User Config" below.</span>
<%}%>
<br/>
<ul>
<li><a href="../userlist.jsp">Return to User List</a></li>
<li><a href="../modifyUser.jsp">Return to User Config</a></li>
</ul>
 </div>
</body>
</html>