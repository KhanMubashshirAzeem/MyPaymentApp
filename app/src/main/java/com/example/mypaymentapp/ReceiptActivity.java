package com.example.mypaymentapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReceiptActivity extends AppCompatActivity {

    TextView receipt;
    TextView date_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt); // This must match the layout file name

        date_time = findViewById(R.id.date_time_pay);
        getDateTime();

        receipt = findViewById(R.id.receipt); // Make sure this ID matches the XML
        String status = getIntent().getStringExtra("status");
        receipt.setText(status);
    }

    private void getDateTime() {
        // Create date and time formatters with locale
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss z", Locale.getDefault());

        // Format current date and time
        String currentDateString = dateFormat.format(new Date());
        String currentTimeString = timeFormat.format(new Date());

        // Set formatted text to TextViews
        date_time.setText("Time: " + currentTimeString + "\nDate: " + currentDateString);
    }
}
