package com.fiveplus.platform.controller;

import com.fiveplus.platform.helpModels.StartProfile;
import com.fiveplus.platform.model.User;
import com.fiveplus.platform.service.PublicService;
import com.fiveplus.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PublicService publicService;

    @PutMapping("/startUpdateForm/{id}")
    public ResponseEntity<User> startUpdateUser(@RequestBody StartProfile data, @PathVariable("id") Long id){
        return userService.startUpdateUsr(data, id);
    }

    @PutMapping("/editProfile/{id}")
    public ResponseEntity<User> editUpdateUser(@RequestBody User user, @PathVariable("id") Long id){
        return userService.editProfile(user, id);
    }

    @GetMapping("/getCurrent")
    public User getCurrentUser(){
        return publicService.getCurrentUsr();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

}