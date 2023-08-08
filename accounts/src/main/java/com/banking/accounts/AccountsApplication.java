package com.banking.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// https://www.educba.com/docker-push/
@SpringBootApplication
@ComponentScans({ @ComponentScan("com.banking.accounts.controller")})
@EnableJpaRepositories("com.banking.accounts.repository")
@EntityScan("com.banking.accounts.model")
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
