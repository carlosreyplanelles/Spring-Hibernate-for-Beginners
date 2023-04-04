package com.luv2code.demo.rest;


import com.luv2code.demo.entity.Student;
import com.luv2code.demo.exception.StudentErrorResponse;
import com.luv2code.demo.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;
    @PostConstruct
    public void init(){
        students = new ArrayList<>();
        students.add(new Student("Max", "Power"));
        students.add(new Student("John", "Constantine"));
        students.add(new Student("Antonio", "Constantine"));
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudents(@PathVariable int studentId){

        if(studentId >= students.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found - "+ studentId);
        }
        return students.get(studentId);
    }


    //StudentNotFoundException exception handler

}
