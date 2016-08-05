package com.epam.service;

import com.epam.model.Card;
import com.epam.repository.CardRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by fg on 7/27/2016.
 */
@Service
@Transactional
public class CardServiceImp implements CardService {
    private static final Logger LOGGER = Logger.getLogger(CardServiceImp.class);

    @Autowired
    private CardRepository cardRepository;

    @Override
    public void doAction(String actionAndCardId) {
        LOGGER.debug("actionAndCardId=" + actionAndCardId);
        int cardId = Integer.valueOf(actionAndCardId.substring(0, actionAndCardId.indexOf("+")));
        actionAndCardId = actionAndCardId.substring(actionAndCardId.indexOf("+") + 1, actionAndCardId.length());
        switch (actionAndCardId) {
            case DELETE : deleteCard(cardId);
                break;
            case UN_DELETE : restoreCard(cardId);
                break;
            case BLOCK : blockCard(cardId);
                break;
            case UN_BLOCK : unBlockCard(cardId);
        }
    }

    @Override
    public void blockCard(int cardId) {
        LOGGER.debug("block Card");
        Card card = cardRepository.findById(cardId);
        card.setActive(false);
        cardRepository.update(card);
    }

    @Override
    public void unBlockCard(int cardId) {
        LOGGER.debug("unBlock Card");
        Card card = cardRepository.findById(cardId);
        card.setActive(true);
        cardRepository.update(card);
    }

    @Override
    public void restoreCard(int cardId) {
        LOGGER.debug("restore Card");
        Card card = cardRepository.findById(cardId);
        card.setDeleted(false);
        cardRepository.update(card);
    }

    @Override
    public void deleteCard(int cardId) {
        LOGGER.debug("delete Card");
        Card card = cardRepository.findById(cardId);
        card.setDeleted(true);
        cardRepository.update(card);
    }

    @Override
    public Card findByName(String name) {
        LOGGER.debug("get Card by name");
        return cardRepository.findByName(name);
    }
}
