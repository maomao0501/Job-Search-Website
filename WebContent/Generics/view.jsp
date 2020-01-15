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
	<c:forEach items="${result.entrySet()}" var="entry">
		<div>
			<label> ${entry.getKey()} :</label> <span> ${entry.getValue()}
			</span>
		</div>
	</c:forEach>
	
	<form action="../delete/${ table.getName() }" method="POST">
	<input id="${ table.getPk() }" name="${ table.getPk() }" value="${ result.get(table.getPk()) }" style="display:none">
		<input type="submit" value="Delete">
	</form>

	<button onclick="window.location.href='../edit/${ table.getName() }'">Add</button>
	<button onclick="window.location.href='../edit/${ table.getName() }?${ table.getPk() }=${ result.get(table.getPk()) }'">Edit</button>
</body>
</html>