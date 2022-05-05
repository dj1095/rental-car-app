package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Button addCustomerBtn;
    private Button addVehicleBtn;
    private Button createBookingBtn;
    private Button usrDetailsBtn;
    private Button vhlDetailsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.addCustomerBtn = findViewById(R.id.btnMainAddCustomer);
        this.addVehicleBtn = findViewById(R.id.btnMainAddVehicle);
        this.createBookingBtn = findViewById(R.id.btnMainCreateBooking);
        //this.usrDetailsBtn = findViewById(R.id.usrDetailsBtn);
        //this.vhlDetailsBtn = findViewById(R.id.vhlDetailsBtn);
        this.addCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCustomerActivity.class);
                startActivity(intent);
            }
        });
        this.addCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCustomerActivity.class);
                startActivity(intent);
            }
        });
        this.addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddVehicleActivity.class);
                startActivity(intent);
            }
        });
        this.createBookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GetCarAvailabilityActivity.class);
                startActivity(intent);
            }
        });

    }

}