package com.springcloud.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

/**
 * Created by chenyantao
 * 2018/6/27.
 */
@Component
//@RabbitListener(queues = "fanout.A")
public class HelloReceiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }
}
