package com.luv2code.springboot.demoSecurity.dao;

import com.luv2code.springboot.demoSecurity.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImpl implements UserDao{


    private EntityManager entityManager;
    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUsername(String userName) {
        TypedQuery<User> query  =entityManager.createQuery("FROM User WHERE username=:userName", User.class);
        query.setParameter("userName", userName);

        User registeredUser;

        try{
            registeredUser = query.getSingleResult();
        } catch (Exception e){
            registeredUser = null;
        }
        return registeredUser;
    }

    @Override
    @Transactional
    public int save(User user) {
        User u = entityManager.merge(user);
        return u.getId();
    }
}
