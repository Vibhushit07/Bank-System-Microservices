//package com.banking.cards.controller;
//
//import com.banking.cards.config.CardsServiceConfig;
//import com.banking.cards.model.Cards;
//import com.banking.cards.model.Customer;
//import com.banking.cards.model.Properties;
//import com.banking.cards.repository.CardsRepository;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class CardsController {
//
//    @Autowired
//    private CardsRepository cardsRepository;
//
//    @Autowired
//    private CardsServiceConfig cardsServiceConfig;
//
//    private static final Logger logger = LoggerFactory.getLogger(CardsController.class);
//
//    @PostMapping("/myCards")
//    public List<Cards> getCardDetails(@RequestHeader("banking-correlation-id") String correlationId, @RequestBody Customer customer) {
//        logger.info("getCardDetails() started");
//        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
//        logger.info("getCardDetails() ended");
//        return cards;
//    }
//
//    @GetMapping("/card/properties")
//    public String getPropertyDetails() throws JsonProcessingException {
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        Properties properties = new Properties(cardsServiceConfig.getMsg(), cardsServiceConfig.getBuildVersion(),
//                cardsServiceConfig.getMailDetails(), cardsServiceConfig.getActiveBranches());
//        String jsonStr = ow.writeValueAsString(properties);
//        return jsonStr;
//    }
//
//}