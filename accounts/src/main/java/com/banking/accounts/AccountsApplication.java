package com.banking.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// https://www.educba.com/docker-push/
// docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
// https://github.com/spring-cloud/spring-cloud-stream/issues/2639
@SpringBootApplication
//@RefreshScope
//@EnableFeignClients
//@ComponentScans({ @ComponentScan("com.banking.accounts.controller")})
//@EnableJpaRepositories("com.banking.accounts.repository")
//@EntityScan("com.banking.accounts.model")
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
