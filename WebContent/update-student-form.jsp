<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Student</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">

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


	<div class="row"">
	
		<div class="col-sm-12">
				<h3>Update Student</h3>
				
				<form action="StudentControllerServlet" method ="GET"  class="form-group">
					
					<input type="hidden" name="command" value="UPDATE" />
		
					<input type="hidden" name="studentId" value="${THE_STUDENT.id}" />
					
					<table>
					
						<tbody>
						
							<tr>
							
							<td> <label>First name :</label> </td>
							<td> <input type="text" name="firstName"
										value="${THE_STUDENT.firstNameString }"/> </td>
							
							</tr>
						
							<tr>
							
							<td> <label>Last name :</label> </td>
							<td> <input type="text" name="lastName"
							value="${THE_STUDENT.lastNameString }"/> </td>
							
							</tr>
							
							
							<tr>
							
							<td> <label>Email :</label> </td>
							<td> <input type="text" name="email"
							value="${THE_STUDENT.emailString }"/> </td>
							
							</tr>
							
							<tr>
							
							<td> <label></label> </td>
							<td> <input type="submit" value="Save" class="alert alert-primary"/> </td>
							
							</tr>
							
							
							
						</tbody>
					
					
					</table>
				
				</form>
				
				
				<div style="clear:both;"> </div>
			
				<p>
				
					<a href="StudentControllerServlet"> Back to List</a>
				</p>
			</div>
	
	</div>


</body>
</html>