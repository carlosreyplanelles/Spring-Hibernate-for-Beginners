package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
	
	public static void main(String[] args ) {
		String dbUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL = false";
		String user = "hbstudent";
		String pass = "hbstudent";
		
		try {
			
			Connection conn = DriverManager.getConnection(dbUrl,user,pass);
			
			System.out.println("Connection Successful");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
