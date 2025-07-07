package com.example.mypaymentapp.electricity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypaymentapp.MainActivity;
import com.example.mypaymentapp.R;

import java.util.ArrayList;
import java.util.List;

public class ElectricityActivity extends AppCompatActivity {

    SearchView searchView;
    View view;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_electricity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        view = findViewById(R.id.electricity_card);
        searchView = findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.biller_recyclerview);

        searchViewFun();
        itemClickFunction();
        setRecyclerView();

    }

    private void setRecyclerView() {
        List<BillersModel> list = new ArrayList<>();
        list.add(new BillersModel(R.drawable.torrent_power_logo, "Torrent Power"));
        list.add(new BillersModel(R.drawable.adani_elec_mum, "Adani Electricity Mumbai Limited (AEML)"));
        list.add(new BillersModel(R.drawable.maha_elec_mum, "MSEDCL Mahavitaran = Maharashtra"));
        list.add(new BillersModel(R.drawable.tata_elec_mum, "Tata Power - Mumbai"));
        list.add(new BillersModel(R.drawable.best_elec_mum, "Best Mumbai - Brihanmumbai Electricity"));
        list.add(new BillersModel(R.drawable.torrent_power_logo, "Torrent Power"));
        list.add(new BillersModel(R.drawable.adani_elec_mum, "Adani Electricity Mumbai Limited (AEML)"));
        list.add(new BillersModel(R.drawable.maha_elec_mum, "MSEDCL Mahavitaran = Maharashtra"));
        list.add(new BillersModel(R.drawable.tata_elec_mum, "Tata Power - Mumbai"));
        list.add(new BillersModel(R.drawable.best_elec_mum, "Best Mumbai - Brihanmumbai Electricity"));
        list.add(new BillersModel(R.drawable.torrent_power_logo, "Torrent Power"));
        list.add(new BillersModel(R.drawable.adani_elec_mum, "Adani Electricity Mumbai Limited (AEML)"));
        list.add(new BillersModel(R.drawable.maha_elec_mum, "MSEDCL Mahavitaran = Maharashtra"));
        list.add(new BillersModel(R.drawable.tata_elec_mum, "Tata Power - Mumbai"));
        list.add(new BillersModel(R.drawable.best_elec_mum, "Best Mumbai - Brihanmumbai Electricity"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        BillersAdapter adapter = new BillersAdapter(list, this, model -> {
            Toast.makeText(this, "Clicked: " + model.getTitle(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
    }

    private void itemClickFunction() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectricityActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    // No login just function is added
    private void searchViewFun() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


}