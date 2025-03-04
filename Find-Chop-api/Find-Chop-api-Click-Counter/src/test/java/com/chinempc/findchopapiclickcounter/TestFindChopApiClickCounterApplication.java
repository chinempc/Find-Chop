package com.chinempc.findchopapiclickcounter;

import org.springframework.boot.SpringApplication;

public class TestFindChopApiClickCounterApplication {

	public static void main(String[] args) {
		SpringApplication.from(FindChopApiClickCounterApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
