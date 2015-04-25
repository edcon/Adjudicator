
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" 
  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" 
  content="text/html; charset=UTF-8" />
<title>Welcome</title>
<link rel="stylesheet" href="../css/tablestyle.css" type="text/css"/>
</head>
<body>
<%@ page import= "com.erc.java.adjudicator.dbHelper"%>
<jsp:useBean id="link" scope="application" class = "com.erc.java.adjudicator.dbHelper"/>

<%@ page import= "com.erc.java.adjudicator.Room"%>
<jsp:useBean id="link2" scope="application" class = "com.erc.java.adjudicator.Room"/>

<%@ page import= "java.util.ArrayList" %>
<%
String username = request.getRemoteUser();
System.out.println("username is " + username);
dbHelper t = new dbHelper();
String role = t.getRoles(username);
System.out.println("role is " + role);
String room;
String accessCode;
ArrayList<Room> rooms = t.getData(role);
%>

<div id="holder">
<h1>DATA CENTER
<div class="logout"><a href="${pageContext.request.contextPath}/logout">Logout</a></div></h1>
<div id = "clearancetag"><b>Clearance Level</b><br/><%=role %></div>

<table class="t1">
<tr>
<td><b>Room</b></td>
<td><b>Access Code</b></td>
<td><b>Role</b></td>
</tr>
<% for(Room r : rooms){
%>
<tr>
<td><%=r.getName()%></td>
<td><%=r.getAccess()%></td>
<td><%=r.getRole()%></td>
</tr>	
<%	
}
%>
</table>
</div>
</body>
</html>