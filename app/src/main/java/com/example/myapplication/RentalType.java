package com.example.myapplication;

public enum RentalType {
    DAILY("DAILY", 1),
    WEEKLY("WEEKLY", 7),
    DEFAULT("DEFAULT",0);

    private String type;
    private int value;

    RentalType(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
