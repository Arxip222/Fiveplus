package com.fiveplus.platform.controller;

import com.fiveplus.platform.model.Question;
import com.fiveplus.platform.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    QuestionRepo questionRepo;

    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return new ResponseEntity<Question>(questionRepo.save(question), HttpStatus.OK);
    }
}
