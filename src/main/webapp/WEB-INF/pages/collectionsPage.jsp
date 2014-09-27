<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Collections Page</title>
</head>
<body>
	<h1>Collections Page</h1>
	
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
		<c:redirect url="<c:url value='/' />" />
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h4>Use the bellow table to manipulate through the collections data.</h4>
		<h4>Or <a href="<c:url value='/addCollection' />" >click here</a> to create new collection.</h4>
		
		<br><br>
		<table border="1" style="width:50%">
			<tr>
				<th>Collection Name</th>
				<th>Edit Link</th>
				<th>Delete Link</th>
			</tr>
			
			<c:forEach var="collection" items="${collectionsList}">
				<tr>
					<td><a href="<c:url value='/monumentsPage' />?collectionId=${collection.collectionId}&collectionName=${collection.collectionName}" >${collection.collectionName}</a></td>
					<td><a href="<c:url value='/editCollection' />?id=${collection.collectionId}" >Edit</a></td>
					<td><a href="<c:url value='/deleteCollection' />?id=${collection.collectionId}" >Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
		<h2><a href="<c:url value='/' />" >Home sweet Home!</a></h2>
		<h2>
			User : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	
</body>
</html>