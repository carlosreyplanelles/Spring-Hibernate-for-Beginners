package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import com.example.cruddemo.entity.Review;
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
			//createCourseWithReviews(appDAO);
			//retrieveCourseAndReviews(appDAO);
			deleteCourseAndReviews(appDAO);
		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		//This call will delete the course and the reviews associated due to the cascading configuration.
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

	private void createCourseWithReviews(AppDAO appDAO) {

		//Create the course
		Course course = new Course("SpringBoot and Hibernate Course");

		//Create the reviews
		Review r1 = new Review("This was an incredible course i learnt a lot about developing APIs and databases");
		Review r2 = new Review("The course was too basic. Don't waste your time and money");

		//Add reviews to the course
		course.addReview(r1);
		course.addReview(r2);

		//Save the Course
		appDAO.saveCourse(course);
		System.out.println("Course saved");
	}
}
