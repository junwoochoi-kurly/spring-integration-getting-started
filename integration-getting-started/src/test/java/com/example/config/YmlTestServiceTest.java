package com.example.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class YmlTestServiceTest {

    @Autowired
    private YmlTestService ymlTestService;

    @Test
    void test() {
        String result = ymlTestService.method();
        assertThat(result).isEqualTo("welcome-topic");
    }

}