package com.kscapser.rest.message.service.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kscapser.rest.message.service.producer.config.Producer;
import com.kscapser.rest.message.service.producer.model.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class KafkaMessageService {

    private String topicPattern = "sample.message.%s.events";
    private final BinderAwareChannelResolver resolver;

    private final Producer producer;

    @Autowired
    public KafkaMessageService(BinderAwareChannelResolver resolver, Producer producer) {
        this.resolver = resolver;
        this.producer = producer;
    }


    /**
     * Method to generate the message to topic
     *
     * @param channelName
     * @return
     */
    public String sendMessage(ChatMessage channelName) {
        channelName.setTime(System.currentTimeMillis());
        Message<String> message = MessageBuilder.withPayload(buildPayload(channelName))
                .setHeader(KafkaHeaders.MESSAGE_KEY, "chat-1")
                .setHeader("type", "\"chat\"")
                .build();
        resolver.resolveDestination(createTopic("chat")).send(message);
        log.info("Generated Message : {} ", message);
        //producer.getMySource().output().send(message);

        return "Message send Successfully";
    }

    private String buildPayload(ChatMessage channelName) {
        try{
            return new ObjectMapper().writeValueAsString(channelName);
        }catch (JsonProcessingException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private String createTopic(String channelName) {
        return String.format(topicPattern, channelName);
    }
}