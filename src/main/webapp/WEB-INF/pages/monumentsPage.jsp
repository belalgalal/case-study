<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Monuments Page</title>
</head>
<body>
	<h1>Monuments Page</h1>
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<c:redirect url="<c:url value='/' />" />
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h4>Current collection is:${currentCollectionName}. Use the bellow table to manipulate through the monuments data.</h4>
		<h4>Or <a href="<c:url value='/addMonument' />" >click here</a> to create new monument.</h4>
		<form:form action="" method="GET">
			<br><br>
			<table border="1" style="width:50%">
				<tr>
					<th>Monument Name</th>
					<th>Description</th>
					<th>Category Name</th>
					<th>Edit Link</th>
					<th>Delete Link</th>
				</tr>
				
				<c:forEach var="monument" items="${monumentsList}">
					<tr>
						<td><a href="<c:url value='/picturesPage' />?monumentId=${monument.monumentId}&monumentName=${monument.monumentName}" >${monument.monumentName}</a></td>
						<td>${monument.monumentDesc}</td>
						<td>${monument.category.categoryName}</td>
						<td><a href="<c:url value='/editMonument' />?id=${monument.monumentId}" >Edit</a></td>
						<td><a href="<c:url value='/deleteMonument' />?id=${monument.monumentId}" >Delete</a></td>
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