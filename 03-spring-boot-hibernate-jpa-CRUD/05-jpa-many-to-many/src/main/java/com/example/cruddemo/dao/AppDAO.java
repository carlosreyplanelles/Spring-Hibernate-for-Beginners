package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.Student;

import java.util.List;

public interface AppDAO {

    int save(Instructor instructor);
    Instructor findInstructorById(int id);
    List<Course> findCoursesByInstructorId(int instructorId);
    Instructor findInstructorByIdJoinFetch(int instructorId);
    void delete(int id);
    void update(Instructor instructor);
    Course findCourseById(int id);
    void update(Course course);
    void deleteInstructorById(int id);
    void deleteCourseById(int id);
    void saveCourse(Course course);
    Course findCourseAndReviewsByCourseId(int id);
    public Course findCourseAndStudentsByCourseId(int id);
    public Student findStudentAndCoursesByStudentId(int id);
    void updateStudent(Student student);
    void updateCourse(Course course);
    void deleteStudentById(int id);

}
