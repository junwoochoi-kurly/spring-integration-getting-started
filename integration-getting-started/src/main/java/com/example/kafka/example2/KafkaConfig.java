package com.example.kafka.example2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.kafka.inbound.KafkaMessageDrivenChannelAdapter;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.messaging.PollableChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaConfig {

    private static final ObjectMapper KAFKA_MAPPER;

    static {
        KAFKA_MAPPER = new ObjectMapper();
    }

    @Bean
    public PollableChannel testQueueChannel() {
        return new QueueChannel();
    }

    @Bean
    public PollableChannel testOutBoundChannel() {
        return new QueueChannel();
    }

    @Bean("simpleKafkaListenerContainer")
    public ConcurrentMessageListenerContainer<String, String> kafkaListenerContainer(ConsumerFactory<String, String> consumerFactory) {
        // 예시로 토픽명을 명시적으로 정의
        String[] topics = {"topic1", "topic2"};
        ContainerProperties containerProps = new ContainerProperties(topics);
        containerProps.setGroupId("test-topic-group");
        containerProps.setClientId("unique-client-id-" + UUID.randomUUID());
        /**
         * 설정한 concurrency 만큼 consumerId를 생성하여 병렬로 처리한다.
         * */
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
        PollableChannel testQueueChannel,
        SimpleKafkaMessageConverter messageConverter) {

        KafkaMessageDrivenChannelAdapter<String, String> adapter = new KafkaMessageDrivenChannelAdapter<>(simpleKafkaListenerContainer);
        adapter.setOutputChannel(testQueueChannel);
        adapter.setMessageConverter(messageConverter);
        return adapter;
    }

    public static ObjectMapper getKafkaMapper() {
        return KAFKA_MAPPER;
    }

}
