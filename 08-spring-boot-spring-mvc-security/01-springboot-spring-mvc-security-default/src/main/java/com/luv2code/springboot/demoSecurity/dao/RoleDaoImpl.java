package com.luv2code.springboot.demoSecurity.dao;

import com.luv2code.springboot.demoSecurity.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao{


    private EntityManager entityManager;

    @Autowired
    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String roleName) {

        TypedQuery<Role> query = entityManager.createQuery("from Role where name=:roleName", Role.class);
        query.setParameter("roleName", roleName);

        Role role;
        try{
            role = query.getSingleResult();
            return role;
        } catch (Exception e){
            role = null;
        }

        return role;
    }
}
