<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Company</title>
</head>
<body>
	<form action="findcompanies" method="post">
		<h1>Search for a Company by State</h1>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="${fn:escapeXml(param.state)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="companyCreate"><a href="companycreate">Create Company</a></div>
	<br/>
	<h1>Matching Companies</h1>
        <table border="1">
            <tr>
                <th>CompanyId</th>
                <th>CompanyName</th>
                <th>YearFounded</th>
                <th>City </th>
                <th>State</th>
                <th>CompanyType</th>
                <th>Jobs</th>
                <th>Delete Company</th>
                <th>Update Company</th>
            </tr>
            <c:forEach items="${companies}" var="company" >
                <tr>
                    <td><c:out value="${company.getCompanyId()}" /></td>
                    <td><c:out value="${company.getCompanyName()}" /></td>
                    <td><c:out value="${company.getYearFounded()}" /></td>
                    <td><c:out value="${company.getCity()}" /></td>
                    <td><c:out value="${company.getState()}" /></td>
                    <td><c:out value="${company.getCompanyType()}" /></td>
                    <td><a href="companyjobs?companyid=<c:out value="${company.getCompanyId()}"/>">Jobs</a></td>
                    <td><a href="companydelete?companyid=<c:out value="${company.getCompanyId()}"/>">Delete</a></td>
                    <td><a href="companyupdate?companyid=<c:out value="${company.getCompanyId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
