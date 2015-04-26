<%@ page language="java" 
  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import= "java.util.ArrayList"%>
<%@ page import= "com.erc.java.adjudicator.dbHelper"%>
<jsp:useBean id="link" scope="application" class = "com.erc.java.adjudicator.dbHelper"/>


<%
String username = request.getRemoteUser();
System.out.println("userlist, checking name: " + username);
dbHelper t = new dbHelper();
String role = t.getRoles(username);
System.out.println("userlist, checking role:  " + role);
ArrayList<String> users = t.getUsers();
%>

<html>

<head>
<link rel="stylesheet" href="css/tablestyle.css"/>
<link rel="stylesheet" href="css/theme.css"/>
</head>

<body>

<div id="holder">
	<ul id="nav">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="data.jsp">Data</a></li>
	<li><a href="userlist.jsp">User List</a></li>
	<li><a href="modifyData.jsp">Data Config</a></li>
	<li><a href="modifyUser.jsp">User Config</a></li>
	<li><a href="addData.jsp">Add Data</a></li>
	<li><a href="index.jsp">Add Users</a></li>
	</ul>
<h1>USERS</h1>
<div class="loggedin">logged in as <%=username%> | <a href="${pageContext.request.contextPath}/logout">logout</a></div>
<div id = "clearancetag"><b>Clearance Level</b><br/><%=role %></div>

<table class="t1">
<tr>
<td><b>User</b></td>
<td><b>Role</b></td>
</tr>
<% for(String user : users){
%>
<tr>
<td><%=user%></td>
<td><%=t.getRoles(user)%></td>
</tr>	
<%	
}
%>
</table>
</div>
</body>
</htmL>