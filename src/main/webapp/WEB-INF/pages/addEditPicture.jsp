<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>
<script language="JavaScript">
	function Validate() {
		var image = document.getElementById("image").value;
		if (image != '') {
			var checkimg = image.toLowerCase();
			if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
				alert("Please enter  Image  File Extensions .jpg,.png,.jpeg");
				document.getElementById("image").focus();
				return false;
			}
		}
		return true;
	}
</script>
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

		<form:form action="${action}?${_csrf.parameterName}=${_csrf.token}" method='POST' modelAttribute="picture" enctype="multipart/form-data" onSubmit="return Validate();">
			<table border="1" style="width: 50%">
				<tr>
					<td>Picture Name:<FONT color="red"><form:errors path="pictureName" /></FONT></td>
				</tr>
				<tr>
					<td><form:input path="pictureName" /></td>
				</tr>
				<tr>
					<td>Picture Description:<FONT color="red"><form:errors path="pictureDesc" /></FONT></td>
				</tr>
				<tr>
					<td><form:input path="pictureDesc" /></td>
				</tr>
				<tr>
					<td>Picture Date (dd/MM/yyyy):<FONT color="red"><form:errors path="pictureDate" /></FONT></td>
				</tr>
				<tr>
					<td><form:input path="pictureDate" /></td>
				</tr>
				<c:if test="${mode == 'add'}">
					<tr>
						<td><form:label for="fileData" path="fileData">Picture:</form:label></td>
					</tr>
					<tr>
						<td><form:input path="fileData" id="image" type="file" /></td>
					</tr>
				</c:if>
				<c:if test="${mode == 'edit'}">
					<tr>
						<td><form:label for="fileData" path="fileData">Picture:</form:label></td>
					</tr>
					<tr>
						<td><img src="getPicture" width="100" height="100"/></td>
					</tr>
				</c:if>
				<tr>
					<td><input type="submit" value="Submit" /></td>
				</tr>
				<tr>
					<td><input type="reset" value="Reset" /></td>
				</tr>
			</table>
			<input type="hidden" name="pictureId" value="${picture.pictureId}" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form:form>
		<br>
		<br>
		<h2>
			<a href="<c:url value='/' />">Home sweet Home!</a>
		</h2>
		<h2>
			User : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>


</body>
</html>