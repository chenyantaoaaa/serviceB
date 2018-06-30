package com.springcloud.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenyantao
 * 2018/6/27.
 */
@RestController
public class HelloController {

    @Autowired
    private HelloSender helloSender;

    @RequestMapping("/send/{name}")
    public String helloworld(@PathVariable String name) {
        return helloSender.send(name);
    }

    @RequestMapping("/sendMany")
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            helloSender.send(String.valueOf(i));
        }
    }

    @RequestMapping("/fanoutSend")
    public void fanoutSend() throws Exception {
        helloSender.sendFanout();
    }
}