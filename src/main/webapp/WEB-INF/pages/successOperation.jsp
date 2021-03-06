<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success Operation</title>
</head>
	<body>
	
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
		
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<c:redirect url="/"/>
		</c:if>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h1>Success Operation</h1>
			<h4>Wohoooo!</h4>
			
			<br><br>
			<h2><a href="<c:url value='/' />" >Home sweet Home!</a></h2>
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
	</body>
</html>