package com.example.payment_sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends Activity {

    EditText editAmount, editBiller;
    Button btnPay;
    ProgressBar progressBar;

    int expectedAmount, expectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getInsets(WindowInsetsCompat.Type.systemBars()).left, insets.getInsets(WindowInsetsCompat.Type.systemBars()).top, insets.getInsets(WindowInsetsCompat.Type.systemBars()).right, insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });

        editAmount = findViewById(R.id.etAmount);
        editBiller = findViewById(R.id.etBiller);
        btnPay = findViewById(R.id.btnPay);
        progressBar = findViewById(R.id.progress_bar);

        // Get expected values from the intent
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
                    progressBar.setVisibility(View.VISIBLE);

                    PaymentProcessor.process(enteredAmountStr, enteredIdStr, status -> {
                        progressBar.setVisibility(View.GONE);

                        Intent i = new Intent();
                        i.setClassName("com.example.mypaymentapp", "com.example.mypaymentapp.ReceiptActivity");
                        i.putExtra("status", status);
                        startActivity(i);
                        finish();
                    });

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
}
