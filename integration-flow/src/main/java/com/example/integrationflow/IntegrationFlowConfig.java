package com.example.integrationflow;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.dsl.Files;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

import java.io.File;

@Configuration
public class IntegrationFlowConfig {

    @Bean
    public IntegrationFlow upperFlow() {
        return IntegrationFlow.from("inputChannel")
                .transform((GenericTransformer<String, String>) String::toUpperCase)
                .channel("outputChannel")
                .get();

    }

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PollableChannel outputChannel() {
        return new QueueChannel();
    }

    @Bean
    public FileReadingMessageSource sourceDirectory() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        messageSource.setDirectory(new File("test"));
        return messageSource;
    }

    @Bean
    public IntegrationFlow fileReadingFlow() {
        return IntegrationFlow.from(sourceDirectory(), configurer -> configurer.poller(Pollers.fixedDelay(10000)))
                .transform(Files.toStringTransformer())
                .channel(inputChannel())
                .channel(outputChannel())
                .handle(message -> System.out.println("Received file content: " + message.getPayload()))
                .get();
    }

}
