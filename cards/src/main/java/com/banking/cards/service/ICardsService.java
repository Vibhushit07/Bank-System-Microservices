package com.banking.cards.service;

import com.banking.cards.dto.CardsDto;

public interface ICardsService {

    /**
     *
     * @param mobileNumber- Mobile Number of Customer
     */
    void createCard(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Card Details based on a given mobileNumber
     */
    CardsDto fetchCard(String mobileNumber);
}
