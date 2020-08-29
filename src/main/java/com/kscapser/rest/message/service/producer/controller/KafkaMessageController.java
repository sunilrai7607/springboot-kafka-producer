package com.kscapser.rest.message.service.producer.controller;

import com.kscapser.rest.message.service.producer.model.ChatMessage;
import com.kscapser.rest.message.service.producer.service.KafkaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class KafkaMessageController {

    private final KafkaMessageService kafkaMessageService;

    @Autowired
    public KafkaMessageController(KafkaMessageService kafkaMessageService) {
        this.kafkaMessageService = kafkaMessageService;
    }

    @PostMapping(value = "/sendMessage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sayHello(@RequestBody ChatMessage chatMessage) {
        return ResponseEntity.ok(kafkaMessageService.sendMessage(chatMessage));
    }
}
