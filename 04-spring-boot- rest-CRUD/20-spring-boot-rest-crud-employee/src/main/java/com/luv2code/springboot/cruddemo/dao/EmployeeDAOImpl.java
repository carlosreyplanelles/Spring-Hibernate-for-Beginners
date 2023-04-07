package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        //Create query
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);

        //Execute query and get result list
        List<Employee> employees = query.getResultList();

        //return result
        return employees;
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee e) {
        //If the id of the employee is 0 merge will consider this a new employee and will add a new employee to the database.
        //If is different than 0 will update the employee with the provided Id.
        Employee updatedEmployee = entityManager.merge(e);
        return updatedEmployee;
    }

    @Override
    public void deleteById(int id) {
        Employee e = entityManager.find(Employee.class, id);
        entityManager.remove(e);
    }
}
