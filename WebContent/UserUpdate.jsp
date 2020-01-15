<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a User</title>
</head>
<body>
	<h1>Update User</h1>
	<form action="userupdate?action=update" method="post">
	
		<p>
			<label for="UserAccountId">UserAccountId</label>
			<input id="UserAccountId" name="UserAccountId" value="${jobUser.getUserAccountId()}">
		</p>
		<p>
			<label for="UserTypeId">UserTypeId</label>
			<input id="UserTypeId" name="UserTypeId" value="${jobUser.getUserType().getUserTypeId()}">
		</p>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="${jobUser.getFirstName()}">
		</p>
		<p>
			<label for="lastname">LastName</label>
			<input id="lastname" name="lastname" value="${jobUser.getLastName()}">
		</p>
		<p>
			<label for="email">email </label>
			<input id="email" name="email" value="${jobUser.getEmail()}">
		</p>
		<p>
			<label for="password">password</label>
			<input id="password" name="password" value="${jobUser.getPassword()}">
		</p>
		<p>
			<label for="DateOfBirth">DateOfBirth(yyyy-mm-dd)</label>
			<input id="DateOfBirth" name="DateOfBirth" value="${jobUser.getDateOfBirth()}">
		</p>
		<p>
			<label for="Gender">Gender</label>
			<input id="Gender" name="Gender" value="${jobUser.getGender()}">
		</p>
		<p>
			<label for="ContactNumber">ContactNumber</label>
			<input id="ContactNumber" name="ContactNumber" value="${jobUser.getContactNumber()}">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>