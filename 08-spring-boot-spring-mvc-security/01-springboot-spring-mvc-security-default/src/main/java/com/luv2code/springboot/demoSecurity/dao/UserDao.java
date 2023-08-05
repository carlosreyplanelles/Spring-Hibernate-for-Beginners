package com.luv2code.springboot.demoSecurity.dao;

import com.luv2code.springboot.demoSecurity.entity.User;

public interface UserDao {

    User findByUsername (String userName);
    int save (User user);
}
