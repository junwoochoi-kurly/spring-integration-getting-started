package com.example.kafka.example2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("simpleKafkaMessageHandler")
public class KafkaMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageHandler.class);


    @ServiceActivator(inputChannel = "testQueueChannel")
    public void handleKafkaMessage(Message<EventDomain1> message) throws InterruptedException {
        EventDomain1 payload = message.getPayload();
        // 메시지 처리 로직
        logger.info("Received message (QueueChannel): {}", payload.getMessage());

        Thread.sleep(2000L);

        logger.info("finished (QueueChannel): {}", payload.getMessage());
    }

    
}
