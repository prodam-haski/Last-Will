package com.prodadimhaski.lastwill.Room.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = "login")
public class Login {
    @NonNull
    private String password;

    @NonNull
    private String question;

    @NonNull
    private String answer;

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getQuestion() {
        return question;
    }

    public void setQuestion(@NonNull String question) {
        this.question = question;
    }

    @NonNull
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(@NonNull String answer) {
        this.answer = answer;
    }
}
