package com.example.serviceactivator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestListenerTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testEventChannelBeanExists() {
        MessageChannel eventChannel = applicationContext.getBean(TestEvent.EVENT_CHANNEL, MessageChannel.class);

        assertTrue(eventChannel instanceof DirectChannel, "The channel should be an instance of DirectChannel.");
    }


}