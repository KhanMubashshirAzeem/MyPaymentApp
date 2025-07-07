package com.example.mypaymentapp.electricity_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypaymentapp.R;
import com.example.payment_sdk.MainActivity; // ✅ SDK's MainActivity

import java.util.ArrayList;
import java.util.List;

public class ElectricityActivity extends AppCompatActivity {

    SearchView searchView;
    View view;
    RecyclerView recyclerView;
    TextView customer_id, customer_amount;
    List<BillersModel> list = new ArrayList<>();
    BillersAdapter adapter;

    int id = 12131415;
    int amount = 5400;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_electricity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        view = findViewById(R.id.electricity_card);
        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.biller_recyclerview);
        customer_id = findViewById(R.id.customer_id);
        customer_amount = findViewById(R.id.customer_bill_amount);

        customer_id.setText(String.valueOf(id));
        customer_amount.setText("₹" + amount + "\t\t\tlast due on");

        searchViewFun();
        itemClickFunction();
        setRecyclerView();
    }

    private void setRecyclerView() {
        list.add(new BillersModel(R.drawable.torrent_power_logo, "Torrent Power"));
        list.add(new BillersModel(R.drawable.adani_elec_mum, "Adani Electricity Mumbai"));
        list.add(new BillersModel(R.drawable.tata_elec_mum, "Tata Power - Mumbai"));
        list.add(new BillersModel(R.drawable.best_elec_mum, "BEST Mumbai - Brihanmumbai"));
        list.add(new BillersModel(R.drawable.maha_elec_mum, "MSEDCL Mahavitaran - Maharashtra"));

        list.add(new BillersModel(R.drawable.torrent_power_logo, "Torrent Power"));
        list.add(new BillersModel(R.drawable.adani_elec_mum, "Adani Electricity Mumbai"));
        list.add(new BillersModel(R.drawable.tata_elec_mum, "Tata Power - Mumbai"));
        list.add(new BillersModel(R.drawable.best_elec_mum, "BEST Mumbai - Brihanmumbai"));
        list.add(new BillersModel(R.drawable.maha_elec_mum, "MSEDCL Mahavitaran - Maharashtra"));

        list.add(new BillersModel(R.drawable.torrent_power_logo, "Torrent Power"));
        list.add(new BillersModel(R.drawable.adani_elec_mum, "Adani Electricity Mumbai"));
        list.add(new BillersModel(R.drawable.tata_elec_mum, "Tata Power - Mumbai"));
        list.add(new BillersModel(R.drawable.best_elec_mum, "BEST Mumbai - Brihanmumbai"));
        list.add(new BillersModel(R.drawable.maha_elec_mum, "MSEDCL Mahavitaran - Maharashtra"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BillersAdapter(list, this, model -> {
            Toast.makeText(this, "Clicked: " + model.getTitle(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
    }

    private void itemClickFunction() {
        view.setOnClickListener(v -> {
            Intent intent = new Intent(ElectricityActivity.this, MainActivity.class);
            intent.putExtra("amount", amount);
            intent.putExtra("id", id);
            startActivity(intent);
            finish(); // Optional: Close this activity after starting SDK's MainActivity
        });
    }

    private void searchViewFun() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
