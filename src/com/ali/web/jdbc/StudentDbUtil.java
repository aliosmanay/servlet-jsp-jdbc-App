package com.ali.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

public class StudentDbUtil {
	
	private DataSource dataSource;
	private Connection myConnection= null;
	private Statement myStatement=null;
	private ResultSet myResultSet=null;
	private PreparedStatement myPreparedStatement=null;
	
	
	public StudentDbUtil(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	
	public List<Student> getStudents() throws Exception{
		
		List<Student> students= new ArrayList<Student>();
		
		
		
		try {
			// step 1 get a connection
			myConnection=dataSource.getConnection();
			
			// step 2 create sql statemant
			
			String sqlString="select * from student order by last_name ";
			myStatement=myConnection.createStatement();
			
			// execute query
			
			myResultSet=myStatement.executeQuery(sqlString);
			
			// preocess result set
			
			while (myResultSet.next()) {
				int id =myResultSet.getInt("id");
				String firstNameString=myResultSet.getString("first_name");
				String lastNameString=myResultSet.getString("last_name");
				String emailString=myResultSet.getString("email");
				
				students.add(new Student(id,firstNameString, lastNameString, emailString));
				
				
				
				
			}
			
				return students;
			
			
		}
		finally {
			//close JDBC object
			
			close(myConnection,myStatement,myResultSet);
		}
		
		
	}


	private void close(Connection myConnection, Statement myStatement, ResultSet myResultSet) {
		
		try {
			if(myResultSet!=null) {
				myResultSet.close();
			}
			
			if(myStatement !=null) {
				myStatement.close();
			}
			
			if(myConnection!=null) {
				myConnection.close();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public void addStudent(Student theStudent) throws Exception {
		
		try {
			//get db connection
			myConnection=dataSource.getConnection();
			
			//create sql for insert
			String sqlString="insert into student"
					+"(first_name,last_name,email)"
					+"values (?,?,?)";
			
			myPreparedStatement=myConnection.prepareStatement(sqlString);
			//set the param values for the student
			
			myPreparedStatement.setString(1,theStudent.getFirstNameString());
			myPreparedStatement.setString(2,theStudent.getLastNameString());
			myPreparedStatement.setString(3,theStudent.getEmailString());
			
			
			
			//execute sql insert
			myPreparedStatement.execute();
		
			
		} 
		finally {
			//clean up jdbc
			close(myConnection,myPreparedStatement,null);
			
		}
		
		
		
	}


	public Student getStudent(String studentIdString) throws Exception {
		Student student=null;
		try {
			myConnection=dataSource.getConnection();
			String sqlString="select * from student where id=?";
			
			myPreparedStatement=myConnection.prepareStatement(sqlString);
			myPreparedStatement.setInt(1, Integer.parseInt(studentIdString));
			myResultSet=myPreparedStatement.executeQuery();
			
			if(myResultSet.next()) {
				int id=myResultSet.getInt("id");
				String firstNameString=myResultSet.getString("first_name");
				String lastNameString=myResultSet.getString("last_name");
				String emailString=myResultSet.getString("email");
			 student= new Student(id,firstNameString, lastNameString, emailString);
			}else {
				throw new Exception("could not find student id" + studentIdString);
			}
			
			
			
			
			return student;
			
		} finally {
			close(myConnection,myPreparedStatement,myResultSet);
		}
		
		
	}


	public void updateStudent(Student student) throws Exception{
		
		try {
			myConnection=dataSource.getConnection();
			
			String sqlString  = "update student "
					+ "set first_name=?, last_name=?, email=? "
					+ "where id=?";
			
			
			myPreparedStatement=myConnection.prepareStatement(sqlString);
			myPreparedStatement.setString(1, student.getFirstNameString());
			myPreparedStatement.setString(2, student.getLastNameString());
			myPreparedStatement.setString(3, student.getEmailString());
			myPreparedStatement.setInt(4, student.getId());
			
			myPreparedStatement.execute();
			
			
		} finally {
			close(myConnection,myPreparedStatement,null);
		}
		
		
	}


	public void deleteStudent(int id) throws Exception{
		
		try {
			myConnection=dataSource.getConnection();
			
			String sqlString="delete from student where id=?";
			
			myPreparedStatement=myConnection.prepareStatement(sqlString);
			myPreparedStatement.setInt(1, id);
			myPreparedStatement.execute();
			
			
		} finally {
			close(myConnection,myPreparedStatement,null);
		}
		
		
	}
	
	
	
	

}
