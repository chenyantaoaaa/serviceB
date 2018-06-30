package com.springcloud.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by chenyantao
 * 2018/6/28.
 */
@Component
public class TopicReceiver2 {
    @RabbitHandler
    @RabbitListener(queues = "tp.messages")
    public void process(Message message, Channel channel) {
        System.out.println("Topic Receiver2  : " + message);
        try {
            System.out.println("Receiver2 ack success");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
