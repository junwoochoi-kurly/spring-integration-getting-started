package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
@EnableIntegration
public class BasicIntegrationConfig extends AbstractConfig {

    @Bean
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "fileChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        messageSource.setDirectory(new File(INPUT_DIR));
        messageSource.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
        return messageSource;
    }

    @Bean
    @ServiceActivator(inputChannel = "fileChannel")
    public MessageHandler fileWriteMessageHandler() {
        FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        fileWritingMessageHandler.setFileExistsMode(FileExistsMode.REPLACE);
        fileWritingMessageHandler.setExpectReply(false);
        return fileWritingMessageHandler;

    }

}
