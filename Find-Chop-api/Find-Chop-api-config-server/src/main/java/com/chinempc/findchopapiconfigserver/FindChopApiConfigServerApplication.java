package com.chinempc.findchopapiconfigserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FindChopApiConfigServerApplication {
	//@Value("${env.MONGO_URI}")
	//private String url;

	public static void main(String[] args) {
		SpringApplication.run(FindChopApiConfigServerApplication.class, args);
	}

}
