<%@ page language="java" 
  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adjudicator</title>
<link rel="stylesheet" href="css/theme.css"/>
</head>
<%
String username = request.getRemoteUser();
%>
<body>
<div id="holder">
	<ul id="nav">
	<li><a href="index.jsp">Home</a></li>
	<li><a href="data.jsp">Data</a></li>
	<li><a href="userlist.jsp">User List</a></li>
	<li><a href="modifyData.jsp">Data Config</a></li>
	<li><a href="modifyUser.jsp">User Config</a></li>
	<li><a href="addData.jsp">Add Data</a></li>
	<li><a href="config/addUser.jsp">Add Users</a></li>
	</ul>
	
	<h1>Welcome</h1>
	
	<div class="loggedin">logged in as <%=username%> | <a href="${pageContext.request.contextPath}/logout">logout</a></div>

</div>
 
  
</body>
</html>