package com.springcloud.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by chenyantao
 * 2018/6/28.
 */
@Component
@RabbitListener(queues = "fanout.D")
public class HelloReceiver4 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver 4 : " + hello);
    }
}