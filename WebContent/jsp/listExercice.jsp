<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<sql:query var="exercices" dataSource="jdbc/gymlogger">
    select id, name from exercice;
</sql:query>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>list</title>
	</head>

<body>

	<div class="main-menu">
		<ul>
			<li><a href="">list</a></li>
			<li><a href="">create</a></li>
			<li><a href="">calendar</a></li>
		</ul>
	</div>
	
	<div class="content">exercice</div>
	
	<br>
	
	<table border="1" cellpadding="5">
	    <!-- <caption><h2>List of users</h2></caption> -->
	    <tr>
	        <th>Name</th>
	        <th>Email</th>
	    </tr>
	    <c:forEach var="exercice" items="${exercices.rows}">
	        <tr>
	            <td><c:out value="${exercice.id}" /></td>
	            <td><c:out value="${exercice.name}" /></td>
	        </tr>
	    </c:forEach>
	</table>
	
</body>

</html>