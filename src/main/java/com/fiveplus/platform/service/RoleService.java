package com.fiveplus.platform.service;


import com.fiveplus.platform.model.Role;
import com.fiveplus.platform.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;

    public Role addRole(Role role) {
        return roleRepo.save(role);
    }

    public List<Role> getRoleWithName(String role) {
        return roleRepo.findByName(role);
    }
}
