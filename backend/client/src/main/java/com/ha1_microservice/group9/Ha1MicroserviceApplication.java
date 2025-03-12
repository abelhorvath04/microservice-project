package com.ha1_microservice.group9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class Ha1MicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(Ha1MicroserviceApplication.class, args);
	}
}
