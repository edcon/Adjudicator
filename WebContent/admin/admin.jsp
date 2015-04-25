
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

<%
String username = request.getRemoteUser();

%>

<form method=post action="newUserScript.jsp">
    <input type="text" name="newUsername" placeholder="Username">
    <input type="password" name="newPassword" placeholder="Password">
    <select name="roleDropdown">
			<option value="ADMIN">ADMIN</option>
			<option value="USER">USER</option>
			<option value="PEON">PEON</option>
	</select> <input type="submit" name="Create" value="Create User">
  </form>

    
    
<span>Hello <%= username %>. This is a secure resource (admin)</span>
<p> <a href="http://localhost:8080/Adjudicator/data/data.jsp"> View Data</a> </p>

<br />
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>