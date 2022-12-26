package org.itmo.highload.dishservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class DishServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DishServiceApplication.class, args);
	}

}
