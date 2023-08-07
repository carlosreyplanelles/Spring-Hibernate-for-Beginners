package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Instructor;

public interface AppDAO {

    int save(Instructor instructor);
    Instructor findInstructorById(int id);

    void delete(int id);
}
