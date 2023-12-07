package com.example.todo;

import com.example.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example"})
@EnableJpaRepositories(basePackages = {"com.example.todo"})
public class PlannerTodoApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlannerTodoApplication.class, args);
	}

}
