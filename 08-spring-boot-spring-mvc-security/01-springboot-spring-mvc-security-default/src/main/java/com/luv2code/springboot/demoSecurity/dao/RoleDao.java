package com.luv2code.springboot.demoSecurity.dao;

import com.luv2code.springboot.demoSecurity.entity.Role;

public interface RoleDao {

    public Role findRoleByName(String roleName);

}
