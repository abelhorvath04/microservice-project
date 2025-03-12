package com.ha1.api_gateway_group9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayGroup9Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayGroup9Application.class, args);
	}

}
