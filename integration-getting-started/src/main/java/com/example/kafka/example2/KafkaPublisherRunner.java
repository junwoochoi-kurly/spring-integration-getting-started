package com.example.kafka.example2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisherRunner implements CommandLineRunner {

    private final KafkaMessagePublisher kafkaMessagePublisher;

    public KafkaPublisherRunner(KafkaMessagePublisher kafkaMessagePublisher) {
        this.kafkaMessagePublisher = kafkaMessagePublisher;
    }

    @Override
    public void run(String... args) throws Exception {
        String jsonMessage = "{\"message\":\"Hello Kafka\"}";

        kafkaMessagePublisher.sendMessage(jsonMessage);
    }

}
