package com.example.payment_sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    EditText editAmount, editBiller;
    Button btnPay;
    ProgressBar progressBar;

    int expectedAmount, expectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAmount = findViewById(R.id.etAmount);
        editBiller = findViewById(R.id.etBiller);
        btnPay = findViewById(R.id.btnPay);
        progressBar = findViewById(R.id.progress_bar);

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
                    showPinDialog(enteredAmountStr, enteredIdStr);
                } else {
                    if (enteredAmount != expectedAmount) editAmount.setError("Amount mismatch");
                    if (enteredId != expectedId) editBiller.setError("Service number mismatch");

                    Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
                }

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid input format", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPinDialog(String amount, String biller) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_pin, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        EditText pin1 = view.findViewById(R.id.pin_one);
        EditText pin2 = view.findViewById(R.id.pin_two);
        EditText pin3 = view.findViewById(R.id.pin_three);
        EditText pin4 = view.findViewById(R.id.pin_four);
        Button confirmBtn = view.findViewById(R.id.btn_confirm);

        dialog.show();

        confirmBtn.setOnClickListener(v -> {
            String enteredPin = pin1.getText().toString().trim() + pin2.getText().toString().trim() + pin3.getText().toString().trim() + pin4.getText().toString().trim();

            if (enteredPin.equals("0123")) {
                dialog.dismiss();
                progressBar.setVisibility(View.VISIBLE);

                PaymentProcessor.process(amount, biller, status -> {
                    progressBar.setVisibility(View.GONE);

                    Intent i = new Intent();
                    i.setClassName("com.example.mypaymentapp", "com.example.mypaymentapp.ReceiptActivity");
                    i.putExtra("status", status);
                    startActivity(i);
                    finish();
                });
            } else {
                Toast.makeText(this, "Incorrect PIN", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
