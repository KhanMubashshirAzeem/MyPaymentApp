package com.example.mypaymentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.payment_sdk.PaymentSDKActivity;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity {

    EditText editAmount, editBiller;
    Button btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAmount = findViewById(R.id.etAmount);
        editBiller = findViewById(R.id.etBiller);
        btnPay = findViewById(R.id.btnPay);

        btnPay.setOnClickListener(v -> {
            String amount = editAmount.getText().toString();
            String biller = editBiller.getText().toString();

            // Start SDK activity and pass data
            Intent intent = new Intent(MainActivity.this, PaymentSDKActivity.class);
            intent.putExtra("amount", amount);
            intent.putExtra("biller", biller);
            startActivityForResult(intent, 101); // wait for result
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && data != null) {
            String status = data.getStringExtra("status");

            Intent i = new Intent(this, ReceiptActivity.class);
            i.putExtra("status", status);
            startActivity(i);
        }
    }
}


