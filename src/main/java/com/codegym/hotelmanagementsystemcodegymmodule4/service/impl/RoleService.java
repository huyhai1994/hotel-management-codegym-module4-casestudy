package com.codegym.hotelmanagementsystemcodegymmodule4.service.impl;

import com.codegym.hotelmanagementsystemcodegymmodule4.entity.Role;
import com.codegym.hotelmanagementsystemcodegymmodule4.repository.IRoleRepository;
import com.codegym.hotelmanagementsystemcodegymmodule4.service.interfac.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
