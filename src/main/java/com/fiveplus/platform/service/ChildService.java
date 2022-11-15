package com.fiveplus.platform.service;


import com.fiveplus.platform.model.Child;
import com.fiveplus.platform.model.User;
import com.fiveplus.platform.repository.ChildRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildService {

    @Autowired
    ChildRepo childRepo;

    @Autowired
    UserService userService;
    @Autowired
    PublicService publicService;


    public ResponseEntity<Child> saveChild(Child child) {
        child.setParent(publicService.getCurrentUsr());
        childRepo.save(child);
        return new ResponseEntity<Child>(HttpStatus.OK);
    }

    public ResponseEntity<Child> editChild(Child child, Long id) {
        try {
            Child child1 = childRepo.findById(id).get();
            child1.setName(child.getName());
            child1.setPhoto(child.getPhoto());
            childRepo.save(child1);
            return new ResponseEntity<Child>(child1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Child>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Child> getChildById(Long id){
        try {
            return new ResponseEntity<Child>(childRepo.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Child>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Child>> getChildrenByParentId(Long id){
        User parent = userService.getUserById(id).getBody();
        if (parent != null) {
            return new ResponseEntity<List<Child>>(childRepo.findByParent(parent), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
