package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner ->{
			int studentId = createStudent(studentDAO);
			readStudent(studentDAO, studentId);
			readAllStudents(studentDAO);
			ReadByLastName(studentDAO, "Martinez");
			updateStudent(studentDAO, "Duffy", studentId);
			deleteStudent(studentDAO, studentId);
		};
	}




	private int createStudent(StudentDAO studentDAO) {

		System.out.println("Creating new Student...");
		Student student = new Student("Rodrigo", "Martinez","r.martinez@luv2code.com");

		System.out.println("Saving student...");
		studentDAO.save(student);

		System.out.println("Student created. Id generated: "+ student.getId());
		return student.getId();
	}

	private void readStudent(StudentDAO studentDAO, int studentId) {
		System.out.println("Retrieving Student with id " + studentId + "...");
		System.out.println(studentDAO.findById(studentId));
	}

	private void readAllStudents(StudentDAO studentDAO){
		System.out.println("Retrieving all the students...");
		List<Student> students = studentDAO.findAll();
		for(Student s: students){
			System.out.println(s);
		}
	}

	private void ReadByLastName(StudentDAO studentDAO, String lastName) {
		System.out.println("Retrieving students with last name "+ lastName + "...");
		List<Student> students = studentDAO.findByLastName(lastName);
		for(Student s: students) {
			System.out.println(s);
		}
	}

	private void updateStudent(StudentDAO studentDAO, String firstName, int studentId) {

		System.out.println("Retrieving student with id "+ studentId);
		Student s = studentDAO.findById(studentId);
		System.out.println("Updating student firstName to "+ firstName);
		s.setFirstName(firstName);
		studentDAO.update(s);
		System.out.println(studentDAO.findById(studentId));
	}

	private void deleteStudent(StudentDAO studentDAO, int studentId) {
		System.out.println("Deleting student with id "+ studentId);
		studentDAO.delete(studentId);
	}
}
