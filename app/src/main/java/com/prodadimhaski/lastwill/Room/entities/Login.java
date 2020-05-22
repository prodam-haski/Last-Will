package com.prodadimhaski.lastwill.Room.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class Login {
    @PrimaryKey
    private int id;

    @NonNull
    private String password;

    @NonNull
    private String question;

    @NonNull
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Login(int id, @NonNull String password, @NonNull String question, @NonNull String answer) {
        this.id = id;
        this.password = password;
        this.question = question;
        this.answer = answer;
    }

    @NonNull
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(@NonNull String answer) {
        this.answer = answer;
    }
}
