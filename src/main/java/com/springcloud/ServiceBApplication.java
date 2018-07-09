package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableEurekaClient
@RestController
@EnableAutoConfiguration
//@EnableHystrix
@MapperScan("com.springcloud.splitTable.mapper")
@ServletComponentScan
@EnableSwagger2
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
