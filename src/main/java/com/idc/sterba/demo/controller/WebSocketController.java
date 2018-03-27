package com.idc.sterba.demo.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    WebSocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/send/message")
    public void onReceiveMessage(String message) {
        this.simpMessagingTemplate.convertAndSend("/chat", "test");
    }
}
