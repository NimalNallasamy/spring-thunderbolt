package com.ekart.thunderbolt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ThunderboltApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThunderboltApplication.class, args);
	}

}
