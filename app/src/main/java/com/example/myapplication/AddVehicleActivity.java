package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.models.Vehicle;
import com.example.myapplication.utils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddVehicleActivity extends AppCompatActivity {
    private Button addVehicleBtn;
    private Vehicle vehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        this.addVehicleBtn = findViewById(R.id.btnAddVehicle);
        this.addVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText vehicleId = findViewById(R.id.vehicleId);
                EditText vehicleDesc = findViewById(R.id.vehicleDesc);
                EditText vehicleYear = findViewById(R.id.vehicleYear);
                EditText vehicleType = findViewById(R.id.vehicleType);
                EditText vehicleCategory = findViewById(R.id.vehicleCategory);
                vehicle = new Vehicle();
                vehicle.setVehicleId(vehicleId.getText().toString());
                vehicle.setCategory(vehicleCategory.getText().toString());
                vehicle.setDesciption(vehicleDesc.getText().toString());
                vehicle.setYear(Integer.parseInt(vehicleYear.getText().toString()));
                vehicle.setType(vehicleType.getText().toString());
                new AddVehicleActivity.InfoAsyncTask().execute();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            Connection connection = DbUtils.getDbConnection();
            return DbUtils.saveVehicle(vehicle,connection);
            /*try (Connection connection = DriverManager.getConnection(DbUtils.URL, DbUtils.USER, DbUtils.PASSWORD)) {
                String query = "INSERT INTO vehicle(vehicle_id,description,year,type,category)VALUES(?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, vehicle.getVehicleId());
                preparedStatement.setString(2, vehicle.getDesciption());
                preparedStatement.setInt(3, vehicle.getYear());
                preparedStatement.setString(4, vehicle.getType());
                preparedStatement.setString(5, vehicle.getCategory());
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }
            return result;*/
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result > 0) {

            }
        }
    }
}