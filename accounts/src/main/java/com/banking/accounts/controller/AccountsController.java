package com.banking.accounts.controller;

import com.banking.accounts.config.AccountsServiceConfig;
import com.banking.accounts.model.*;
import com.banking.accounts.repository.AccountsRepository;
import com.banking.accounts.service.client.CardsFeignClient;
import com.banking.accounts.service.client.LoansFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountsController {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private AccountsServiceConfig accountsServiceConfig;

    @Autowired
    LoansFeignClient loansFeignClient;

    @Autowired
    CardsFeignClient cardsFeignClient;

    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @PostMapping("/myAccount")
    public Accounts getAccountDetail(@RequestBody Customer customer) {

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        return accounts;
    }

    @GetMapping("/account/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion(),
                accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

    @PostMapping("/myCustomerDetails")
//    @CircuitBreaker(name = "detailsForCustomerSupportApp", fallbackMethod = "myCustomerDetailsFallback")
    @Retry(name = "retryForCustomerDetails", fallbackMethod = "myCustomerDetailsFallback")
    public CustomerDetails myCustomerDetails(@RequestHeader("banking-correlation-id") String correlationId, @RequestBody Customer customer) {
        logger.info("myCustomerDetials() started");
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoanDetails(correlationId, customer);
        List<Cards> cards = cardsFeignClient.getCardDetails(correlationId, customer);
        logger.info("myCustomerDetials() ended");
        return CustomerDetails
                .builder()
                .accounts(accounts)
                .loans(loans)
                .cards(cards)
                .build();
    }

    private CustomerDetails myCustomerDetailsFallback(@RequestHeader("banking-correlation-id") String correlationId, Customer customer, Throwable t) {
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Loans> loans = loansFeignClient.getLoanDetails(correlationId, customer);

        return CustomerDetails
                .builder()
                .accounts(accounts)
                .loans(loans)
                .build();
    }

    @GetMapping("/sayHello")
    @RateLimiter(name = "sayHello", fallbackMethod = "sayHelloFallbackMethod")
    public String sayHello() {
        return "Hello, Welcome to Banking Microservice Application";
    }

    private String sayHelloFallbackMethod(Throwable t) {
        return "Hi, Welcome to Banking Microservice Application";
    }
}
