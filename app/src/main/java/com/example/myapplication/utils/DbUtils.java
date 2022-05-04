package com.example.myapplication.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.myapplication.models.Customer;
import com.example.myapplication.models.Vehicle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SuppressLint("StaticFieldLeak")
public class DbUtils {

    public static final String URL = "jdbc:mysql://51.81.160.154:3306/dxj0015_rental_car";
    //private static final String URL = "jdbc:mysql://10.182.17.65:3306/car_rental";
    public static final String USER = "dxj0015_pramodh";
    public static final String PASSWORD = "WCHtukx43RzwWMg";
    public static Connection connection = null;

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

}
