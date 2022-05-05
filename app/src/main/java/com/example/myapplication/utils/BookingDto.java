package com.example.myapplication.utils;

import com.example.myapplication.VehicleType;

import java.io.Serializable;

public class BookingDto  implements Serializable {
    private String bookDate;
    private String returnDate;
    private String vehicleType;
    private String rentalCategory;
    private String modeOfRental;
    private String userId;
    private String vehicleId;
    private int quantity;
    private Double payment;

    public String getModeOfRental() {
        return modeOfRental;
    }

    public void setModeOfRental(String modeOfRental) {
        this.modeOfRental = modeOfRental;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRentalCategory() {
        return rentalCategory;
    }

    public void setRentalCategory(String rentalCategory) {
        this.rentalCategory = rentalCategory;
    }


}
