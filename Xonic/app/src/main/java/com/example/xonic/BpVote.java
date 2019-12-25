package com.example.xonic;


import java.io.Serializable;

public class BpVote implements Serializable {
    private String name;
    private int votes;
    private double vestvote;
    private String textbutton;

    public BpVote(String name, int votes, double vestvote, String textbutton) {
        this.name = name;
        this.votes = votes;
        this.vestvote = vestvote;
        this.textbutton = textbutton;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getVestvote() {
        return vestvote;
    }

    public void setVestvote(double vestvote) {
        this.vestvote = vestvote;
    }

    public String getTextbutton() {
        return textbutton;
    }

    public void setTextbutton(String textbutton) {
        this.textbutton = textbutton;
    }
}
