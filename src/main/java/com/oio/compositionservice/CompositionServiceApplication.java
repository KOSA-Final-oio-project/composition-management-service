package com.oio.compositionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CompositionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompositionServiceApplication.class, args);
	}

}
