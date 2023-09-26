//package com.banking.gatewayserver;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//
//import java.util.Date;
//
//@SpringBootApplication
//public class GatewayserverApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(GatewayserverApplication.class, args);
//	}
//
//	@Bean
//	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p
//						.path("/banking/accounts/**")
//						.filters(f -> f.rewritePath("/banking/accounts/(?<segment>.*)","/${segment}")
//								.addResponseHeader("X-Response-Time",new Date().toString()))
//						.uri("lb://accounts")).
//				route(p -> p
//						.path("/banking/loans/**")
//						.filters(f -> f.rewritePath("/banking/loans/(?<segment>.*)","/${segment}")
//								.addResponseHeader("X-Response-Time",new Date().toString()))
//						.uri("lb://loans")).
//				route(p -> p
//						.path("/banking/cards/**")
//						.filters(f -> f.rewritePath("/banking/cards/(?<segment>.*)","/${segment}")
//								.addResponseHeader("X-Response-Time",new Date().toString()))
//						.uri("lb://cards")).build();
//	}
//
//}
