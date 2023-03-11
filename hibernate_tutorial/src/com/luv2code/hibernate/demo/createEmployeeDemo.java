package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class createEmployeeDemo {
	
	public static void main (String[] args) {
		 
		SessionFactory sf = new Configuration()
				.configure()
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session s = sf.getCurrentSession();
		
		try {
			s.beginTransaction();
			
			Employee e = new Employee("John", "Rodriguez", "Samsung");
			
			s.save(e);
			
			s.getTransaction().commit();
		} finally {
			s.close();
		}		
	}
}
