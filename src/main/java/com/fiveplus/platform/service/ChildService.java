package com.fiveplus.platform.service;


import com.fiveplus.platform.model.Child;
import com.fiveplus.platform.model.Purchase;
import com.fiveplus.platform.repository.ChildRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChildService {

    @Autowired
    ChildRepo childRepo;

    @Autowired
    UserService userService;


    public ResponseEntity<Child> saveChild(Child child) {
        child.setParent(userService.getCurrentUsr());
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

}
