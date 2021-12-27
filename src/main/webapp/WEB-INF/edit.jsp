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
<title>Edit {show.name}</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>
<h1>Edit ${show.name} ${user.id }</h1>
	<form:form method="post" action="/show/update/${show.id}" modelAttribute="show">
	<form:hidden path="user" value="${user.id}"/>
	<p>
		<form:label path="name" >Show Name</form:label>
		<form:errors path="name"/>
		<form:input path="name"/>
	</p>
		<p>
		<form:label path="network">Network</form:label>
		<form:errors path="network"/>
		<form:input path="network"/>
	</p>
		<p>
		<form:label path="description" >Description</form:label>
		<form:errors path="description"/>
		<form:input type="textarea" path="description"/>
	</p>
	<a href="/dashboard">Cancel</a>
	 <input class="btn btn-primary" type="submit" value="Update!"/>
</form:form>
<a href="/show/delete/${show.id}">Delete Show</a>

</body>
</html>