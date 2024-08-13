package com.example.kafka.example2;

import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("simpleKafkaMessageHandler")
public class KafkaMessageHandler {

    @ServiceActivator(inputChannel = "inboundChannel", poller = @Poller(fixedDelay = "1000", taskExecutor = "taskExecutorCustom"))
    public void handleKafkaMessage(Message<EventDomain1> message) {
        EventDomain1 payload = message.getPayload();
        // 메시지 처리 로직
        System.out.println("Received message: " + payload.getMessage());
    }

}
