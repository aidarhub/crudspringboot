package com.example.crudspringboot.model;

import com.example.crudspringboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class RoleConverter implements Converter<String, Role> {

    @Autowired
    private RoleService roleService;

    public Role convert(String id) {
        Long parseId = Long.parseLong(id);

        return roleService.getRoleById(parseId);
    }
}
