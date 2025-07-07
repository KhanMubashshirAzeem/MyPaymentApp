package com.example.payment_sdk;

import java.util.Random;

public class PaymentProcessor {

    public static String processPayment(String amount, String biller) {
        // Simulate success always for matching details
        return "Payment Successful\nAmount: ₹" + amount + "\nBiller ID: " + biller;
    }
}
