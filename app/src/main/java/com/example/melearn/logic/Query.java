package com.example.melearn.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Query {
    private List<Card> cards;
    private int length;

    public Query(int length, Deck deck){
        this.length = length;
        this.cards = new ArrayList<>();
        Random r = new Random();
        while(this.cards.size() < length && deck.getCards().size() != this.cards.size()){
            int id = r.nextInt(deck.getCards().size());
            if(!this.cards.contains(deck.getCards().get(id))){
                this.cards.add(deck.getCards().get(id));
            }
        }
    }
}
