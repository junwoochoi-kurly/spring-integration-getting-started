package com.example.kafka.example2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.PollableChannel;

import java.util.Map;

@Configuration
public class KafkaConfig {

    private static final ObjectMapper KAFKA_MAPPER;

    static {
        KAFKA_MAPPER = new ObjectMapper();
    }

    @Bean
    public PollableChannel inboundChannel() {
        return new QueueChannel();
    }

    @Bean("simpleKafkaListenerContainer")
    public ConcurrentMessageListenerContainer<String, String> kafkaListenerContainer(ConsumerFactory<String, String> consumerFactory) {
        // 예시로 토픽명을 명시적으로 정의
        String[] topics = {"topic1", "topic2"};
        ContainerProperties containerProps = new ContainerProperties(topics);
        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(consumerFactory, containerProps);
        container.setConcurrency(3);  // 병렬 처리 설정
        return container;
    }

    @Bean("simpleConcurrentKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public SimpleKafkaMessageConverter messageConverter() {
        Map<String, Class<?>> topicMap = Map.of("topic1", EventDomain1.class, "topic2", EventDomain2.class);  // 토픽과 클래스 매핑 예시
        return new SimpleKafkaMessageConverter(getKafkaMapper(), topicMap);
    }

    @Bean("simpleKafkaMessageDrivenChannelAdapter")
    public KafkaMessageDrivenChannelAdapter<String, String> kafkaMessageAdapter(
        ConcurrentMessageListenerContainer<String, String> simpleKafkaListenerContainer,
        PollableChannel inboundChannel,
        SimpleKafkaMessageConverter messageConverter) {

        KafkaMessageDrivenChannelAdapter<String, String> adapter = new KafkaMessageDrivenChannelAdapter<>(simpleKafkaListenerContainer);
        adapter.setOutputChannel(inboundChannel);
        adapter.setMessageConverter(messageConverter);
        return adapter;
    }

    public static ObjectMapper getKafkaMapper() {
        return KAFKA_MAPPER;
    }

//    @Bean
//    @ServiceActivator(inputChannel = "inboundChannel", poller = @Poller(fixedDelay = "1000", taskExecutor = "taskExecutor"))
//    public MessageHandler kafkaMessageHandler() {
//        // 메시지 처리 로직 구현
//        return message -> {
//            // 메시지 처리 코드
//            System.out.println("Received message: " + message.getPayload());
//        };
//    }

}
