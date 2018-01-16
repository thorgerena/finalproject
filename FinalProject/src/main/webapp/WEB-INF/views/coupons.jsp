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

			<c:if test="${ redeemed eq true }">
				<div>You have successfully redeemed your coupon!</div>
			</c:if>
			<c:if test="${ redeemed eq false }">
				<div>Sorry, there are no more uses of this coupon</div>
			</c:if>

			<form action="redeem" method="post">
				<label>Coupon ID: </label> <input type="hidden" name="username"
					value="${sessionScope.loggeduser.username }"> <select
					name="couponID">
					<c:forEach var="c" items="${ coupons }">
						<option value="${ c.couponID }">${c.couponID }: ${ c.description }, uses left: ${ c.uses }</option>
					</c:forEach>
				</select>
				<button type="submit">Redeem</button>
			</form>

			<div>
				<a href="logout">Logout</a>
			</div>
		</c:when>
		<c:otherwise>
			<div>
				<a href="login">Login</a>
			</div>
		</c:otherwise>
	</c:choose>
	<div>
		<a href="register">Register</a>
	</div>
	<div>
		<a href="blabla">LinkToShowExceptionWorks</a>
	</div>
</body>
</html>
