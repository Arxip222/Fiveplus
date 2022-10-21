package com.fiveplus.platform.controller;

import com.fiveplus.platform.model.User;
import com.fiveplus.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id){
        return userService.updateUsr(user, id);
    }

    @GetMapping("/getCurrentUser")
    public User getCurrentUser(){
        return userService.getCurrentUsr();
    }
}