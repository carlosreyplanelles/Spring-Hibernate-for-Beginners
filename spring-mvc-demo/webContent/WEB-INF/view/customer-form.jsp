<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Customer form</title>
	<style type="text/css">
		.error { color:red }
	</style>
</head>
<body>
	
	<h1>Customer Validation</h1>
	<form:form action="validate" modelAttribute="customer" >
		<div>
			First name: <form:input path="firstName"/>
		</div>
		<div>
			Last name: <form:input path="lastName"/>
			<form:errors path="lastName" class="error"/>
		</div>
		<div>
			FreePasses: <form:input type="number" path="freePasses"/>
			<form:errors path="freePasses" class="error"/>
		</div>
		<div>
			Postal code: <form:input path="postalCode"/>
			<form:errors path="postalCode" class="error"/>
		</div>
		<div>
			Course code: <form:input path="courseCode"/>
			<form:errors path="courseCode" class="error"/>
		</div>
		
		<input type="submit" value="Submit"/>
	</form:form>

</body>
</html>