package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class updateEmployeeDemo {
	
	public static void main (String[] args) {
		 
		SessionFactory sf = new Configuration()
				.configure()
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session s = sf.getCurrentSession();
		
		try {
			
			//UPDATE SPECIFIC EMPLOYEE
			s.beginTransaction();
			Employee e = s.get(Employee.class, 1);
			e.setFirstName("Tessa");
			
			//UPDATE A LIST OF EMPLOYEES
			s.createQuery("UPDATE Employee e set e.company='Google' WHERE e.company = 'Samsung'").executeUpdate();
			s.getTransaction().commit();
		} finally {
			s.close();
		}		
	}
}
