package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CreateBookingActivity extends AppCompatActivity implements RecyclerViewInterface {

    private Button createBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);
        List<Vehicle> vehicles = (ArrayList<Vehicle>)this.getIntent().getSerializableExtra("AvailableVehicles");
        Log.d("CreateBookingActivity","list size is "+vehicles.size());
        InitializeCardView(vehicles);
        this.createBooking = findViewById(R.id.btnBookCar);
        this.createBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateBookingActivity.this, ConfirmBooking.class);
                startActivity(intent);
            }
        });
    }

    private void InitializeCardView(List<Vehicle> vehicles) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        BookingViewAdapter adapter = new BookingViewAdapter(this, vehicles, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onItenClick(int position) {
        Log.d("CreateBookingActivity","On Click Listener ",null);
        Intent intent = new Intent(CreateBookingActivity.this, ConfirmBooking.class);
        startActivity(intent);

    }
}