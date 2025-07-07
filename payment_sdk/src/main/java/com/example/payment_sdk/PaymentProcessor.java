package com.example.payment_sdk;

import android.os.Handler;

public class PaymentProcessor {

    public interface PaymentCallback {
        void onResult(String status);
    }

    public static void process(String amount, String biller, PaymentCallback callback) {
        // Simulate a delay (like real payment processing)
        new Handler().postDelayed(() -> {
            String result = "Payment of ₹" + amount + "\nprocessed successfully";
            callback.onResult(result);
        }, 2000); // 2-second delay
    }
}
