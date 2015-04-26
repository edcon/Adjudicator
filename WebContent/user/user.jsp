<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" 
  content="text/html; charset=UTF-8" />
<title>Welcome</title>
</head>
<body>
<%
String username = request.getRemoteUser();
%>
<span>Hello <%= username %>. This is a secure resource (Peon)</span>
<br />
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>