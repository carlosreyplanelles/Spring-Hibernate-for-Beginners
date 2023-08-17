package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }


    @Override
    @Transactional
    public int save(Instructor instructor) {
        entityManager.persist(instructor);
        return instructor.getId();
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :instructorId", Course.class);
        query.setParameter("instructorId", instructorId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int instructorId) {

        TypedQuery<Instructor> query = entityManager.createQuery( "SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id = :instructorId", Instructor.class);
        query.setParameter("instructorId", instructorId);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void delete(int id) {
        Instructor instructor= entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        List<Course> courses = instructor.getCourses();

        //Break the relationship between courses and instructors
        for(Course course : courses){
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

}
