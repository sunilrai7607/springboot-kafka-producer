package com.kscapser.rest.message.service.producer.config;

import lombok.Data;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;

@EnableBinding(Source.class)
public class Producer {

    private Source mySource;

    public Producer(Source mySource) {

        super();
        this.mySource = mySource;
    }

    public Source getMySource() {

        return mySource;
    }

    public void setMySource(Source mysource) {

        mySource = mysource;
    }
}
