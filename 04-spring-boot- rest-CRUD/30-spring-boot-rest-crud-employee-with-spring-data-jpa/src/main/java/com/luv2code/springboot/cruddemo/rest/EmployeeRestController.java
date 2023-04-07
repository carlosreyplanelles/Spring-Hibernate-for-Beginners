package com.luv2code.springboot.cruddemo.rest;


import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    //Constructor Injection
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //expose /employee endpoint
    @GetMapping("/employees")
    public List<Employee> getAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId){
        Employee e = employeeService.findById(employeeId);
        if(e==null){
            throw new RuntimeException("Employee not found. Id - "+ employeeId);
        }
        return e;
    }

    @PostMapping("/employees")
    public Employee AddEmployee(@RequestBody Employee e){

        //Set the id to 0 to make sure that the request creates a new employee even if an id is recieved.
        e.setId(0);
        Employee dbEmployee = employeeService.save(e);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee e){
        Employee dbEmployee = employeeService.save(e);

        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee e =employeeService.findById(employeeId);
        if(e==null){
            throw new RuntimeException("Employee not found id - "+employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Employee deleted id - "+ employeeId;
    }
}
