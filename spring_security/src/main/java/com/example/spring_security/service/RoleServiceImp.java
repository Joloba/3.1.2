package com.example.spring_security.service;

import com.example.spring_security.dao.RoleDaoHibernateImpl;
import com.example.spring_security.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {


    private final RoleDaoHibernateImpl roleDao;

    public RoleServiceImp(RoleDaoHibernateImpl roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
