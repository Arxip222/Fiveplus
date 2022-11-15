package com.fiveplus.platform.service;

import com.fiveplus.platform.helpModels.StartProfile;
import com.fiveplus.platform.model.*;
import com.fiveplus.platform.helpModels.LoginData;
import com.fiveplus.platform.repository.ChildRepo;
import com.fiveplus.platform.repository.PurchaseRepo;
import com.fiveplus.platform.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ChildRepo childRepo;
    private PurchaseService purchaseService;
    private PublicService publicService;

    @Autowired
    public UserService(UserRepo userRepository, RoleService roleService, @Lazy PasswordEncoder passwordEncoder, ChildRepo childRepo, PurchaseService purchaseService, PublicService publicService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.childRepo = childRepo;
        this.purchaseService = purchaseService;
        this.publicService = publicService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }



    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public ResponseEntity<User> startUpdateUsr(StartProfile data, Long id){
        try {
            User usr = userRepository.findById(id).get();
            usr.setFirstName(data.getParent().getFirstName());
            usr.setImage(data.getParent().getImage());
            usr.setLastName(data.getParent().getLastName());
            usr.setFullName(data.getParent().getFirstName() + " " + data.getParent().getLastName());
            usr.setPhoneNumber(data.getParent().getPhoneNumber());
            userRepository.save(usr);
            Child chl = new Child();
            chl.setName(data.getFirstChild().getName());
            chl.setParent(usr);
            chl.setKlass(data.getFirstChild().getKlass());
            childRepo.save(chl);
            Purchase pur1 = new Purchase(0, LessonType.NACHALKA, publicService.getCurrentUsr());
            Purchase pur2 = new Purchase(0, LessonType.HOMEWORK_DAY, publicService.getCurrentUsr());
            Purchase pur3 = new Purchase(0, LessonType.OST_MIN, publicService.getCurrentUsr());
            purchaseService.addPurchase(pur1);
            purchaseService.addPurchase(pur2);
            purchaseService.addPurchase(pur3);
            return new ResponseEntity<User>(usr, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<User> editProfile(User user, Long id){
        try {
            User usr = userRepository.findById(id).get();
            usr.setFirstName(user.getFirstName());
            usr.setLastName(user.getLastName());
            usr.setImage(user.getImage());
            usr.setFullName(user.getFirstName() + " " +user.getLastName());
            usr.setEmail(user.getEmail());
            usr.setImage(user.getImage());
            usr.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(usr);
            return new ResponseEntity<User>(usr, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteUsr(User user){
        userRepository.delete(user);
    }

    public User registerUsrParent(LoginData loginDto){
        User newUser = new User();
        newUser.setRoles(roleService.getRoleWithName("PARENT"));
        newUser.setEmail(loginDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        return userRepository.save(newUser);
    }

    public User registerUsrTeacher(LoginData loginDto){
        User newUser = new User();
        newUser.setRoles(roleService.getRoleWithName("TEACHER"));
        newUser.setEmail(loginDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        return userRepository.save(newUser);
    }

    public ResponseEntity<User> getUserById(Long id){
        try {
            return new ResponseEntity<User>(userRepository.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

}
