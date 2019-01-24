<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Employee List</title>	
	<meta name="author" content="Mitrais">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="stylesheet" href="../css/bootstrap.min.css">   		
    <script src="../js/bootstrap.min.js"></script>     
</head>
<body>
<div class="container">
	<h1 align="left">Employee List</h1>
	<table border="1" class="table table-striped">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Status</th>
			<th>Age</th>
			<th>Address</th>
			<th>Action</th>
		</tr>
		<c:forEach var="x" items="${employeeAttribute}">
			<c:url var="tempUpdate" value="update">
				<c:param name="command" value="EDIT"></c:param>
				<c:param name="employeeId" value="${x.emplId}"></c:param>
			</c:url>
			<c:url var="tempDelete" value="list">
				<c:param name="command" value="DELETE"></c:param>
				<c:param name="employeeId" value="${x.emplId}"></c:param>
			</c:url>
			<c:url var="tempCompetency" value="viewCompetency">
				<c:param name="command" value="VIEW"></c:param>
				<c:param name="employeeId" value="${x.emplId}"></c:param>
			</c:url>
			<tr>
				<td>${x.emplId}</td>
				<td>${x.emplName}</td>
				<td>${x.emplStatus}</td>
				<td>${x.emplAge}</td>
				<td>${x.emplAddress}</td>
				<td>
					<a href="${tempUpdate}">Edit</a> | 
					<a href="${tempDelete}" onclick="if(!(confirm('Are you sure?'))) return false">Delete</a> | 			
					<a href="${tempCompetency}">View Competency</a>
				</td>
			</tr>
		</c:forEach>
	</table> 
	<input type="button" class="btn btn-primary" value="Back to Home" onclick="window.location='/rms-servlet-web';"></input>
	<input type="button" class="btn btn-success" value="Add New Employee" onclick="window.location='new';"></input>
</div>

</body>
</html>