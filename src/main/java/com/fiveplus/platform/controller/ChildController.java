package com.fiveplus.platform.controller;

import com.fiveplus.platform.model.Child;
import com.fiveplus.platform.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/children")
public class ChildController {

    @Autowired
    ChildService childService;

    @PostMapping("/addChild")
    public ResponseEntity<Child> addChild(@RequestBody Child child){
        childService.saveChild(child);
        return new ResponseEntity<Child>(HttpStatus.OK);
    }

    @PutMapping("/editChild/{id}")
    public HttpEntity<Child> editChild(@RequestBody Child child, @PathVariable("id") Long id){
        return childService.editChild(child, id);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Child> getChildById(@PathVariable("id") Long id){
        return childService.getChildById(id);
    }
}