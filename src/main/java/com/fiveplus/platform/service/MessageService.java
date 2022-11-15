package com.fiveplus.platform.service;

import com.fiveplus.platform.model.Chat;
import com.fiveplus.platform.model.Message;
import com.fiveplus.platform.model.User;
import com.fiveplus.platform.repository.ChatRepo;
import com.fiveplus.platform.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;

    @Autowired
    PublicService publicService;

    @Autowired
    ChatRepo chatRepo;


    public ResponseEntity<Message> saveMessage(Message message) {
        message.setCreator(publicService.getCurrentUsr());
        //message.setChat(chatRepo.findById(3L).get());
        messageRepo.save(message);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
}
