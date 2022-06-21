package com.example.spring_security.dao;

import com.example.spring_security.model.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);


}
