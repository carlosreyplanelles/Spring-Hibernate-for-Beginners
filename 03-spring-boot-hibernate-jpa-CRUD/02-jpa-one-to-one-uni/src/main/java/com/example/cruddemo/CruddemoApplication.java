package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){

		return runner->{
			int instructorId= createInstructor(appDAO);
			findInstructorById(appDAO, instructorId);
			deleteInstructor(appDAO, instructorId);
		};
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
