package com.example.kafka.example2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {

    @Bean(name = "taskExecutorCustom")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // 기본적으로 생성할 스레드 수
        executor.setMaxPoolSize(20); // 최대 스레드 수
        executor.setQueueCapacity(50); // 큐 용량
        executor.setThreadNamePrefix("MyThreadPool-"); // 스레드 이름 접두사
        executor.initialize();
        return executor;
    }
}

