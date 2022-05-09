package com.example.cinqouplus.jeu;

public class Score {
    private int score;

    public Score(){
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = this.score+score;
    }
}
