package com.example.kafka.example2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("simpleKafkaMessageHandler")
public class KafkaMessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageHandler.class);

    // todo outPutChannel 은 어떻게 되는거지?
    /**
     * outboundChannel 은 빈으로 등록되어야 한다.
     * */
    @ServiceActivator(inputChannel = "testQueueChannel", poller = @Poller(fixedDelay = "1000", taskExecutor = "taskExecutorCustom"), outputChannel = "testOutBoundChannel")
    public EventDomain1 handleKafkaMessage1(Message<EventDomain1> message) throws InterruptedException {
        EventDomain1 payload = message.getPayload();
        logger.info("Received message (QueueChannel): {}", payload.getMessage());

        Thread.sleep(2000L);

        logger.info("finished (QueueChannel): {}", payload.getMessage());

        return payload;
    }

    
}
