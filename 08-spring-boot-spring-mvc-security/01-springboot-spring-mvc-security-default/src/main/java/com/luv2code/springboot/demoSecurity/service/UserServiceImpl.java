package com.luv2code.springboot.demoSecurity.service;

import com.luv2code.springboot.demoSecurity.dao.RoleDao;
import com.luv2code.springboot.demoSecurity.dao.UserDao;
import com.luv2code.springboot.demoSecurity.entity.Role;
import com.luv2code.springboot.demoSecurity.entity.User;
import com.luv2code.springboot.demoSecurity.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String userName) {
        return userDao.findByUsername(userName);
    }

    @Override
    public int save(WebUser webUser) {
        User newUser = new User();


        //Assign the form information to our new user
        newUser.setUsername(webUser.getUserName());
        newUser.setFirstName(webUser.getFirstName());
        newUser.setLastName(webUser.getLastName());
        newUser.setEmail(webUser.getEmail());
        newUser.setPassword(passwordEncoder.encode(webUser.getPassword()));

        //Assign employee role by default
        newUser.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

        //Create the new user and return the id
        return userDao.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }

        Collection<SimpleGrantedAuthority> roles = mapRolesToAuthorities(user.getRoles());

        UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
        return details;
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role temprole : roles){
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(temprole.getName());
            authorities.add(tempAuthority);
        }

        return authorities;
    }
}
