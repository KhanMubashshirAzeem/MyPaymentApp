package com.example.mypaymentapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        receipt = findViewById(R.id.receipt);
        FloatingActionButton shareBtn = findViewById(R.id.floating_share_btn);

        PaymentCompleteAnimation();
        getDateTime();

        String status = getIntent().getStringExtra("status");
        receipt.setText(status);

        shareBtn.setOnClickListener(v -> shareReceiptScreenshot());
    }

    private void PaymentCompleteAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.payment_complete_anim);
        paymentComp.startAnimation(animation);
        paymentComp.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss z", Locale.getDefault());

        String currentDateString = dateFormat.format(new Date());
        String currentTimeString = timeFormat.format(new Date());

        date_time.setText("Time: " + currentTimeString + "\nDate: " + currentDateString);
    }

    private void shareReceiptScreenshot() {
        View view = findViewById(R.id.main); // Root layout

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        try {
            // Save to Pictures/MyReceipts/
            File picturesDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyReceipts");
            if (!picturesDir.exists()) picturesDir.mkdirs();

            File file = new File(picturesDir, "receipt_" + System.currentTimeMillis() + ".png");

            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

            // Make image appear in gallery
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file);
            mediaScanIntent.setData(contentUri);
            sendBroadcast(mediaScanIntent);

            // ✅ Only show a single Toast
            Toast.makeText(this, "Image saved in gallery", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }


}
