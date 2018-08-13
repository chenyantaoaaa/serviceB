package com.springcloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ComputeController {

//    @Autowired
//    private DiscoveryClient client;

    @Autowired
//    @LoadBalanced
    RestTemplate restTemplate;

    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
//        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
//        return restTemplate.getForEntity("http://SERVICEA/add?a=1&b=2",String.class).getBody();
        return r.toString();
    }
}