package com.example.oil.ui.login.model;

import android.text.TextUtils;

public class User implements IUser {

   private String login, password;


    @Override
    public boolean isValidData() {
        return !TextUtils.isEmpty(login.trim()) && password.length() >= 6;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getLogin() {
        return login;
    }
}
