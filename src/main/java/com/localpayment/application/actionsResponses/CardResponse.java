package com.localpayment.application.actionsResponses;

import com.localpayment.domain.entities.Card;

import java.util.List;

public class CardResponse {
    private List<Card> cards;
    private String message;
    private String status;

    public CardResponse() {

    }

    public CardResponse(List<Card> cards, String message, String status) {
        this.cards = cards;
        this.message = message;
        this.status = status;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> card) {
        this.cards = card;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
