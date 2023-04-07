package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.dao.EmployeeRepository;
import com.luv2code.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;



    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee e = null;

        if(!result.isPresent()){
            throw new RuntimeException("Did not find employee employeeId - " + id);
        }
        e=result.get();
        return e;
    }

    @Override
    public Employee save(Employee e) {
        return employeeRepository.save(e);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
