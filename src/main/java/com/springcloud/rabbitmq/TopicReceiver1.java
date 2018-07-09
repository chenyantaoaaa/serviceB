package com.springcloud.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by chenyantao
 * 2018/6/28.
 */
@Component
public class TopicReceiver1 {

    @RabbitHandler
//    @RabbitListener(queues = "tp.message")
    public void process(Message message, Channel channel) {
        System.out.println("Topic Receiver1  : " + message);
        try {
            System.out.println("Receiver1 ack success");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @RabbitHandler
//    public void process(String message) {
//        System.out.println("Topic Receiver1  : " + message);
//    }
}
