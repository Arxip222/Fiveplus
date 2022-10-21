package com.fiveplus.platform.service;


import com.fiveplus.platform.exception.ResourceNotFoundException;
import com.fiveplus.platform.model.Application;
import com.fiveplus.platform.model.Child;
import com.fiveplus.platform.repository.ApplicationRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    UserService userService;

    public ResponseEntity<Application> addApplication(Application application) {
        applicationRepo.save(application);
        return new ResponseEntity<Application>(HttpStatus.OK);
    }

    public ResponseEntity<Application> editApplication(Application application, Long id) {
        try {
            Application application1 = applicationRepo.findById(id).get();
            application1.setFinish_less(application.getFinish_less());
            application1.setFree(application.isFree());
            application1.setChild(application.getChild());
            application1.setParent(userService.getCurrentUsr());
            applicationRepo.save(application1);
            return new ResponseEntity<Application>(application1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Application>(HttpStatus.NOT_FOUND);
        }
    }
}
