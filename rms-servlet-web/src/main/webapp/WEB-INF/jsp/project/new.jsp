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
<title>New Project Form</title>
</head>
<%
	String dat =(String) request.getAttribute("currDate");
%>
<body>
<div class="container">
<h1>New Project Form</h1>
	<form action="list" method="POST" id="inputForm">
		<div class="form-group">
		<table border="1" width="75%">
			<tr>
				<td width="40%">Project Name</td>
				<td><input type="text" name="projectNameForm" required="true" maxlength="200" class="form-control"></input></td>
			</tr>
			<tr>
				<td width="40%">Project Start Date</td>
				<td><input type="date" id="projectStartForm" name="projectStartForm" required="true" min="<%=dat %>"  class="form-control"></input></td>
			</tr>
			<tr>
				<td width="40%">Project End Date</td>
				<td><input type="date" id="projectEndForm" name="projectEndForm" required="true" min="<%=dat %>" class="form-control"></input></td>
			</tr>
			<tr>
				<td width="40%">Project Requirement</td>
				<td><select name="ListSkill">
					<c:forEach var="x" items="${listSkillAttribute}">	
					<option value="${x.skillName}">${x.skillName}</option>	
  					</c:forEach>
  					</select>
				</td>
			</tr>
		</table>
		<input name="command" value="CREATE" type="hidden"></input>			
		<br><input type="submit" value="Create" class="btn btn-success" >
		</div>
	</form>
		<c:url var="tempCancel" value="list">
			<c:param name="command" value="LOAD_ALL"></c:param>
		</c:url>
	<a href="${tempCancel}">
 	<button class="btn btn-primary">Cancel</button>
 </a> 
</div>	
<script>
	var start = document.getElementById('projectStartForm');
	var end = document.getElementById('projectEndForm');
	start.addEventListener('change', function() {
	    if (start.value)
	        end.min = start.value;
	}, false);
	end.addEventLiseter('change', function() {
	    if (end.value)
	        start.max = end.value;
	}, false);
</script>
</body>
</html>