package com.example.melearn.logic;

public class Card {

    private String front;
    private String back;
    private int score;

    public Card(String front, String back, int score){
        this.front = front;
        this.back = back;
        this.score = score;
    }

    public String getFront(){
        return this.front;
    }
    
    public void setFront(String front){
        this.front = front;
    }

    public String getBack(){
        return this.back;
    }

    public void setBack(String back){
        this.back = back;
    }

    public void increaseScore(int increment){
        this.score += increment;
    }
}
