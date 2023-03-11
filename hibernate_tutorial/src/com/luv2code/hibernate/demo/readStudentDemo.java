package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class readStudentDemo {

	public static void main(String[] args) {
		
		
		//Create session Factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		//Retrieve the session from the factory
		Session session = factory.getCurrentSession();
		
		try {
			//CREATE STUDENT ON DB
			
			//create a Student object
			Student newStudent = new Student ("Test", "Muffin", "test@luv2code.com");
			
			//Start a transaction
			session.beginTransaction();
			
			//save the student
			session.save(newStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			//READ STUDENT FROM DB
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Retrieve the new created user id
			System.out.println("New Student id: "+ newStudent.getId());
			
			//Retrieve the information of the user
			Student studentInfo = session.get(Student.class, newStudent.getId());
			
			System.out.println("Student: " + studentInfo);
			
			session.getTransaction().commit();
			
			
			//READ A LIST OF STUDENTS THAT MEET A CONDITION
			
			session = factory.getCurrentSession();
			
			//Start a transaction
			session.beginTransaction();
			
			//Retrieve the list of students.
			List<Student> students = session.createQuery("from Student").getResultList();
			
			displayStudents(students);
			
			System.out.println("\n Students with lastName Doe");
			//Retrieve the list of students with lastName Doe.
			students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			displayStudents(students);
			
			//commit the transaction
			session.getTransaction().commit();
			
		} finally {
			
			factory.close();
		}

	}
	
	private static void displayStudents(List<Student> students) {
		for(Student s : students) {
			System.out.println(s);
		}
	}

}
