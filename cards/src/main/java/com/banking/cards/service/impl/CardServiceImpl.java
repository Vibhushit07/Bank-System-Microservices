package com.banking.cards.service.impl;

import com.banking.cards.constants.CardsConstants;
import com.banking.cards.entity.Cards;
import com.banking.cards.exception.CardAlreadyExistException;
import com.banking.cards.repository.CardsRepository;
import com.banking.cards.service.ICardsService;

import java.util.Random;

public class CardServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    /**
     * @param mobileNumber - Mobile Number of Customer
     */
    @Override
    public void createCard(String mobileNumber) {

        if(cardsRepository.findByMobileNumber(mobileNumber).isPresent()) {
            throw new CardAlreadyExistException("Card already registered with given mobileNumber "+mobileNumber);
        }

        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
