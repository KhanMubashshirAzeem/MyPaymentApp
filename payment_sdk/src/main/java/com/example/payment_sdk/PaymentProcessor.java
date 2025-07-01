package com.example.payment_sdk;

import java.util.Random;

public class PaymentProcessor {

    // Simulates processing a payment
    public static String processPayment(String amount, String biller) {
        // Simulate delay, success or failure
        boolean success = new Random().nextBoolean();
        return success ? "Payment Successful\nAmount: ₹" + amount + "\nBiller: " + biller
                : "Payment Failed\nAmount: ₹" + amount + "\nBiller: " + biller;
    }
}
