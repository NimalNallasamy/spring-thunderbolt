package com.ekart.thunderbolt;

import com.ekart.thunderbolt.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class ThunderboltApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThunderboltApplication.class, args);
	}

}
