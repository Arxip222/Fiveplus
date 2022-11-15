package com.fiveplus.platform.service;


import com.fiveplus.platform.utils.TypeConvertor;
import com.fiveplus.platform.model.Application;
import com.fiveplus.platform.model.LessonType;
import com.fiveplus.platform.model.User;
import com.fiveplus.platform.repository.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepo applicationRepo;

    @Autowired
    UserService userService;
    @Autowired
    PublicService publicService;

    @Autowired
    ChildService childService;

    public ResponseEntity<Application> addMomentApplication(Application application, Long child_id, LessonType type) {
        application.setFree(true);
        application.setParent(publicService.getCurrentUsr());
        application.setFinished(false);
        application.setChild(childService.getChildById(child_id).getBody());
        application.setDlitT(application.getDlitT());
        TypeConvertor convertor = new TypeConvertor();
        application.setType(convertor.convertType(type));
        applicationRepo.save(application);
        return new ResponseEntity<Application>(application, HttpStatus.OK);
    }

    public ResponseEntity<Application> editApplication(Application application, Long id) {
        try {
            Application application1 = applicationRepo.findById(id).get();
            application1.setFinish_less(application.getFinish_less());
            application1.setFree(application.isFree());
            application1.setChild(application.getChild());
            application1.setParent(publicService.getCurrentUsr());
            applicationRepo.save(application1);
            return new ResponseEntity<Application>(application1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Application>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Application> getApplicationById(Long id){
        try {
            return new ResponseEntity<Application>(applicationRepo.findById(id).get(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Application>(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteApplicationById(Long id){
        applicationRepo.delete(applicationRepo.findById(id).orElseThrow());
    }

    public ResponseEntity<Application> addTableApplication(Application application){
        application.setParent(publicService.getCurrentUsr());
        applicationRepo.save(application);
        return new ResponseEntity<Application>(application, HttpStatus.OK);
    }

    public ResponseEntity<List<Application>> getByUserId(Long id) {
        User parent = userService.getUserById(id).getBody();
        if (parent != null) {
            return new ResponseEntity<List<Application>>(applicationRepo.findByParent(parent), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Application> stopLesson(Long id) {
        try {
            Application lesson = applicationRepo.findById(id).get();
            lesson.setFinished(true);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
