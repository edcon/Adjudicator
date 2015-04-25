
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
</head>
<body>
<%@ page import= "com.erc.java.adjudicator.dbHelper"%>
<jsp:useBean id="link" scope="application" class = "com.erc.java.adjudicator.dbHelper"/>
<%@ page import= "com.erc.java.adjudicator.Room"%>
<jsp:useBean id="link2" scope="application" class = "com.erc.java.adjudicator.Room"/>
<%@ page import= "java.util.ArrayList" %>
<%
String username = request.getRemoteUser();
dbHelper t=new dbHelper();
String role = t.getRoles(username);
String room;
String accessCode;
ArrayList<Room> rooms = t.getData(role);
%>

<span>Hello <%= username %>. </span>
<span>Your role is: <%= role %></span>
<br />

<table>
<% for(Room r : rooms){
%>
<tr>
<td><%r.getName();%></td>
<td><%r.getAccess(); %></td>
</tr>	
<%	
}
%>
</table>

<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>