package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
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
			//int instructorId= createInstructor(appDAO);
			//Instructor newInstructor = findInstructorById(appDAO, instructorId);
			//deleteInstructor(appDAO, instructorId);
			//createInstructorAndCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//FindCoursesForInstructor(appDAO);
			//FindInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructorById(appDAO);
			deleteCourseById(appDAO);

		};
	}

	private void deleteCourseById(AppDAO appDAO) {
		int id = 16;
		appDAO.deleteCourseById(id);
		System.out.println("Course "+ id + " deleted!");
	}

	private void deleteInstructorById(AppDAO appDAO) {
		int id = 9;
		appDAO.deleteInstructorById(9);
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;
		Course c = appDAO.findCourseById(id);
		c.setTitle("Enjoy simple things");
		appDAO.update(c);
		System.out.println("Course with id "+ id + " Updated!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 2;
		Instructor instructor = appDAO.findInstructorById(id);
		instructor.setFirstName("Louise");

		appDAO.update(instructor);
		System.out.println("Instructor with id "+ id + " Updated!");
	}

	private void FindInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 2;
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("Instructor Join Fetch: "+ instructor);
		System.out.println("Courses Join Fetch: " + instructor.getCourses());
	}

	private void FindCoursesForInstructor(AppDAO appDAO) {
		int id = 2;
		Instructor instructor = appDAO.findInstructorById(id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);
		System.out.println("Instructor: "+ instructor);
		System.out.println("Courses: " + instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 2;
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: "+ instructor);
		System.out.println("Courses: "+ instructor.getCourses());
	}

	private void createInstructorAndCourses(AppDAO appDAO) {

		Instructor newInstructor = new Instructor("John", "Doe", "jdoe@test.com");
		InstructorDetail detail = new InstructorDetail("http://www.youtube.com", "Fortnite");
		newInstructor.setInstructorDetail(detail);

		Course c1 = new Course("Javascript developer 2");
		Course c2 = new Course("SpringBoot Course 2");

		newInstructor.add(c1);
		newInstructor.add(c2);

		appDAO.save(newInstructor);
	}

	private void deleteInstructor(AppDAO appDAO, int instructorId) {

		System.out.println("Deleting instructor with Id: "+ instructorId);
		appDAO.delete(instructorId);

		System.out.println("Instructor with id " + instructorId + " deleted successfully");

	}

	private Instructor findInstructorById(AppDAO appDAO, int instructorId) {
		Instructor  foundInstructor = appDAO.findInstructorById(instructorId);
		System.out.println("Instructor with id "+instructorId +" found: "+ foundInstructor);

		return foundInstructor;
	}

	private int createInstructor(AppDAO appDAO) {
		Instructor testInstructor = new Instructor("John", "Doe", "jdoe@test.com");
		InstructorDetail detail = new InstructorDetail("http://www.youtube.com", "Fortnite");
		testInstructor.setInstructorDetail(detail);

		System.out.println("Saving Instructor: "+ testInstructor);
		int instructorId = appDAO.save(testInstructor);

		return instructorId;
	}


}
