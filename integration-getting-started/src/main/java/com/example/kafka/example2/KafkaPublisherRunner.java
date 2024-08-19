package com.example.kafka.example2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
./kafka-console-producer --topic topic1 --bootstrap-server localhost:9092 --property "parse.key=true" --property "key.separator=:"message:{"message":"junwoo"}
 * */
@Component
public class KafkaPublisherRunner implements CommandLineRunner {

    private final KafkaMessagePublisher kafkaMessagePublisher;

    public KafkaPublisherRunner(KafkaMessagePublisher kafkaMessagePublisher) {
        this.kafkaMessagePublisher = kafkaMessagePublisher;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 20; i++) {
            String jsonMessage = String.format("{\"message\":\"Hello Kafka %d\"}", i);
            kafkaMessagePublisher.sendMessage(jsonMessage);
        }
    }


}
