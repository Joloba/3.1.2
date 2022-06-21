package com.example.spring_security.service;

import com.example.spring_security.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> getAllRoles();

    public Role getRoleByName(String name);
}
