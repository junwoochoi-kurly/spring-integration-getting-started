package com.example.kafka.example2;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessagePublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        String topic = "topic1";
        kafkaTemplate.send(topic, message);
//        System.out.println("Message sent to topic1: " + message);
    }
}
