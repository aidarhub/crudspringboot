package com.example.crudspringboot.service;

import com.example.crudspringboot.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> listRole();

    Optional<Role> getRoleById(Long id);

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);

    Role getRoleByName(String role);
}
