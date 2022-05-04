package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models.Customer;
import com.example.myapplication.utils.DbUtils;

import java.sql.Connection;

public class AddCustomerActivity extends AppCompatActivity {

    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        Button addCustTxtButton = findViewById(R.id.addCustTxtBtn);
        addCustTxtButton.setOnClickListener(view -> {
            EditText custIdEditText = findViewById(R.id.customerIdEdText);
            EditText custnameEditText = findViewById(R.id.custnameEditText);
            EditText custPhnEditTxt = findViewById(R.id.custPhnEditTxt);
            customer = new Customer();
            customer.setCustomerId(Integer.parseInt(custIdEditText.getText().toString()));
            customer.setCustomerName(custnameEditText.getText().toString());
            customer.setPhoneNumber(custPhnEditTxt.getText().toString());
            new InfoAsyncTask().execute();
        });
    }


    @SuppressLint("StaticFieldLeak")
    public class InfoAsyncTask extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... voids) {
            /*Integer result = 0;
            try (Connection connection = DriverManager.getConnection(DbUtils.URL, DbUtils.USER, DbUtils.PASSWORD)) {
                String query = "INSERT INTO customer(cust_id,name,phone)VALUES(?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, customer.getCustomerId());
                preparedStatement.setString(2, customer.getCustomerName());
                preparedStatement.setString(3, customer.getPhoneNumber());
                result = preparedStatement.executeUpdate();
            } catch (Exception e) {
                Log.e("InfoAsyncTask", "Error reading school information", e);
            }*/
            Connection connection = DbUtils.getDbConnection();
            return DbUtils.saveCustomer(customer,connection);
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result > 0) {
                Toast.makeText(AddCustomerActivity.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(AddCustomerActivity.this, " Error! User Not Added ", Toast.LENGTH_SHORT).show();
            }
        }
    }

}