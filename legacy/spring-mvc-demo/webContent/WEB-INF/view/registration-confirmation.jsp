<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Confirmation</title>
</head>
<body>
<h1>Student registration completed</h1>
<article>
Student registration confirmed: ${student.firstName} ${student.lastName}
<p>
Country: ${student.country}
</p>
<p>
Preferred programming language: ${student.favoriteLanguage}
</p>
<p>
Operating systems:
<ul>
	<c:forEach var="os" items="${student.userOS}">
	<li>${os}</li>
	</c:forEach>
</ul>
</p>
</article>

</body>
</html>