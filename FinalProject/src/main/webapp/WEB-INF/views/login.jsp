<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Login - Final Project</title>
</head>
<body>
	<form action="loginuser" method="post">
		<c:if test="${ registered eq true }">
			<div>You have successfully registered! Please login to use your
				access!</div>
		</c:if>
		<c:if test="${ wrongusername eq true }">
			<div>Username not found. Please try again.</div>
		</c:if>
		<c:if test="${ wrongpassword eq true }">
			<div>Username and password don't match.</div>
		</c:if>
		
		<div class="container">
			<label><b>Username</b></label> 
			<input type="text" placeholder="Enter Username" name="username" required/> 
			<label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="password"	required/>
			<button class="button" type="submit">Login</button>
		</div>
	</form>
</body>
</html>
