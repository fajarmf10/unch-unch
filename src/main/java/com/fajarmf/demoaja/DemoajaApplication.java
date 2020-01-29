package com.fajarmf.demoaja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EntityScan("com.fajarmf.demoaja.entity")
@SpringBootApplication
@EnableJpaAuditing
public class DemoajaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoajaApplication.class, args);
	}

}
