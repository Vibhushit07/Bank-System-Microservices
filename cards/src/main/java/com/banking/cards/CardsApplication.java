package com.banking.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// https://github.com/eazybytes/microservices-with-spring-sectionwise-code
@SpringBootApplication
@ComponentScans({ @ComponentScan("com.banking.cards.controller") })
@EnableJpaRepositories("com.banking.cards.repository")
@EntityScan("com.banking.cards.model")
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}