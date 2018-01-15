<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Register - Final Project</title>
</head>
<body>
	<form action="registeruser" method="post">

		<div class="container">
			<c:if test="${ emptyField eq true }">
				<div>No field can be an empty string!</div>
			</c:if>
			<c:if test="${ emailexists eq true }">
				<div>This email has already been registered!</div>
			</c:if>
			<c:if test="${ usernameexists eq true }">
				<div>Username already exists. Please choose another.</div>
			</c:if>
			<c:if test="${ registered eq false }">
				<div>Oops something went wrong. Please try again</div>
			</c:if>
			<label><b>First name</b></label> <input type="text"
				placeholder="Enter first name" name="firstName" required /> <label>
				<b>Last name</b>
			</label> <input type="text" placeholder="Enter last name" name="lastName"
				required /> <label> <b>Email</b>
			</label> <input type="text" placeholder="Enter your email" name="email"
				required /> <label> <b>Username</b>
			</label> <input type="text" placeholder="Create your username"
				name="username" required /> <label> <b>Password</b>
			</label> <input type="password" placeholder="Enter Password" name="password"
				required />

			<button class="button" type="submit">Submit</button>
			<br />
		</div>
	</form>
</body>
</html>
