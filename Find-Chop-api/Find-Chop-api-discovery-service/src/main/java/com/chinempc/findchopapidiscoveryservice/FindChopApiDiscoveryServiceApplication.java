package com.chinempc.findchopapidiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableEurekaServer
@EnableRetry
public class FindChopApiDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindChopApiDiscoveryServiceApplication.class, args);
	}

}
