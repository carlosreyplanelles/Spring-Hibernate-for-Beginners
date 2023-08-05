package com.luv2code.springboot.demoSecurity.service;

import com.luv2code.springboot.demoSecurity.entity.User;
import com.luv2code.springboot.demoSecurity.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User findByUsername(String userName);
    public int save(WebUser webUser);

}
