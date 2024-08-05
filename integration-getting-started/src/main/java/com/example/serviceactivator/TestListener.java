package com.example.serviceactivator;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class TestListener {

    /**
     * @see org.springframework.integration.config.MessagingAnnotationBeanPostProcessor#processAnnotationTypeOnMethod
     * 채널이 존재하지 않을 경우 해당 이름을 가진 DirectChannel로 빈 등록
     * */
    @ServiceActivator(inputChannel = TestEvent.EVENT_CHANNEL)
    public void handle() {
        System.out.println("event handling...!");
    }

}
