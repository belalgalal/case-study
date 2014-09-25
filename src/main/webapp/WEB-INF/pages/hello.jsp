<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<title>Welcome Page</title>
<body>
	<h1>${title}</h1>
	<h4>${message}</h4>

	<sec:authorize access="hasRole('ROLE_USER')">
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

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<ul>
				<li><a href="<c:url value='/collectionsPage' />" >Collections Page</a></li>
				<li><a href="<c:url value='/categoriesPage' />" >Categories Page</a></li>
			</ul>
			
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>
	</sec:authorize>
	
	
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<ul>
			<li><a href="<c:url value='/login' />" >Click here to Login</a></li>
			<li><a href="<c:url value='/register' />" >Click here to Register</a></li>
		</ul>
	</c:if>
	
</body>
</html>