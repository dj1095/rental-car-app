package com.example.myapplication;

public enum Category {
    DAILY("BASIC", 0),
    WEEKLY("LUXURY", 1);

    private String type;
    private int value;

    Category(String category, int value) {
        this.type = type;
        this.value = value;
    }
}
