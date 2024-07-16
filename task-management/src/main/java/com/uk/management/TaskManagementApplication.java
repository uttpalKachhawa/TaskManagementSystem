package com.uk.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.uk.management")
@EnableJpaRepositories("com.uk.management")
@SpringBootApplication
@EntityScan(basePackages = "com.uk.management")
public class TaskManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskManagementApplication.class, args);
	}
}
