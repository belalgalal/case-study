<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Register Page</title>

</head>

<body>
	
	<h1>Register Page</h1>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<c:redirect url="/"/>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<form:form action="register" method='POST' commandName="user">
			<table border="1" style="width:50%">
				<tr>
					<td>User Name:<FONT color="red"><form:errors path="userName" /></FONT></td>
				</tr>
				<tr>
					<td><form:input path="userName" /></td>
				</tr>
				<tr>
					<td>Password:<FONT color="red"><form:errors path="password" /></FONT></td>
				</tr>
				<tr>
					<td><form:password path="password" /></td>
				</tr>
	
				<tr>
					<td>Confirm Password:<FONT color="red"><form:errors path="confirmPassword" /></FONT></td>
				</tr>
				<tr>
					<td><form:password path="confirmPassword" /></td>
				</tr>
				<tr>
					<td>First Name:<FONT color="red"><form:errors path="firstName" /></FONT></td>
				</tr>
				<tr>
					<td><form:input path="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name:<FONT color="red"><form:errors path="lastName" /></FONT></td>
				</tr>
				<tr>
					<td><form:input path="lastName" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" />
					</td>
				</tr>
				<tr>
					<td><input type="reset" value="Reset" />
					</td>
				</tr>
			</table>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form:form>
	</c:if>
	
</body>

</html>