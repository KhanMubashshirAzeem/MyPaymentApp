package com.example.payment_sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class PaymentSDKActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String amount = getIntent().getStringExtra("amount");
        String biller = getIntent().getStringExtra("biller");

        // Use processor
        String result = PaymentProcessor.processPayment(amount, biller);

        // Send back to host app
        Intent intent = new Intent();
        intent.putExtra("status", result);
        setResult(RESULT_OK, intent);
        finish();
    }
}