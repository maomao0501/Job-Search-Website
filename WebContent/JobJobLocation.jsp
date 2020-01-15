<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JobLocation</title>
</head>
<body>
	<h1>${messages.title}</h1>
        <table border="1">
            <tr>
                <th>JobLocationId</th>
                <th>StreetAddress</th>
                <th>City</th>
                <th>State</th>
                <th>Country</th>
                <th>Zip</th>
            </tr>
            <c:set var="joblocation" value="${joblocation}"/>
                <tr>
                    <td><c:out value="${joblocation.getJobLocationId()}" /></td>
                    <td><c:out value="${joblocation.getStreetAddress()}" /></td>
                    <td><c:out value="${joblocation.getCity()}" /></td>
                    <td><c:out value="${joblocation.getState()}" /></td>
                    <td><c:out value="${joblocation.getCountry()}" /></td>
                    <td><c:out value="${joblocation.getZip()}" /></td>
                </tr>
       </table>
</body>
</html>