<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
  	<meta name="author" content="Mitrais">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link rel="stylesheet" href="../css/bootstrap.min.css">   		
    <script src="../js/bootstrap.min.js"></script>    
<title>New Employee Form</title>
</head>
<body>
<div class="container">
<h1>New Employee Form</h1>
	<form action="list" method="POST">
		<div class="form-group">
		<table border="1" width="75%">
			<tr>
				<td width="25%">Employee Name</td>
				<td><input type="text" name="emplNameForm" required="true" class="form-control"></input></td>
			</tr>
			<tr>
				<td>Employee Age</td>
				<td><input type="number" name="emplAgeForm" min="0" required="true" class="form-control"></input></td>
			</tr>
			<tr>
				<td>Employee Address</td>
				<td><input type="text" name="emplAddressForm" multiple="true" required="true" class="form-control"></input></td>
			</tr>
		</table>
		<input name="command" value="CREATE" type="hidden"></input>
		<br><input type="submit" value="Create" class="btn btn-success">
		</div>
	</form>
	<input type="button" class="btn btn-primary" value="Cancel" onclick="window.location='list';"></input>
</div>	

</body>
</html>