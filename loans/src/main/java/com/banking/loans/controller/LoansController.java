package com.banking.loans.controller;

import com.banking.loans.config.LoansServiceConfig;
import com.banking.loans.model.Customer;
import com.banking.loans.model.Loans;
import com.banking.loans.model.Properties;
import com.banking.loans.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoansController {
    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private LoansServiceConfig loansServiceConfig;

    private static final Logger logger = LoggerFactory.getLogger(LoansController.class);

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestHeader("banking-correlation-id") String correlationId, @RequestBody Customer customer) {
        logger.info("getLoansDetails() started");
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDateDesc(customer.getCustomerId());
        logger.info("getLoansDetails() ended");
        return loans;
    }

    @GetMapping("/loan/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(loansServiceConfig.getMsg(), loansServiceConfig.getBuildVersion(),
                loansServiceConfig.getMailDetails(), loansServiceConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
