<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
			<option value="GODMODE" >GODMODE</option>
			<option value="ADMIN">ADMIN</option>
			<option value="USER">USER</option>
			<option value="PEON">PEON</option>
	</select> <input type="submit" name="Create" value="Create User">
  </form>

    
<span>Hello <%= username %>. This is a secure resource (Godmode)</span>
<br />
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>