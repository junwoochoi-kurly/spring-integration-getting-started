package com.example.integrationflow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class IntegrationFlowConfigTest {

    @Autowired
    private MessageChannel inputChannel;

    @Autowired
    private PollableChannel outputChannel;

    @Test
    void integrationFlowConfigTest() {
        // given
        String input = "hello";

        // when
        inputChannel.send(MessageBuilder.withPayload(input).build());

        // then
        String resultPayload = (String) requireNonNull(outputChannel.receive(0)).getPayload();
        assertEquals(resultPayload, "HELLO");
    }

}