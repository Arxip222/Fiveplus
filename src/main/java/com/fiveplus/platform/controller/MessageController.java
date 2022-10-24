package com.fiveplus.platform.controller;

import com.fiveplus.platform.model.Message;
import com.fiveplus.platform.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @PostMapping("/addMessage")
    public ResponseEntity<Message> addMessage(@RequestBody Message message){
        return messageService.saveMessage(message);
    }
}