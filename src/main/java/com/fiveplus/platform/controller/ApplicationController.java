package com.fiveplus.platform.controller;

import com.fiveplus.platform.model.Application;
import com.fiveplus.platform.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @PostMapping("/addApplications")
    public ResponseEntity<Application> addApplication(@RequestBody Application application) {
        return applicationService.addApplication(application);
    }

    @PutMapping("/editApplication/{id}")
    public ResponseEntity<Application> editApplications(@RequestBody Application application, @PathVariable("id") Long id) {
        return applicationService.editApplication(application, id);
    }
}

