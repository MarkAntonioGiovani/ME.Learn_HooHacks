package com.example.melearn.logic;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private User admin;
    private List<User> participants;

    public Group(String name, User admin){
        this.name = name;
        this.admin = admin;
        this.participants = new ArrayList<>();
        this.participants.add(admin);
    }

}
