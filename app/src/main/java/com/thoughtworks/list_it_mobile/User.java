package com.thoughtworks.list_it_mobile;

public class User {
    int id;
    String username;
    String email;

    public User(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return username;
    }
}
