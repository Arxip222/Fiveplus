package com.fiveplus.platform.controller;

import com.fiveplus.platform.model.User;
import com.fiveplus.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id){
        return userService.updateUsr(user, id);
    }

    @GetMapping("/getCurrent")
    public User getCurrentUser(){
        return userService.getCurrentUsr();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

}