<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Data Result</title>
<link rel="stylesheet" href="css/theme.css"/>
</head>
<body>

<div id="messagebox">
<%@ page import= "com.erc.java.adjudicator.dbHelper"%>
<jsp:useBean id="link" scope="application" class = "com.erc.java.adjudicator.dbHelper"/>
	<% String executor = request.getRemoteUser();%>
	<% String room = request.getParameter("Room");%>
    <% String newCode = request.getParameter("accessCode");%>
    <% String newRole = request.getParameter("Role"); %>



 <% 
 System.out.println("attempting to change data for " + room);
 dbHelper t=new dbHelper();
 int changedRole = t.modifyData(executor, room, newCode, newRole);
 if(changedRole==1){ %> 
 	<img src="img/success.png"/><br/>
	 <span>0x01: SUCCESS! You updated the information for the <%=room%>.</span>	 
 <%}else if(changedRole==2){ %>
 	 <img src="img/error.png"/><br/><span>Error 0x02: Access codes must be exactly 5 digits (0-9).</span>
 <%}else if(changedRole==3){
	 %><img src="img/error.png"/><br/><span>Error 0x03: Sorry, but you cannot elevate data to the <%=newRole%> level. This is higher than your own.</span>
<%}else{%>
	<img src="img/error.png"/><br/><span>Error 0x04: Database Connection Error. This isn't your fault.</span>
<%}%>

<br/>
<ul>
<li><a href="data.jsp">Return to Data</a></li>
<li><a href="modifyData.jsp">Return to Data Config</a></li>
</ul>
  </div>
</body>
</html>