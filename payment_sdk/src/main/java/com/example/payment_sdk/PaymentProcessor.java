package com.example.payment_sdk;

import android.os.Handler;

public class PaymentProcessor {

    // Callback interface to return the result
    public interface PaymentCallback {
        void onResult(String status);
    }

    // Simulated payment processing method
    public static void process(String amount, String biller, PaymentCallback callback) {
        // Simulate a 2-second delay to mimic payment processing
        new Handler().postDelayed(() -> {
            String result = "Payment of ₹" + amount + "\nprocessed successfully";
            callback.onResult(result);
        }, 2000);
    }
}
