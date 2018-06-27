package com.springcloud.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenyantao
 * 2018/6/27.
 */
@Configuration
public class HelloRabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}
