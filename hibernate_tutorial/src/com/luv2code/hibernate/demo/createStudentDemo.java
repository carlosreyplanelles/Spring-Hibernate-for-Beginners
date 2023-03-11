package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class createStudentDemo {

	public static void main(String[] args) {
		
		
		//Create session Factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		//Retrieve the session from the factory
		Session session = factory.getCurrentSession();
		
		try {
			//create a Student object
			Student newStudent = new Student ("Jhon", "Doe", "jhon@luv2code.com");
			
			//Start a transaction
			session.beginTransaction();
			
			//save the student
			session.save(newStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
		} finally {
			
			factory.close();
		}

	}

}
