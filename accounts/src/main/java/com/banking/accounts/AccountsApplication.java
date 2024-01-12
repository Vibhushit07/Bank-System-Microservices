package com.banking.accounts;

import com.banking.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// https://www.educba.com/docker-push/
// docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
// https://github.com/spring-cloud/spring-cloud-stream/issues/2639
// mvn compiler jib:dockerBuild => docker image build command

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.banking.accounts.controller") })
@EnableJpaRepositories("com.banking.accounts.repository")
@EntityScan("com.banking.accounts.model")*/
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Bank Account microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Vibhushit",
						email = "NA",
						url = "NA"
				),
				license = @License(
						name = "NA",
						url = "NA"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank Accounts microservice REST API documentation",
				url = "NA"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
