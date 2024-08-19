package com.example.kafka.example1;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class ApplicationKafkaRunner implements ApplicationRunner {

    private final BookPublisher bookPublisher;
    private final MessageChannel messageChannel;

    public ApplicationKafkaRunner(BookPublisher bookPublisher, @Qualifier("producerChannel") MessageChannel messageChannel) {
        this.bookPublisher = bookPublisher;
        this.messageChannel = messageChannel;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Inside ProducerApplication run method...");

        List<Book> books = bookPublisher.getBooks();
        for (Book book : books) {
//            Map<String, Object> headers = Collections.singletonMap(KafkaHeaders.TOPIC, book.getGenre().toString());
//            messageChannel.send(new GenericMessage<>(book.toString(), headers));
        }

        System.out.println("Finished ProducerApplication run method...");
    }

}
