
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
  
<span>Hello <%= username %>. This is a secure resource (admin)</span>
<p> <a href="http://localhost:8080/Adjudicator/data.jsp"> View Data</a> </p>

<br />
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>