package com.fiveplus.platform.controller;


import com.fiveplus.platform.model.LoginData;
import com.fiveplus.platform.model.User;
import com.fiveplus.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public User login(@RequestBody LoginData loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userService.getCurrentUsr();
    }

    @PostMapping("/registerParent")
    public User createUserParent(@RequestBody LoginData loginDto){
        return userService.registerUsrParent(loginDto);
    }

    @PostMapping("/registerTeacher")
    public User createUserTeacher(@RequestBody LoginData loginDto){
        return userService.registerUsrTeacher(loginDto);
    }

    //Для выхода из аккаунта используется get запрос к /logout
}