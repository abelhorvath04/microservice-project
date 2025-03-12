package com.example.eureka_server_group9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerGroup9Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerGroup9Application.class, args);
	}

}
