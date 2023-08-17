package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.*;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner->{
			//createCourseWithStudents(appDAO);
			//retrieveCourseAndStudents(appDAO);
			//addCourseToStudent(appDAO);
			//addStudentToCourse(appDAO);
			deleteCourse(appDAO);
		};
	}

	private void addStudentToCourse(AppDAO appDAO) {
		Student s = new Student("Rodrigo", "Test", "r.test@gmail.com");
		int courseId = 10;
		Course c = appDAO.findCourseAndStudentsByCourseId(courseId);
		c.addStudent(s);

		appDAO.updateCourse(c);
	}

	private void addCourseToStudent(AppDAO appDAO) {
		int studentId = 1;
		int courseId = 13;
		Student s = appDAO.findStudentAndCoursesByStudentId(studentId);
		Course c = appDAO.findCourseById(courseId);
		s.addCourse(c);

		appDAO.updateStudent(s);
	}

	private void retrieveCourseAndStudents(AppDAO appDAO) {
		int id = 10;
		Course c = appDAO.findCourseAndStudentsByCourseId(id);

		System.out.println("Course: "+c);
		System.out.println("Students: "+ c.getStudents());
	}

	private void createCourseWithStudents(AppDAO appDAO) {

		//Create the course
		Course course = new Course("How to make your first million");

		//Create Students
		Student s1 = new Student("Juan", "Rodriguez", "j.rodrigued@gmail.com");
		Student s2 = new Student("John", "Kobra", "jkobra@test.com");

		course.addStudent(s1);
		course.addStudent(s2);

		//Save the Course
		appDAO.saveCourse(course);
		System.out.println("Course saved");
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;
		//This call will delete the course and the reviews associated due to the cascading configuration.
		//This call will also delete the associations between students and courses stored in the course_student relationship table.
		//This process wont remove all the students for the course. Just the association.
		appDAO.deleteCourseById(id);
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		//Retrieve the course with the reviews
		Course c = appDAO.findCourseAndReviewsByCourseId(id);
		//Print the course information
		System.out.println(c);
		//print the reviews
		System.out.println(c.getReviews());
	}
}
