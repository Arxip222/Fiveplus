package com.fiveplus.platform.service;


import com.fiveplus.platform.model.Role;
import com.fiveplus.platform.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseEntity<Role> getRoleById(Long id){
        try {
            return new ResponseEntity<Role>(roleRepo.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
    }
}
