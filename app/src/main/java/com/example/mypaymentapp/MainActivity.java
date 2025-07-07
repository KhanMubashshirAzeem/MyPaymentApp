package com.example.mypaymentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payment_sdk.PaymentSDKActivity;

public class MainActivity extends AppCompatActivity {

    EditText editAmount, editBiller;
    Button btnPay;
    int expectedAmount, expectedId;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAmount = findViewById(R.id.etAmount);
        editBiller = findViewById(R.id.etBiller);
        btnPay = findViewById(R.id.btnPay);
        progressBar = findViewById(R.id.progress_bar);


        // Get data from ElectricityActivity
        Intent intent = getIntent();
        expectedAmount = intent.getIntExtra("amount", -1);
        expectedId = intent.getIntExtra("id", -1);

        btnPay.setOnClickListener(v -> {
            String enteredAmountStr = editAmount.getText().toString().trim();
            String enteredIdStr = editBiller.getText().toString().trim();

            if (enteredAmountStr.isEmpty() || enteredIdStr.isEmpty()) {
                Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int enteredAmount = Integer.parseInt(enteredAmountStr);
                int enteredId = Integer.parseInt(enteredIdStr);

                if (enteredAmount == expectedAmount && enteredId == expectedId) {
                    Intent payIntent = new Intent(MainActivity.this, PaymentSDKActivity.class);
                    progressBar.setVisibility(View.VISIBLE); // Show ProgressBar
                    new android.os.Handler().postDelayed(() -> progressBar.setVisibility(View.GONE), 2000); // Hide after 2 sec
                    payIntent.putExtra("amount", enteredAmountStr);
                    payIntent.putExtra("biller", enteredIdStr);
                    startActivityForResult(payIntent, 101);
                } else {
                    if (enteredAmount != expectedAmount) {
                        editAmount.setError("Amount mismatch");
                    }
                    if (enteredId != expectedId) {
                        editBiller.setError("Service number mismatch");
                    }
                    Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
                }

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid input format", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            String status = data.getStringExtra("status");

            Intent i = new Intent(this, ReceiptActivity.class);
            i.putExtra("status", status);
            startActivity(i);
            finish();
        }
    }


}
