package com.fiveplus.platform.controller;


import com.fiveplus.platform.model.LoginData;
import com.fiveplus.platform.model.User;
import com.fiveplus.platform.repository.RoleRepo;
import com.fiveplus.platform.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public LoginData login(@RequestBody LoginData loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return loginDto;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody LoginData loginDto){
        User newUser = new User();
        newUser.setEmail(loginDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        return userRepository.save(newUser);
    }
}