package com.ali.web.jdbc;

public class Student {
	
	private int id;
	private String firstNameString;
	private String lastNameString;
	private String emailString;
	
	
	
	
	
	public Student(String firstNameString, String lastNameString, String emailString) {
		this.firstNameString = firstNameString;
		this.lastNameString = lastNameString;
		this.emailString = emailString;
	}





	public Student(int id, String firstNameString, String lastNameString, String emailString) {
		this.id = id;
		this.firstNameString = firstNameString;
		this.lastNameString = lastNameString;
		this.emailString = emailString;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getFirstNameString() {
		return firstNameString;
	}





	public void setFirstNameString(String firstNameString) {
		this.firstNameString = firstNameString;
	}





	public String getLastNameString() {
		return lastNameString;
	}





	public void setLastNameString(String lastNameString) {
		this.lastNameString = lastNameString;
	}





	public String getEmailString() {
		return emailString;
	}





	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}





	@Override
	public String toString() {
		return "Student [id=" + id + ", firstNameString=" + firstNameString + ", lastNameString=" + lastNameString
				+ ", emailString=" + emailString + "]";
	}
	
	
	
	

}
