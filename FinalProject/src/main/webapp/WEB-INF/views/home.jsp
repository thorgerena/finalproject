<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Final Project</title>
</head>
<body>
<h1>Final Project</h1>
<c:choose>
<c:when test="${  not empty sessionScope.loggeduser }">
	<div>Welcome back, ${sessionScope.loggeduser.username }</div>
	
	<form action="getCoupons" method="post">
		<label>Username: </label>
		<input type="text" name="username"/>
		<button type="submit">Get my coupons</button>
	</form>
	
	<div><a href="logout">Logout</a></div>
</c:when>
<c:otherwise>
<div><a href="login">Login</a></div>
</c:otherwise>
</c:choose>
<div><a href="register">Register</a></div>
<div><a href="blabla">LinkToShowExceptionWorks</a></div>
</body>
</html>
