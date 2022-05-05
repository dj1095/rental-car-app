package com.example.myapplication;

public enum VehicleType {
    COMPACT("COMPACT", 1),
    MEDIUM("MEDIUM", 2),
    LARGE("LARGE", 3),
    SUV("SUV", 4),
    TRUCK("TRUCK", 5),
    VAN("VAN", 6);
    private String category;
    private int value;

    VehicleType(String category, int value) {
        this.category = category;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
