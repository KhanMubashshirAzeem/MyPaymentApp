package com.example.mypaymentapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReceiptActivity extends AppCompatActivity {

    TextView receipt;
    TextView date_time;
    ImageView paymentComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        date_time = findViewById(R.id.date_time_pay);
        paymentComp = findViewById(R.id.payment_complete_icon);
        PaymentCompleteAnimation();

        getDateTime();

        receipt = findViewById(R.id.receipt);
        String status = getIntent().getStringExtra("status");
        receipt.setText(status);
    }

    private void PaymentCompleteAnimation() {
        ImageView paymentComp = findViewById(R.id.payment_complete_icon);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.payment_complete_anim);
        paymentComp.startAnimation(animation);
        paymentComp.setVisibility(View.VISIBLE);
    }

    private void getDateTime() {
        // Create date and time formatters with locale
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss z", Locale.getDefault());

        // Format current date and time
        String currentDateString = dateFormat.format(new Date());
        String currentTimeString = timeFormat.format(new Date());

        // Set formatted text to TextViews
        date_time.setText("Time: " + currentTimeString + "\nDate: " + currentDateString);
    }
}
