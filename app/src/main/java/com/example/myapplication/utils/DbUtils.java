package com.example.myapplication.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.myapplication.RentalType;
import com.example.myapplication.models.Customer;
import com.example.myapplication.models.Vehicle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("StaticFieldLeak")
public class DbUtils {

    //private static final String URL = "jdbc:mysql://192.168.1.86:3306/car_rental";
    //public static final String USER = "root";
    //public static final String PASSWORD = "root";

    public static final String URL = "jdbc:mysql://51.81.160.154:3306/dxj0015_rental_car";
    public static final String USER = "dxj0015_pramodh";
    public static final String PASSWORD = "WCHtukx43RzwWMg";
    private static Connection connection = null;

    public static Connection getDbConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                Log.e("DatabaseUtils", "Error Connecting to Database", e);
            }
        }
        return connection;
    }

    public static int saveCustomer(Customer customer, Connection connection) {
        String query = "INSERT INTO customer(cust_id,name,phone)VALUES(?,?,?)";
        int row = 0;
        try {
            if (connection != null) {
                Log.d("DbUtils", customer.getCustomerName(), null);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, customer.getCustomerId());
                preparedStatement.setString(2, customer.getCustomerName());
                preparedStatement.setString(3, customer.getPhoneNumber());
                row = preparedStatement.executeUpdate();
                Log.d("DbUtils", "number of rows returned is" + row, null);
            }
        } catch (SQLException e) {
            Log.e("DatabaseUtils", "Error inserting into Db", e);
        }
        return row;
    }

    public static int saveVehicle(Vehicle vehicle, Connection connection) {
        String query = "INSERT INTO vehicle(vehicle_id,description,year,type,category)VALUES(?,?,?,?,?)";
        int row = 0;
        try {
            if (connection != null) {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, vehicle.getVehicleId());
                preparedStatement.setString(2, vehicle.getDesciption());
                preparedStatement.setInt(3, vehicle.getYear());
                preparedStatement.setString(4, vehicle.getType());
                preparedStatement.setString(5, vehicle.getCategory());
                row = preparedStatement.executeUpdate();
                String msg = String.valueOf(row);
                Log.d("DbUtils", "number of rows returned is" + msg, null);
            }
        } catch (SQLException e) {
            Log.e("DatabaseUtils", "Error inserting into Db", e);
        }
        return row;
    }

    public static List<Vehicle> getCarAvailability(BookingDto bookingDetails, Connection connection) {
        List<Vehicle> vehicles = new ArrayList<>();
        ResultSet rs = null;
        String query = "SELECT v.vehicle_id AS VIN, v.description,v.year FROM vehicle v LEFT JOIN rental r ON v.vehicle_id = r.vehicle_id WHERE (return_date < ? OR start_date > ?) AND v.type = ? AND v.category = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, bookingDetails.getBookDate());
            preparedStatement.setString(2, bookingDetails.getReturnDate());
            preparedStatement.setInt(3, Integer.parseInt(bookingDetails.getVehicleType()));
            preparedStatement.setInt(4, Integer.parseInt(bookingDetails.getVehicleType()));
            rs = preparedStatement.executeQuery();
            while (rs != null && rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleId(rs.getString(1));
                vehicle.setDesciption(rs.getString(2));
                vehicle.setYear(rs.getInt(3));
                vehicles.add(vehicle);
            }
            Log.d("DbUtils", "Car Availability Results" + vehicles, null);
        } catch (SQLException e) {
            Log.e("DatabaseUtils", "Error inserting into Db", e);
        }
        return vehicles;
    }


    public static int confirmBooking(BookingDto bookingDto) {
        RentalType modeOfRental = RentalType.DEFAULT;
        if(RentalType.DAILY.name().equals(bookingDto.getModeOfRental().toUpperCase())){
            modeOfRental = RentalType.DAILY;
        }else{
            modeOfRental = RentalType.WEEKLY;
        }
        String rateQuery = "SELECT "+modeOfRental.name()+" FROM rate WHERE type=? AND category=?";
        String query = "INSERT INTO rental VALUES(?,?,?,?,?,?,?,?,?)";
        int row = 0;

        try {
            connection = getDbConnection();
            if (connection != null) {
                PreparedStatement preparedStatementQ = connection.prepareStatement(rateQuery);
                preparedStatementQ.setString(1,bookingDto.getVehicleType());
                preparedStatementQ.setString(2,bookingDto.getRentalCategory());
                ResultSet rs = preparedStatementQ.executeQuery();
                Double rate=0.0;
                if(rs!=null){
                    rate = rs.next()?rs.getDouble(1):0;
                }
                String orderd_date = "2022-05-05";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, bookingDto.getUserId());
                preparedStatement.setString(2, bookingDto.getVehicleId());
                preparedStatement.setDate(3, Date.valueOf(bookingDto.getBookDate()));
                preparedStatement.setDate(4, Date.valueOf(orderd_date));
                preparedStatement.setString(5, modeOfRental.getValue().toString());
                preparedStatement.setInt(6, bookingDto.getQuantity());
                preparedStatement.setDate(7, Date.valueOf(bookingDto.getReturnDate()));
                preparedStatement.setDouble(8, rate*bookingDto.getQuantity());
                if (bookingDto.getPayment() == 0.0) {
                    preparedStatement.setDate(9, null);

                } else {
                    preparedStatement.setDate(9, Date.valueOf(orderd_date));
                }

                row = preparedStatement.executeUpdate();
                String msg = String.valueOf(row);
                Log.d("DbUtils", "number of rows returned is" + msg, null);
            }
        } catch (SQLException e) {
            Log.e("DatabaseUtils", "Error inserting into Db", e);
        }
        return row;

    }
}
