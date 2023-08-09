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
			Instructor instructor= createInstructor(appDAO);
			//findInstructorById(appDAO, instructor.getId());
			findInstructorDetailById(appDAO, instructor.getInstructorDetail().getId());
			//deleteInstructor(appDAO, instructor.getId());
			deleteInstructorDetailById(appDAO, instructor.getInstructorDetail().getId());

		};
	}

	private void deleteInstructorDetailById(AppDAO appDAO, int id) {
		System.out.println("Deleting instructor detail with id: "+ id);
		appDAO.deleteInstructorDetailByid(id);
		System.out.println("Instructor detail and associated instructor deleted.");
	}

	private void findInstructorDetailById(AppDAO appDAO, int id) {

		System.out.println("Searching new Instructor detail with id " + id);
		InstructorDetail detail = appDAO.findInstructorDetailById(id);
		System.out.println("Instructor detail with id "+id+":"+detail);
		System.out.println("Associated instructor: "+detail.getInstructor());
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

	private Instructor createInstructor(AppDAO appDAO) {
		Instructor testInstructor = new Instructor("John", "Doe", "jdoe@test.com");
		InstructorDetail detail = new InstructorDetail("http://www.youtube.com", "Fortnite");
		testInstructor.setInstructorDetail(detail);

		System.out.println("Saving Instructor: "+ testInstructor);
		Instructor newInstructor = appDAO.save(testInstructor);

		return newInstructor;
	}


}
