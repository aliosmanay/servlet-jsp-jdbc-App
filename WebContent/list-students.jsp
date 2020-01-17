 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>JSP Student APP</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	
	 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
 <script
  src="https://code.jquery.com/jquery-3.3.1.min.js"
  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
  crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.2/jquery.form.min.js" integrity="sha384-FzT3vTVGXqf7wRfy8k4BiyzvbNfeYjK+frTVqZeNDFl8woCbF0CYG6g2fMEFFo/i" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/lazysizes/5.1.0/lazysizes.min.js"></script>
	
	
</head>

<body>

<div class="container" style ="margin-top :30px;">
	<div class="row">
		<div class="col-sm-12">
	
		<h2>AliOsman University</h2>
		</div>
	
	</div>

	<div class="row">
	
		<div class="col-sm-12">
		
		<input type="button" value ="Add Student"
		onclick="window.location.href='add-student-form.jsp' ; return false"
		class="btn btn-success"
		/>
		
			<table class="table">
			
			<tr>
			<th scope="col"> First Name  </th>
			<th scope="col"> Last Name  </th>
			<th scope="col"> Email  </th>
			<th scope="col"> Action  </th>
			
			</tr>
			
			<c:forEach var="tempStudent" items="${STUDENT_LIST}">
			
				<c:url var ="tempLink" value="StudentControllerServlet">
					<c:param name="command" value="LOAD"></c:param>
					<c:param name="studentId" value="${tempStudent.id}"></c:param>
				
				</c:url>
				
					<c:url var ="deleteLink" value="StudentControllerServlet">
					<c:param name="command" value="DELETE"></c:param>
					<c:param name="studentId" value="${tempStudent.id}"></c:param>
				
				</c:url>
			
				<tr>
					<td> ${tempStudent.firstNameString }  </td>
					<td> ${tempStudent.lastNameString }  </td>
					<td> ${tempStudent.emailString }  </td>
					<td>  
						<a href="${tempLink }">Update</a>  
						|
						<a href ="${deleteLink }"
						onclick="if (!(confirm('Are you sure you want to delete?'))) return false">Delete</a>
						
					</td>
					
				</tr>
			
			
			</c:forEach>
			
			</table>
		
		</div>
	
	
	</div>

</div>




</body>
</html>