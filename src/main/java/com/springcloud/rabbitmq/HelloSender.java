package com.springcloud.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * Created by chenyantao
 * 2018/6/27.
 */
@Controller
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public String  send(String name) {
        String context = "hello "+name+" --" + new Date();
        this.rabbitTemplate.convertAndSend("hello", context);
        return context;
    }

    public void sendFanout() {
        String context = "hi, fanout msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange","", context);
    }
}
