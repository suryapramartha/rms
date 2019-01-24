<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta name="author" content="Mitrais">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="stylesheet" href="../css/bootstrap.min.css">   		
    <script src="../js/bootstrap.min.js"></script>    
<title>Project List</title>
</head>
<body>
<div class="container">
<h1 align="left">Project List</h1>
<table border="1" class="table table-striped">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Start Date</th>
		<th>End Date</th>
		<th>Status</th>
		<th>Requirements</th>
		<th>PIC</th>
		<th>Action</th>
	</tr>
	<c:forEach var="x" items="${projectAttribute}">
		<c:url var="tempUpdate" value="update">
			<c:param name="command" value="EDIT"></c:param>
			<c:param name="projectId" value="${x.projectId}"></c:param>
		</c:url>
		<c:url var="tempDelete" value="list">
			<c:param name="command" value="DELETE"></c:param>
			<c:param name="projectId" value="${x.projectId}"></c:param>
		</c:url>
			<c:url var="tempNewPIC" value="newPIC">
			<c:param name="command" value="NEWPIC"></c:param>
			<c:param name="projectId" value="${x.projectId}"></c:param>
			<c:param name="empl_name" value="${x.emplName}"></c:param>
		</c:url>
		<tr>
			<td>${x.projectId}</td>
			<td>${x.projectName}</td>
			<td>${x.projectStartDate}</td>
			<td>${x.projectEndDate}</td>
			<td>${x.projectStatus}</td>
			<td>${x.projectRequirement}</td>
			<td>${x.emplName}</td>
			<td>
				<a href="${tempUpdate}">Edit</a> | 
				<a href="${tempDelete}" onclick="if(!(confirm('Are you sure?'))) return false">Delete</a> |
				<a href="${tempNewPIC}">Assign PIC</a> 
			</td>
		</tr>
	</c:forEach>
</table>
<input type="button" class="btn btn-primary" value="Back to Home" onclick="window.location='/rms-servlet-web';"></input> 
<c:url var="tempNewProject" value="new">
		<c:param name="command" value="NEW"></c:param>
</c:url>
 <a href="${tempNewProject}">
 	<button class="btn btn-success"> New Project </button>
 </a>   
 
</div>

</body>
</html>