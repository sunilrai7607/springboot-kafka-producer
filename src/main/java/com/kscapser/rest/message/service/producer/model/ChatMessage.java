package com.kscapser.rest.message.service.producer.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 4934189216387970934L;

    private String contents;

    private long time;
}
