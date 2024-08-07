package com.example.config;

import org.springframework.stereotype.Service;

@Service
public class YmlTestService {

    private final TestProperties testProperties;


    public YmlTestService(TestProperties testProperties) {
        this.testProperties = testProperties;
    }

    public String method() {
        return testProperties.getTopic();
    }

}
