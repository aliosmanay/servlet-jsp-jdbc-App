package com.ali.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.sun.xml.internal.ws.encoding.DataHandlerDataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//define datasource conection pool for resource injection
	
	@Resource(name="jdbc/web-student-tracker")
	private DataSource dataSource;
	
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//step1 set up the printwriter
		
		PrintWriter printWriter=response.getWriter();
		response.setContentType("text/plain");
		
		
		
		//step2 get a conection to database 
		Connection myConnection=null;
		Statement myStatement=null;
		ResultSet myResultSet=null;
		
		
		try {
			myConnection=dataSource.getConnection();
			
			
			// step3 create a sql statements
			String sqlString ="select * from student";
			
			myStatement=myConnection.createStatement();
			
			
			//step 4 execute sql query
			
			myResultSet=myStatement.executeQuery(sqlString);
			
			//step 5 process the result set
			
			while(myResultSet.next()) {
				String email=myResultSet.getString("email");
				printWriter.println(email);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
