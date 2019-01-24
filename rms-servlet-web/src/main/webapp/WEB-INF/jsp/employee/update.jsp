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
<title>Update Employee</title>
</head>
<%
	List<String> list = (List<String>) request.getAttribute("employeeSelectedAttribute");
	String id = list.get(0);
	String name = list.get(1);
	String status = list.get(2);
	String age = list.get(3);
	String address = list.get(4);
%>
<body>
<div class="container">
	<h1>Update Employee</h1>
	<div class="form-group">
	<form action="list" method="POST">
		<table border="1">
			<tr>
				<td>Employee ID</td>
				<td><input type="text" disabled="disabled" value="<%=id%>" class="form-control"></input></td>
			</tr>
			<tr>
				<td>Employee Name</td>
				<td><input type="text" name="emplNameForm" value="<%=name%>" required="required" class="form-control"></input></td>
			</tr>
			<tr>
				<td>Employee Status</td>
				<td><input type="text" disabled="disabled" value="<%=status%>" class="form-control"></input></td>
			</tr>
			<tr>
				<td>Employee Age</td>
				<td><input type="number" name="emplAgeForm" min="0" value="<%=age%>" required="required" class="form-control"></input></td>
			</tr>
			<tr>
				<td>Employee Address</td>
				<td><input type="text" name="emplAddressForm" value="<%=address%>" required="required" class="form-control" ></input></td>
			</tr>
		</table>
		<input name="command" value="UPDATE" type="hidden"></input>
		<input name="emplIdForm" value="<%=id%>" type="hidden"></input>
		<br><input type="submit" value="Update" class="btn btn-success">
	</form>
	</div>
	<input type="button" class="btn btn-primary" value="Cancel" onclick="window.location='list';"></input> 
	
	</div>
</body>
</html>