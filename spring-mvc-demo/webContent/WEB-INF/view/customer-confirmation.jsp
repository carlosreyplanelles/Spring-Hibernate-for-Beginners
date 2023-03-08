<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Customer Confirmation</title>
</head>
<body>

	<h1>Registration successful</h1>
	<p><label>Registration confirmed:</label><label> ${customer.firstName} ${customer.lastName}</label></p>
	<p><label>Free passes: </label><label>${customer.freePasses}</label></p>
	<p><label>Postal Code: </label><label>${customer.postalCode}</label></p>
	<p><label>Course Code: </label><label>${customer.courseCode}</label></p>
</body>
</html>