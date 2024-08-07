package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "spring.kafka")
public class TestProperties {

    private final String topic;

    @ConstructorBinding
    public TestProperties(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

}
