package com.example.payment_sdk;

public class PaymentProcessor {

    public static String processPayment(String amount, String biller) {
        // Simulate success always for matching details
        return "Payment of \t₹" + amount + "\nprocessed successfully";
    }
}
