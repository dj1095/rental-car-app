package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.utils.BookingDto;
import com.example.myapplication.utils.DbUtils;

public class ConfirmBooking extends AppCompatActivity {

    private Button confirmBooking;
    private BookingDto bookingDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_booking);
        this.confirmBooking = findViewById(R.id.ConfirmBooking);
        this.confirmBooking.setOnClickListener(view -> {
            EditText userId = findViewById(R.id.userId);
            EditText vehicleId = findViewById(R.id.bknVehicleId);
            EditText startDate = findViewById(R.id.startDate);
            EditText returnDate = findViewById(R.id.bknreturnDate);
            EditText quantity = findViewById(R.id.bknQuantity);
            EditText bknWeeklyDaily = findViewById(R.id.bknWeeklyDaily);
            EditText amount = findViewById(R.id.amount);
            EditText category = findViewById(R.id.rcategory);
            EditText typeOfVehicle = findViewById(R.id.typeOfVehicle);
            bookingDto = new BookingDto();
            bookingDto.setUserId(userId.getText().toString());
            bookingDto.setVehicleId(vehicleId.getText().toString());
            bookingDto.setBookDate(startDate.getText().toString());
            bookingDto.setModeOfRental(bknWeeklyDaily.getText().toString());
            bookingDto.setQuantity(Integer.parseInt(quantity.getText().toString()));
            bookingDto.setReturnDate(returnDate.getText().toString());
            bookingDto.setPayment(Double.parseDouble(amount.getText().toString()));
            bookingDto.setRentalCategory(category.getText().toString());
            bookingDto.setVehicleType(typeOfVehicle.getText().toString());
            new InfoAsyncTask().execute();
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            return DbUtils.confirmBooking(bookingDto);
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result > 0) {
                Toast.makeText(ConfirmBooking.this, "Rental Created Successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ConfirmBooking.this, " Error! Renatal Not Added ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}