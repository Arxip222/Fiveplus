package com.fiveplus.platform.service;

import com.fiveplus.platform.model.User;
import com.fiveplus.platform.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PublicService {

    @Autowired
    UserRepo userRepository;

    public User getCurrentUsr(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  userRepository.findByEmail(authentication.getName()).stream().findFirst().get();
    }
}
