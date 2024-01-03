package com.banking.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator bankingRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(path -> path
						.path("/banking/accounts/**")
						.filters(filter -> filter.rewritePath("/banking/accounts/(?<segment>.*)", "/${segment}"))
						.uri("lb://ACCOUNTS"))  // lb means Load Balancer
				.route(path -> path
						.path("/banking/cards/**")
						.filters(filter -> filter.rewritePath("/banking/cards/(?<segment>.*)", "/${segment}"))
						.uri("lb://CARDS"))
				.route(path -> path
						.path("/banking/loans/**")
						.filters(filter -> filter.rewritePath("/banking/loans/(?<segment>.*)", "/${segment}"))
						.uri("lb://LOANS"))
				.build();
	}
}
