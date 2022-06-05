package com.example.testrabbitmq01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitMqListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "queue2")
    public void processQueue1(String message) {
        log.info("Received from queue 2: " + message);
    }
}
