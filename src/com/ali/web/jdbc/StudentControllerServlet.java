package com.ali.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web-student-tracker")
	private DataSource dataSource;
	
	
	
  
    @Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			studentDbUtil = new StudentDbUtil(dataSource);
			
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		
	}


	public StudentControllerServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		
		try {
			//read the "command" parameter
			
			String theCommand=request.getParameter("command") ;
			
			//if the command is missing then default to listing students
			
			if(theCommand==null) {
				theCommand="LIST";
			}
			//route to the appropriate method
			switch (theCommand) {
			case "LIST":
				//list students ... in MVC
				listStudents(request,response);
				break;
			case "ADD":
				addStudent(request,response);
				break;
			case "LOAD":
				loadStudent(request,response);
				break;
			case "UPDATE":
				updateStudent(request,response);
				break;
			case "DELETE":
				deleteStudent(request,response);
				

			default:
				listStudents(request,response);
				
			}
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}


	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int id = Integer.parseInt(request.getParameter("studentId"));
		
		studentDbUtil.deleteStudent(id);
		
		listStudents(request, response);
		
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read student info from the form data
		
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		
		// crate a new student object
		
		Student student=new Student(id,firstName, lastName, email);
		
		
		
		//perfom update database 
		
		studentDbUtil.updateStudent(student);
		//send them back to the list students page
		listStudents(request, response);
		
	}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read student id from form data 
		String studentIdString=request.getParameter("studentId");
		
		
		// get student from database (db util)
		Student theStudent= studentDbUtil.getStudent(studentIdString);
		
		
		//place student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		
		
		//send to jsp page : update-student-form
		RequestDispatcher dispatcher=request.getRequestDispatcher("update-student-form.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read student info from form data
		String firstName= request.getParameter("firstName");
		String lastName= request.getParameter("lastName");
		String email= request.getParameter("email");
		
		
		// create new student object
		Student theStudent= new Student(firstName, lastName, email);
		
		// add the database
		
		studentDbUtil.addStudent(theStudent);
		// send to main page
		listStudents(request, response);
		
		
		
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) 
	
	throws Exception{
	
		List<Student> students=studentDbUtil.getStudents();
		
		request.setAttribute("STUDENT_LIST", students);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
