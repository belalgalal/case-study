<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>
</head>
<body>
	<h1>${title}</h1>
	
	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<c:redirect url="<c:url value='/' />" />
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h4>${message}</h4>
		
		<form:form action="${action}" method='POST' commandName="picture">
			<table border="1" style="width:50%">
				<tr>
					<td>Picture Name:<FONT color="red"><form:errors path="pictureName" /></FONT></td>
				</tr>
				<tr>
					<td><form:input  path="pictureName" /></td>
				</tr>
				<tr>
					<td>Picture Description:<FONT color="red"><form:errors path="pictureDesc" /></FONT></td>
				</tr>
				<tr>
					<td><form:input  path="pictureDesc" /></td>
				</tr>
				<tr>
					<td>Category:<FONT color="red"><form:errors path="pictureDesc" /></FONT></td>
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
			<input type="hidden" name="pictureId" value="${picture.pictureId}" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form:form>
		<br><br>
		<h2><a href="<c:url value='/' />" >Home sweet Home!</a></h2>
		<h2>
			User : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>
	
	
</body>
</html>