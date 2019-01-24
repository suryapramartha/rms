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
<title>Competency List</title>
</head>
<%
	List<String> list = (List<String>) request.getAttribute("currEmployeeAttribute");
	String id = list.get(0);
	String name = list.get(1);
	String age = list.get(2);
	String address = list.get(3);
%>
<body>
<div class="container">
<h1 align="left">Competency List</h1>
<div class="jumbotron">
<p>Employee ID 		: <%=id%></p>
<p>Employee Name    : <%=name%></p>
<p>Employee Age     : <%=age%></p>
<p>Employee Address : <%=address%></p><br>
</div>
<table border="1" class="table table-striped">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Competency</th>
		<th>Action</th>
	</tr>
	<c:forEach var="x" items="${assignedSkillAttribute}">
		<c:url var="tempDelete" value="viewCompetency">
			<c:param name="command" value="DELETE_CMP"></c:param>
			<c:param name="skillId" value="${x.skillId}"></c:param>
			<c:param name="employeeId" value="${x.emplId}"></c:param>
		</c:url>
		<tr>
			<td>${x.emplId}</td>
			<td>${x.emplName}</td>
			<td>${x.skillName}</td>
			<td><a href="${tempDelete}" onclick="if(!(confirm('Are you sure?'))) return false">Delete</a></td>
		</tr>
	</c:forEach>
</table>
<input type="button" class="btn btn-primary" value="Home" onclick="window.location='/rms-servlet-web';"></input>
<input type="button" class="btn btn-primary" value="Back to Employee List" onclick="window.location='list';"></input> 
	<c:url var="tempEmployee" value="newCompetency">
			<c:param name="command" value="NEW_CMP"></c:param>
			<c:param name="employeeId" value="<%=id%>"></c:param>
		</c:url>
 <a href="${tempEmployee}">
 	<button class="btn btn-success"> Assign New Competency </button>
 </a>   

</div>

</body>
</html>