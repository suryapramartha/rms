<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="author" content="Mitrais">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="stylesheet" href="../css/bootstrap.min.css">   		
    <script src="../js/bootstrap.min.js"></script>    
<title>Update Competency</title>
</head>
<%
	List<String> list = (List<String>) request.getAttribute("skillSelectedAttribute");
	String id = list.get(0);
	String name = list.get(1);
	String desc = list.get(2);
	String status = list.get(3);
%>
<body>
<div class="container">
	<h1>Update Competency</h1>
	<div class="form-group" >
	<form action="list" method="POST">
		<table border="1" width="75%" >
			<tr>
				<td width="25%">Competency ID</td>
				<td><input type="text" disabled="true" value="<%=id%>" class="form-control"></input></td>
			</tr>
			<tr>
				<td>Competency Name</td>
				<td><input type="text" name="skillNameForm" value="<%=name%>" required="true" class="form-control" maxlength="50"></input></td>
			</tr>
			<tr>
				<td>Competency Description</td>
				<td><input type="text" name="skillDescForm" value="<%=desc%>" required="true" class="form-control" maxlength="200" width="300px" multiple="true" height="5">
					 </input></td>
			</tr>
			<tr>
				<td>Competency Status</td>
				<td><input type="text" disabled="true" value="<%=status%>" class="form-control"></input></td>
			</tr>
		</table>
		<input name="command" value="UPDATE" type="hidden"></input>
		<input name="skillIdForm" value="<%=id%>" type="hidden"></input>
		<br><input type="submit" value="Update" class="btn btn-success">
	</form>
	</div>
	<input type="button" class="btn btn-primary" value="Cancel" onclick="window.location='list';"></input> 
	
	</div>
</body>
</html>