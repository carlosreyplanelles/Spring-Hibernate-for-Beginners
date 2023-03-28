package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> students = entityManager.createQuery("FROM Student", Student.class);
        return students.getResultList();
    }

    @Override
    public List<Student> findByLastName(String data) {
        TypedQuery<Student> students = entityManager.createQuery("FROM Student WHERE lastName=:lastName", Student.class);
        students.setParameter("lastName", data);
        return students.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Student s = entityManager.find(Student.class, id);
        entityManager.remove(s);
    }

    /*UPDATE A LIST OF STUDENTS THAT MEET A CONDITION
    public List<Student> UpdateyLastName(String data) {
        TypedQuery<Student> students = entityManager.createQuery("UPDATE FROM Student WHERE lastName='Doe' SET lastName=:lastName", Student.class).executeUpdate();
        students.setParameter("lastName", data);
        return students.getResultList();
    }

    DELETE A LIST OF STUDENTS THAT MEET A CONDITION
    public void deleteByLastName(String data) {
        TypedQuery<Student> students = entityManager.createQuery("DELETE FROM Student WHERE lastName=:lastName", Student.class).executeUpdate();
        students.setParameter("lastName", data);
    }
    */
}
