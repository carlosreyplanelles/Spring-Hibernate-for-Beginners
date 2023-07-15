package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class deleteEmployeeDemo {
	
	public static void main (String[] args) {
		 
		SessionFactory sf = new Configuration()
				.configure()
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session s = sf.getCurrentSession();
		
		try {
			//DELETE ONE EMPLOYEE OBJECT FROM THE DATABASE
			s.beginTransaction();
			Employee e = new Employee("John", "Rodriguez", "Samsung");
			s.save(e);
			
			s.delete(e);
			
			//DELETE A LIST OF EMPLOYEES
			s.createQuery("delete from Employee e WHERE e.company = 'Google'").executeUpdate();
			s.getTransaction().commit();
			
		} finally {
			s.close();
		}		
	}
}
