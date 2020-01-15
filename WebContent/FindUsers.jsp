<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User</title>
</head>
<body>
	<form action="findusers" method="post">
		<h1>Search for a User by FirstName</h1>
		<p>
			<label for="firstname">FirstName</label>
			<input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="userCreate"><a href="usercreate">Create User</a></div>
	<br/>
	<h1>Matching Users</h1>
        <table border="1">
            <tr>
                <th>UserAccountId</th>
                <th>UserTypeId</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Password</th>
                <th>DateOfBirth</th>
                <th>Gender</th>
                <th>ContactNumber</th>
                <th>RegistrationDate</th>
                <th>operate</th>
            </tr>
            <c:forEach items="${jobUsers}" var="jobUser" >
                <tr>
               		<td><c:out value="${jobUser.getUserAccountId()}" /></td>
                    <td><c:out value="${jobUser.getUserType().getUserTypeId()}" /></td>
                   
                    <td><c:out value="${jobUser.getFirstName()}" /></td>
                    <td><c:out value="${jobUser.getLastName()}" /></td>
                    
                    <td><c:out value="${jobUser.getEmail()}" /></td>
                    <td><c:out value="${jobUser.getPassword()}" /></td>
                    <td><fmt:formatDate  value="${jobUser.getDateOfBirth()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${jobUser.getGender()}" /></td>
                    <td><c:out value="${jobUser.getContactNumber()}" /></td>
                    <td><fmt:formatDate value="${jobUser.getRegistrationDate()}" pattern="yyyy-MM-dd"/></td>
                          <td><a href="userdelete?UserAccountId=<c:out value="${jobUser.getUserAccountId()}"/>">Delete</a>
                    &nbsp;<a href="userupdate?action=get&UserAccountId=<c:out value="${jobUser.getUserAccountId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
