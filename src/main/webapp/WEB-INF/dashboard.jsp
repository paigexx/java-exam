<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- c:out ; c:forEach ; c:if -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
   <!-- Formatting (like dates) -->
 <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   <!-- form:form -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
   <!-- for rendering errors on PUT routes -->
 <%@ page isErrorPage="true" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome ${user.firstName }</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>
	<h1>Welcome, ${user.firstName} ${user.lastName }</h1>
	<a href="/logout">Logout</a>
	<h3>Shows</h3>
	<table>
		<thead>
			<tr>
				<th>Show Name</th>
				<th>Network</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${shows}" var="show">
				<tr>
					<td><a href="/show/${show.id}">${show.name }</a></td>
					<td>${show.network }</td>
					</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<a href="/show/new">Add Show</a>

</body>
</html>