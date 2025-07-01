package com.example.mypaymentapp;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeActivityPhonePe extends AppCompatActivity {

    private GridView gridView;
    private GridView gridView2;
    private ImageView imageView;
    util u = new util();  // Assuming this holds image URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_phone_pe);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();         // Initialize UI elements
        loadBannerImage();   // Load banner image via Glide
        setupFirstGrid();    // Setup first GridView
        setupSecondGrid();   // Setup second GridView
    }

    // Initializes all views
    private void initViews() {
        imageView = findViewById(R.id.IVadvertising);
        gridView = findViewById(R.id.grid_view);
        gridView2 = findViewById(R.id.grid_view_2);
    }

    // Load image using Glide
    private void loadBannerImage() {
        Glide.with(this)
                .load(u.url.toString())  // assuming u.url is a valid URL string
                .into(imageView);
    }

    // Sets data and adapter for first GridView
    private void setupFirstGrid() {
        ArrayList<ModelGridView> list = new ArrayList<>();
        list.add(new ModelGridView("To Mobile Transfer", R.drawable.to_mobile_svg));
        list.add(new ModelGridView("To Bank & self A/C", R.drawable.bank_transfer_svg));
        list.add(new ModelGridView("Refer", R.drawable.refer_svg));
        list.add(new ModelGridView("Check Balance", R.drawable.check_balance_svg));

        AdapterGridView adapter = new AdapterGridView(this, list);
        gridView.setAdapter(adapter);
    }

    // Sets data and adapter for second GridView
    private void setupSecondGrid() {
        ArrayList<ModelGridView2> list2 = new ArrayList<>();
        list2.add(new ModelGridView2("To Mobile Transfer", R.drawable.to_mobile_svg));
        list2.add(new ModelGridView2("To Bank & self A/C", R.drawable.bank_transfer_svg));
        list2.add(new ModelGridView2("Refer", R.drawable.refer_svg));
        list2.add(new ModelGridView2("Check Balance", R.drawable.check_balance_svg));

        AdapterGridView2 adapter2 = new AdapterGridView2(this, list2);
        gridView2.setAdapter(adapter2);
    }
}
