package com.springcloud.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenyantao
 * 2018/7/1.
 */
@RestController
@EnableAutoConfiguration
public class KafkaController {

    /**
     * 注入kafkaTemplate
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    /**
     * 发送消息的方法
     *
     * @param key  推送数据的key
     * @param data 推送数据的data
     */
    private void send(String key, String data) {
        kafkaTemplate.send("test0", key, data);
    }

    @RequestMapping("/kafka")
    public String testKafka() {
        int iMax = 10;
        for (int i = 1; i < iMax; i++) {
            send("key1" , "data" + i);
        }
        return "success";
    }

//    @KafkaListener(topics = "test0",groupId = "kafka2")
//    public void receive0(ConsumerRecord<?, ?> consumer) {
//        System.out.println("receive0 topic="+consumer.topic()+"   key="+consumer.key()+"    valu="+consumer.value());
//    }
//
//    @KafkaListener(topics = "test0",groupId = "kafka3")
//    public void receive1(ConsumerRecord<?, ?> consumer) {
//        System.out.println("receive1 topic="+consumer.topic()+"   key="+consumer.key()+"    valu="+consumer.value());
//    }
//
//    @KafkaListener(topics = "test0",groupId = "kafka3")
//    public void receive12(ConsumerRecord<?, ?> consumer) {
//        System.out.println("receive2 topic="+consumer.topic()+"   key="+consumer.key()+"    valu="+consumer.value());
//    }
}
