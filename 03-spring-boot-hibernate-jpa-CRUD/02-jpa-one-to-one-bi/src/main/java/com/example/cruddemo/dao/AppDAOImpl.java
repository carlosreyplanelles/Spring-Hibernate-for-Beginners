package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }


    @Override
    @Transactional
    public Instructor save(Instructor instructor) {
        entityManager.persist(instructor);
        return instructor;
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Instructor instructor= entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailByid(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        //Break bi-directional link between instructor detail and the instructor
        Instructor instructor = instructorDetail.getInstructor();
        instructor.setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }
}
