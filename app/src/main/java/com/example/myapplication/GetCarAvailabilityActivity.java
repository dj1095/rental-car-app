package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.models.Vehicle;
import com.example.myapplication.utils.BookingDto;
import com.example.myapplication.utils.DbUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GetCarAvailabilityActivity extends AppCompatActivity {

    private Button getAvailaibility;
    private  BookingDto bookingDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_car_availability);
        this.getAvailaibility = findViewById(R.id.btnGetAvailiability);
        this.getAvailaibility.setOnClickListener(view -> {
            EditText rentalType = findViewById(R.id.rentalType);
            EditText rentalCategory = findViewById(R.id.rentalCategory);
            EditText bookDate = findViewById(R.id.bookDate);
            EditText returnDate = findViewById(R.id.returnDate);
            bookingDetails = new BookingDto();
            bookingDetails.setBookDate(bookDate.getText().toString());
            bookingDetails.setReturnDate(returnDate.getText().toString());
            bookingDetails.setRentalCategory(rentalCategory.getText().toString());
            bookingDetails.setVehicleType(rentalType.getText().toString());
            new InfoAsyncTask().execute();
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, List<Vehicle>> {
        @Override
        protected List<Vehicle> doInBackground(Void... voids) {
            Connection connection = DbUtils.getDbConnection();
            return DbUtils.getCarAvailability(bookingDetails,connection);
        }

        @Override
        protected void onPostExecute(List<Vehicle> result) {
            if (result.size() > 0) {
                Intent intent = new Intent(GetCarAvailabilityActivity.this, CreateBookingActivity.class);
                intent.putExtra("AvailableVehicles",(ArrayList<Vehicle>)result);
                startActivity(intent);
            }
        }
    }

}