package com.example.crudspringboot.service;

import com.example.crudspringboot.dao.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.crudspringboot.model.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Role> listRole() {
        return roleRepository.listRole();
    }

    public Role getRoleById(Long id) {
        Role role = null;
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            role = optionalRole.get();
        }

        return role;
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleByRole(String role) {
        return roleRepository.findRoleByRole(role);
    }
}
