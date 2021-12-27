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
<title>${show.name}</title>
  <!-- Bootstrap -->
  <link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>
<a href="/dashboard">Back To Dashboard</a>
	<h1>${show.name}</h1>
	<p>Posted by: ${show.user.firstName}</p>
	<p>Network: ${show.network}</p>
	<p>Description: ${show.description}</p>
	<a href="/edit/${show.id}">Edit</a>

	<c:set var="contains" value="false" />
	<c:forEach var="item" items="${ratings}">
  	<c:if test="${item.user eq user}">
    <c:set var="contains" value="true" />
  	</c:if>
	</c:forEach> 
	
	
	<c:if test="${contains == false}">						
	<form:form method="post" action="/add/rating" modelAttribute="rating">
		<form:hidden path="user" value="${user.id}"/>
		<form:hidden path="show" value="${show.id}"/>
		<form:label path="score">Add a rating: </form:label>
		<form:errors path="score"/>
		<form:input type="number" path="score"/>
		 <input class="btn btn-primary btn-sm" type="submit" value="Add Rating!"/>
	</form:form>
	</c:if>
	
	<c:if test="${contains == true}">
		<p>Check out your rating below.</p>
	</c:if>
	
	
	<p>This show is rated by: </p>
	<c:forEach items="${ratings }" var="rating">
		<p>${rating.user.firstName} ${rating.user.lastName} Rating:  ${rating.score}</p>
	</c:forEach>

</body>
</html>