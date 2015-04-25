<html>
<head>
<style>
body{
background: #F7F7F7;
}
#container{
text-align: center;
margin: 0px auto;
width: 50%;
}
}</style>
</head>
<%@ page import= "com.erc.java.adjudicator.dbHelper"%>
<jsp:useBean id="link" scope="application" class = "com.erc.java.adjudicator.dbHelper"/>

<%
String username = request.getRemoteUser();
dbHelper t = new dbHelper();
String role = t.getRoles(username);
%>
<body>
<div id="container">
<h1>403 : Access Forbidden</h1>
<img src="../img/stopsign.png"/>
<h3>Sorry, <%=username %>, but this is a restricted area.<br/>
	Your clearance level of <%=role %> is not adequate.</h3>
</body>
</div>

</html>
