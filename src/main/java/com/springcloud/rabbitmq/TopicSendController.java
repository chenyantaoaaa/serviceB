package com.springcloud.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenyantao
 * 2018/6/27.
 */
@RestController
@RequestMapping("topic")
public class TopicSendController {

    @Autowired
    private TopicSend sender;

    @RequestMapping("/topic")
    public void topic() throws Exception {
        sender.send();
    }

    @RequestMapping("/topic1")
    public void topic1() throws Exception {
        sender.send1();
    }

    @RequestMapping("/topic2")
    public void topic2() throws Exception {
        sender.send2();
    }
}