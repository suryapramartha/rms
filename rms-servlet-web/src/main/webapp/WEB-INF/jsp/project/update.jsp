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
<title>Update Project</title>
</head>
<%
	List<String> list = (List<String>) request.getAttribute("projectSelectedAttribute");
	String id = list.get(0);
	String name = list.get(1);
	String start_date = list.get(2);
	String end_date = list.get(3);
	String status = list.get(4);
	String req = list.get(5);
	String pic = list.get(6);
%>

<%
	String dat =(String) request.getAttribute("currDate");
	String dat1 = dat+1;
%>
<body>
<div class="container">
	<h1>Update Project</h1>
	<div class="form-group" >
	<form action="list" method="POST">
		<table border="1" width="75%" >
			<tr>
				<td width="25%">Project ID</td>
				<td><input type="text" disabled="disabled" value="<%=id%>" class="form-control"></input></td>
			</tr>
			<tr>
				<td>Project Name</td>
				<td><input type="text" name="projectNameForm" value="<%=name%>" required="required" class="form-control" maxlength="50"></input></td>
			</tr>
			<tr>
				<td>Start Date</td>
				<td><input type="date" id="projectStartForm" name="startDateForm" value="<%=start_date%>" min="<%=dat%>" required="required" class="form-control"></input>
				</td>
			</tr>
			<tr>
				<td>End Date</td>
				<td><input type="date" id="projectEndForm" name="endDateForm"  value="<%=end_date%>" min="<%=dat%>"  required="required" class="form-control"></input></td>
			</tr>
			<tr>
				<td>Status</td>
				<td><input type="text" disabled="disabled" value="<%=status%>" class="form-control"></input></td>
			</tr>
			
			<tr>
				<td>Project Requirement</td>
				<td><select name="ListSkill">
					<c:forEach var="x" items="${listSkillAttribute}">	
					<option value="${x.skillName}">${x.skillName}</option>	
  					</c:forEach>
  					</select>
				</td>
			</tr>
			<tr>
				<td>PIC</td>
				<td><input type="text" name="picForm"  disabled="disabled" value="<%=pic%>" class="form-control"></input></td>
			</tr>
		</table>
		<input name="command" value="UPDATE" type="hidden"></input>
		<input name="projectIdForm" value="<%=id%>" type="hidden"></input>
		<input name="currReq" value="<%=req%>" type="hidden"></input>
		<br><input type="submit" value="Update" class="btn btn-success">
	</form>
	</div>
	<input type="button" class="btn btn-primary" value="Cancel" onclick="window.location='list';"></input> 
	
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