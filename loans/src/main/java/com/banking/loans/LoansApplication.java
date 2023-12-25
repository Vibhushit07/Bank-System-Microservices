package com.banking.loans;


import com.banking.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Loan microservice REST API Documentation",
				description = "Bank Loans microservice REST API Documentation",
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
				description = "Bank Cards microservice REST API documentation",
				url = "NA"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
