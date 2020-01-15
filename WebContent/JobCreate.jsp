<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Job</title>
</head>
<body>
	<h1>Create Job</h1>
	<form action="jobcreate" method="post">
		<p>
			<label for="companyid">CompanyId</label>
			<input id="companyid" name="companyid" value="">
		</p>
		<p>
			<label for="mainjobtitle">MainJobTitle</label>
			<input id="mainjobtitle" name="mainjobtitle" value="">
		</p>
		<p>
			<label for="dateadvertised">DateAdvertised(yyyy-mm-dd)</label>
			<input id="dateadvertised" name="dateadvertised" value="">
		</p>
		<p>
			<label for="joblocationid">JobLocationId</label>
			<input id="joblocationid" name="joblocationid" value="">
		</p>
		<p>
			<label for="worktype">WorkType </label>
			<input id="worktype" name="worktype" value="">
		</p>
		<p>
			<label for="classification">Classification</label>
			<input id="classification" name="classification" value="">
		</p>
		<p>
			<label for="jobdescription">JobDescription</label>
			<input id="jobdescription" name="jobdescription" value="">
		</p>
		<p>
			<label for="pageURL">PageURL</label>
			<input id="pageURL" name="pageURL" value="">
		</p>
		<p>
			<label for="useraccountid">UserAccountId</label>
			<input id="useraccountid" name="useraccountid" value="">
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