package com.fiveplus.platform.service;

import com.fiveplus.platform.exception.ResourceNotFoundException;
import com.fiveplus.platform.model.LoginData;
import com.fiveplus.platform.model.Role;
import com.fiveplus.platform.model.User;
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
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }



    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public ResponseEntity<User> updateUsr(User user, Long id){
        try {
            User usr = userRepository.findById(id).get();
            usr.setFirstName(user.getFirstName());
            usr.setLastName(user.getLastName());
            usr.setFullName(user.getFullName());
            usr.setEmail(user.getEmail());
            userRepository.save(usr);
            return new ResponseEntity<User>(usr, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteUsr(User user){
        userRepository.delete(user);
    }

    public User getCurrentUsr(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     return  userRepository.findByEmail(authentication.getName()).stream().findFirst().get();
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

}
