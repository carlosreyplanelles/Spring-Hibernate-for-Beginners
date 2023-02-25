<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Student Registration Form</title>
	</head>
	<body>
	
	<h2>Student Registration Form</h2>
	<form:form action="confirmation" modelAttribute="student">
		<div>
		First Name: <form:input path="firstName"/>
		</div>
		<div>
		Last Name: <form:input path="lastName"/>
		</div>
		<div>
		Country:
		<form:select path="country">
			<form:options items="${student.countries}"/>
		</form:select>
		</div>
		Programming languages:
		<div>
			<form:radiobuttons path="favoriteLanguage" items="${student.favoriteLanguageOptions}"  />
		</div>
		Operating Systems:
		<div>
			<form:checkboxes items="${student.operatingSystems}" path="userOS"/>
		</div>
		<input type="submit" value="Submit">
	</form:form>

	</body>
</html>