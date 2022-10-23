package com.fiveplus.platform.controller;

import com.fiveplus.platform.model.Application;
import com.fiveplus.platform.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @PostMapping("/addTableApplication")
    public ResponseEntity<Application> addTableApplication(@RequestBody Application application) {
        return applicationService.addTableApplication(application);
    }

    @PutMapping("/editApplication/{id}")
    public ResponseEntity<Application> editApplications(@RequestBody Application application, @PathVariable("id") Long id) {
        return applicationService.editApplication(application, id);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Application> getById(@PathVariable("id") Long id){
        return applicationService.getApplicationById(id);
    }

    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<List<Application>> getByUserId(@PathVariable("id") Long id){
        return applicationService.getByUserId(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Application> deleteApplicationById(@PathVariable ("id") Long id){
        try {
            applicationService.deleteApplicationById(id);
            return new ResponseEntity<Application>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<Application>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addMomentApplication")
    public ResponseEntity<Application> addMomentApplication(@RequestBody Application application){
        return applicationService.addMomentApplication(application);
    }

    @PutMapping("/stopLesson/{id}")
    public ResponseEntity<Application> stopLesson(@PathVariable Long id){
           return applicationService.stopLesson(id);
    }
}

