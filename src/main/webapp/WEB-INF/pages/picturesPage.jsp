<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pictures Page</title>
</head>
<body>
	<h1>Pictures Page</h1>
	
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
		<h4>Current monument is:${currentMonumentName}. Use the bellow table to manipulate through the pictures data.</h4>
		<h4>Or <a href="<c:url value='/addPicture' />" >click here</a> to create new picture.</h4>
		<form:form action="" method="GET">
			<br><br>
			<table border="1" style="width:50%">
				<tr>
					<th>Picture Name</th>
					<th>Description</th>
					<th>Picture Date</th>
					<th>Edit Link</th>
					<th>Delete Link</th>
				</tr>
				
				<c:forEach var="picture" items="${picturesList}">
					<tr>
						<td>${picture.pictureName}</td>
						<td>${picture.pictureDesc}</td>
						<td>${picture.pictureDate}</td>
						<td><a href="<c:url value='/editPicture' />?id=${picture.pictureId}" >Edit</a></td>
						<td><a href="<c:url value='/deletePicture' />?id=${picture.pictureId}" >Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
		<br><br>
		<h2><a href="<c:url value='/' />" >Home sweet Home!</a></h2>
		<h2>
			User : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	
</body>
</html>