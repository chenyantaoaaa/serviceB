package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
@RestController
@EnableAutoConfiguration
//@EnableHystrix
public class ServiceBApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceBApplication.class, args);
	}


	@Bean
//	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
