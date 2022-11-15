package com.fiveplus.platform.service;


import com.fiveplus.platform.model.Chat;
import com.fiveplus.platform.model.User;
import com.fiveplus.platform.repository.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    ChatRepo chatRepo;

    @Autowired
    PublicService publicService;


    public ResponseEntity<Chat> saveChat(Chat chat) {
        List<User> users = new ArrayList<>();
        users.add(publicService.getCurrentUsr());
        chat.setUsers(users);
        chatRepo.save(chat);
        return new ResponseEntity<Chat>(chat, HttpStatus.OK);
    }
}
