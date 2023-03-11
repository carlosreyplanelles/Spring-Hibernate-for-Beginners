package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;
import com.luv2code.hibernate.demo.entity.Student;

public class readEmployeeDemo {
	
	public static void main (String[] args) {
		 
		SessionFactory sf = new Configuration()
				.configure()
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session s = sf.getCurrentSession();
		
		try {
			s.beginTransaction();
			
			//READ 1 EMPLOYEE DATA FROM OBJECT
			Employee e = new Employee("Maria", "Hill", "Shield");
			s.save(e);
			s.getTransaction().commit();
			
			//get the information for the recently created employee
			s = sf.getCurrentSession();
			s.beginTransaction();
			Employee createdEmployee = s.get(Employee.class, e.getId());
			
			System.out.println("\nEmploye created: "+ createdEmployee.toString());
			
			//QUERY A LIST OF EMPLOYEES

			List<Employee> employees = s.createQuery("FROM Employee e WHERE e.company='Shield'").getResultList();
			
			System.out.println("\nShield employees's list");
			displayEmployees(employees);	
			
		} finally {
			s.close();
		}		
	}
	
	private static void displayEmployees(List<Employee> employees) {
		for(Employee e : employees) {
			System.out.println(e);
		}
	}
}
