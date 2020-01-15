<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${table.getName()}</title>
</head>
<body>
	<c:if test="${result.size()>0}">
		<h1>Edit ${table.getName()} </h1>
		<form action="#" method="POST">
			<c:forEach items="${result.entrySet()}" var="entry">
				<p>
					<label for="${ entry.getKey() }">${ entry.getKey() }</label> <input
						id="${ entry.getValue() }" name="${ entry.getKey() }"
						value="${ entry.getValue() }">
				</p>
			</c:forEach>
			<input type="submit" value="Submit">
		</form>
	</c:if>
	<c:if test="${result.size()==0}">
		<h1>Add ${table.getName()} </h1>
		<form action="../view/${ table.getName() } " method="POST">
			<c:forEach items="${table.getFields().entrySet()}" var="entry">
				<p>
					<label for="${ entry.getKey() }">${ entry.getKey() }</label> <input
						id="${ entry.getValue() }" name="${ entry.getKey() }">
				</p>
			</c:forEach>
			<input type="submit" value="Submit">
		</form>
	</c:if>
</body>
</html>