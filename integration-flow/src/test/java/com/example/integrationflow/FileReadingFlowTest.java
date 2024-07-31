package com.example.integrationflow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.PollableChannel;

@SpringBootTest
public class FileReadingFlowTest extends AbstractConfig {

    @Autowired
    private PollableChannel outputChannel;

    @Test
    void fileReadingFlowTest() {
        String resultPayload = (String) outputChannel.receive(10000).getPayload();
        Assertions.assertEquals(resultPayload, "HELLO WORLD");
    }

}
