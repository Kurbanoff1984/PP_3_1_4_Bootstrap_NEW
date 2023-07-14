package ru.kata.spring.boot_security.demo.Service;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void addRole(Role role);
}
