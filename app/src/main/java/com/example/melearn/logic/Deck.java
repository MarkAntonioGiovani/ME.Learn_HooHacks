package com.example.melearn.logic;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private String deckid;
    private String name;
    private String desc;
    private List<Card> cards;

    public Deck(String deckid, String name, String description) {
        this.deckid = deckid;
        this.name = name;
        this.desc = description;
        this.cards = new ArrayList<>();
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    public String getDeckid() {
        return this.deckid;
    }

    public void setDeckid(String deckid) {
        this.deckid = deckid;
    }

    public List<Card> getCards() {
        return this.cards;
    }


}
