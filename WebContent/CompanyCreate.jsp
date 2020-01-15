<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Company</title>
</head>
<body>
	<h1>Create Company</h1>
	<form action="companycreate" method="post">
		<p>
			<label for="companyname">CompanyName</label>
			<input id="companyname" name="companyname" value="">
		</p>
		<p>
			<label for="url">URL</label>
			<input id="url" name="url" value="">
		</p>
		<p>
			<label for="yearfounded">YearFounded</label>
			<input id="yearfounded" name="yearfounded" value="">
		</p>
		<p>
			<label for="city ">City</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="state ">State</label>
			<input id="state" name="state" value="">
		</p>
		<p>
			<label for="country ">Country</label>
			<input id="country" name="country" value="">
		</p>
		<p>
			<label for="zipCode ">ZipCode</label>
			<input id="zipCode" name="zipcode" value="">
		</p>
		<p>
			<label for="companyType ">CompanyType</label>
			<input id="companyType" name="companytype" value="">
		</p>
		<p>
			<label for="description ">Description</label>
			<input id="description" name="description" value="">
		</p>
		<p>
			<label for="companyCategoryId ">CompanyCategoryId</label>
			<input id="companyCategoryId" name="companycategoryid" value="">
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