package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Instructor;
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
    public int save(Instructor instructor) {
        entityManager.persist(instructor);
        return instructor.getId();
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Instructor instructor= entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
    }
}
