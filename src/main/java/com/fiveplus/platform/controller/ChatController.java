package com.fiveplus.platform.controller;

import com.fiveplus.platform.model.Chat;
import com.fiveplus.platform.model.Child;
import com.fiveplus.platform.service.ChatService;
import com.fiveplus.platform.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping("/addChat")
    public ResponseEntity<Chat> addChat(@RequestBody Chat chat){
        return chatService.saveChat(chat);
    }
}