package com.example.cinqouplus.jeu;

import android.widget.TextView;

public class Score {
    private int score;
    private TextView textScore;

    public Score(){
        this.score = 0;
    }
    /*public Score(TextView textScore){
        this.score = 0;
        this.textScore = textScore;
    }*/

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = this.score+score;
    }

    public TextView getTextScore() {
        return textScore;
    }
}
