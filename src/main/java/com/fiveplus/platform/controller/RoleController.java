package com.fiveplus.platform.controller;

import com.fiveplus.platform.model.Role;
import com.fiveplus.platform.repository.RoleRepo;
import com.fiveplus.platform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }
}

