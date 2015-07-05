package com.thoughtworks.list_it_mobile;

public class Item {
    int id;
    private int userId;
    public String name;
    private String priority;
    public String description;

    public Item(int userId, String name, String priority,String description) {
        this.userId = userId;
        this.name = name;
        this.priority = priority;
        this.description = description;
    }
}
