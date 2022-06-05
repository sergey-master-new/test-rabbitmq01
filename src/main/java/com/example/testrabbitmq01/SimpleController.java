package com.example.testrabbitmq01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AmqpTemplate template;

    @RequestMapping("/emit") //испускать
    @ResponseBody
    String queue1() {
        log.info("Emit to queue2");
        template.convertAndSend("queue2", "Message to queue 2");
        return "Request: Emit to queue 2";
    }
}
