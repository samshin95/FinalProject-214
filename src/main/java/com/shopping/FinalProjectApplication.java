package com.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan({"com.shopping","com.shopping.controller","com.shopping.model","com.shopping.json","com.shopping.service","com.shopping.repository","com.shopping.interceptor"})
@EntityScan({"com.shopping","com.shopping.controller","com.shopping.model","com.shopping.json","com.shopping.service","com.shopping.repository","com.shopping.interceptor"})
@EnableJpaRepositories("com.shopping.repository") 
public class FinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

}
