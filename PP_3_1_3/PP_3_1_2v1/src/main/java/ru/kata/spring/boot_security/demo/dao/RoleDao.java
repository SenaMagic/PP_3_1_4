package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> roleList();
    void addRole(Role role);
    Role getRoleById(Long id);
}
