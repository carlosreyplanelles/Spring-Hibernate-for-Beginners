package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;

public interface AppDAO {

    Instructor save(Instructor instructor);
    Instructor findInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void delete(int id);
    void deleteInstructorDetailByid(int id);
}
