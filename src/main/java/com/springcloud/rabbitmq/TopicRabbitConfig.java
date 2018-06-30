package com.springcloud.rabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by chenyantao
 * 2018/6/27.
 */
@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue AMessage() {
        return new Queue("tp.message");
    }

    @Bean
    public Queue BMessage() {
        return new Queue("tp.messages");
    }


    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(AMessage).to(topicExchange).with("tp.message");
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(BMessage).to(topicExchange).with("tp.#");
    }
}
