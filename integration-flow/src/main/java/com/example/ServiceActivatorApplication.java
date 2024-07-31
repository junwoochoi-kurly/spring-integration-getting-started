package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@IntegrationComponentScan
@SpringBootApplication
public class ServiceActivatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceActivatorApplication.class, args);
    }

}
