package com.example.mypaymentapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiptActivity extends AppCompatActivity {

    TextView receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt); // This must match the layout file name

        receipt = findViewById(R.id.receipt); // Make sure this ID matches the XML
        String status = getIntent().getStringExtra("status");
        receipt.setText(status);
    }
}
