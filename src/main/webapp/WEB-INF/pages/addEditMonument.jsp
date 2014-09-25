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
		<h4>${message}</h4>
		
		<form:form action="${action}" method='POST' commandName="monument">
			<table border="1" style="width:50%">
				<tr>
					<td>Monument Name:<FONT color="red"><form:errors path="monumentName" /></FONT></td>
				</tr>
				<tr>
					<td><form:input  path="monumentName" /></td>
				</tr>
				<tr>
					<td>Monument Description:<FONT color="red"><form:errors path="monumentDesc" /></FONT></td>
				</tr>
				<tr>
					<td><form:input  path="monumentDesc" /></td>
				</tr>
				<tr>
					<td>Category:<FONT color="red"><form:errors path="category.categoryId" /></FONT></td>
				</tr>
				<tr>
					<td>
						<form:select path="category.categoryId">
							<form:options items="${categories}" itemLabel="categoryName" itemValue="categoryId"/>
						</form:select>
					</td>
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
			<input type="hidden" name="monumentId" value="${monument.monumentId}" />
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