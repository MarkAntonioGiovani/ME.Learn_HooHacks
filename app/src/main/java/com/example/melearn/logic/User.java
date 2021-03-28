package com.example.melearn.logic;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String id;
    private List<Deck> decks;
    private List<Group> groups;
    private List<User> friends;

    public User(String name, String id){
        this.name = name;
        this.id = id;
        this.decks = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addDeck(String id, String name, String description){
        this.decks.add(new Deck(id, name, description));
    }
    public void removeDeck(Deck deck){
        this.decks.remove(deck);
    }

    public void addFriend(User friend){
        this.friends.add(friend);
    }

    public void removeFriend(User friend){
        this.friends.remove(friend);
    }

    public void joinGroup(Group group){
        this.groups.add(group);
    }

    public void leaveGroup(Group group){
        this.groups.remove(group);
    }

    public void createGroup(String name){
        Group g = new Group(name, this);
        this.joinGroup(g);
    }


    public List<Deck> getDecks() {
        return this.decks;
    }

}
