<%@ page isErrorPage="true"%>
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Exception - Final Project</title>
</head>
<body>
	<section>
		<form>
			<h1>Server Error</h1>
			<%-- <h2>${ pageContext.exception.message }</h2> --%>
			<h2>
				<c:out
					value="${requestScope[\"javax.servlet.error.exception\"].message}" />
			</h2>
			<a href=".">Click here to go to main page</a>
		</form>
	</section>
</body>
</html>
