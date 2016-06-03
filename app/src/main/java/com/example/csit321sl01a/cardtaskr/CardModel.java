package com.example.csit321sl01a.cardtaskr;


public class CardModel
{
    private int cardNumber; //1-54 the playing card
    private String userID;      //primary key (email) to find the user that owns this card
    private Boolean seenCard; //has the user drawn card yet?


    public CardModel()
    {
        seenCard=false;
    }

    public CardModel(int card, String user)
    {
        cardNumber = card;
        userID = user;
    }

    public Boolean getSeenCard() {
        return seenCard;
    }

    public void setSeenCard(Boolean seenCard) {
        this.seenCard = seenCard;
    }
}
