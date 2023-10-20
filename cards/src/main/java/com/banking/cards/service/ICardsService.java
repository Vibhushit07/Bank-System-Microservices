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

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of the Card details is successful or not
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if delete of the Card details is successful or not
     */
    boolean deleteCard(String mobileNumber);
}
