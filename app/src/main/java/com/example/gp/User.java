package com.example.gp;

import android.content.Context;

import java.security.PublicKey;

public class User extends  Person{
    public int QsScore;

    public int getQsScore() {
        return QsScore;
    }

    public void setQsScore(int qsScore) {
        QsScore = qsScore;
    }

    public String getEmotionVoice() {
        return EmotionVoice;
    }

    public void setEmotionVoice(String emotionVoice) {
        EmotionVoice = emotionVoice;
    }

    public String getEmotionImage() {
        return EmotionImage;
    }

    public void setEmotionImage(String emotionImage) {
        EmotionImage = emotionImage;
    }

    public String getMentalState() {
        return MentalState;
    }

    public void setMentalState(String mentalState) {
        MentalState = mentalState;
    }

    public String EmotionVoice;
    public String EmotionImage;
    public String MentalState;


    public User( Context C,String Name,  String Age, String Mail ,String Password) {
        super( C,Name,  Age,   Mail , Password);
    }

}
