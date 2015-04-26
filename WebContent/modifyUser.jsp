<!-- Access Configuration (changing roles) -->
<%@ page import= "java.util.ArrayList"%>
<%@ page import= "com.erc.java.adjudicator.dbHelper"%>
<jsp:useBean id="link" scope="application" class = "com.erc.java.adjudicator.dbHelper"/>
	<% 	dbHelper t = new dbHelper();
		ArrayList<String> users = t.getUsers();
		String currentUser = request.getRemoteUser();%>
<html>
<head>

<link rel="stylesheet" href="css/usermodify.css"/>
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
	<li><a href="config/addUser.jsp">Add Users</a></li>
	</ul>
<h1>Modify User Permissions</h1>
<div class="loggedin">logged in as <%=currentUser%> | <a href="${pageContext.request.contextPath}/logout">logout</a></div>

<form method=post action="verifyUserChange.jsp">
	<table>
	<tr>
		<td>Target User</td>
		<td><select name="Username">
			<%for(String user: users){ %>
			<option value="<%=user%>"><%=user%></option>
			<%}%>
			</select>
		</td>
	</tr>
	<tr>
		<td>New Role</td>
    <td>
    <select name="Role">
			<option value="GODMODE" >GODMODE</option>
			<option value="ADMIN">ADMIN</option>
			<option value="USER">USER</option>
			<option value="PEON">PEON</option>
	</select> </td></tr>
	<tr><td><input type="submit" name="Modify" value="Modify"></td></tr>
	</table>
  </form>
</div>
</body>
</html>