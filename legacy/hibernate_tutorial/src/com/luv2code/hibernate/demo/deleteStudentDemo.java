package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class deleteStudentDemo {

	public static void main(String[] args) {
		
		
		//Create session Factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		//Retrieve the session from the factory
		Session session = factory.getCurrentSession();
		Integer studentId = 1;
		try {
			
			
			//READ STUDENT FROM DB
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//DELETE 1 STUDENT
			
			//Retrieve the information of the user
			Student studentInfo = session.get(Student.class, studentId);
			
			//Delete 1 student
			session.delete(studentInfo);
	
			System.out.println("Student: " + studentInfo);
			
			//Commit the transaction
			session.getTransaction().commit();
			
			//DELETE THE STUDENTS FROM A QUERY
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			session.createQuery("delete from Student s where s.id=3 ").executeUpdate();
			
			session.getTransaction().commit();
			
		} finally {
			
			factory.close();
		}

	}

}
