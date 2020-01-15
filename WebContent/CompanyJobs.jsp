<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Jobs</title>
</head>
<body>
	<h1>${messages.title}</h1>
	<br/>
	<div id="jobCreate"><a href="jobcreate">Create Job</a></div>
	<br/>
        <table border="1">
            <tr>
                <th>JobId</th>
                <th>CompanyId</th>
                <th>MainJobTitle</th>
                <th>DateAdvertised</th>
                <th>Classification</th>
                <th>JobLocation</th>
                <th>Delete Job</th>
                <th>Update Job</th>
            </tr>
            <c:forEach items="${jobs}" var="job" >
                <tr>
                    <td><c:out value="${job.getJobId()}" /></td>
                    <td><c:out value="${job.getCompany().getCompanyId()}" /></td>
                    <td><c:out value="${job.getMainJobTitle()}" /></td>
                    <td><fmt:formatDate value="${job.getDateAdvertised()}" pattern="MM-dd-yyyy"/></td>
                    <td><c:out value="${job.getClassification()}" /></td>
                    <td><a href="jobjoblocation?joblocationid=<c:out value="${job.getJobLocation().getJobLocationId()}"/>">JobLocation</a></td>
                    <td><a href="jobdelete?jobid=<c:out value="${job.getJobId()}"/>">Delete</a></td>
                    <td><a href="jobupdate?jobid=<c:out value="${job.getJobId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>