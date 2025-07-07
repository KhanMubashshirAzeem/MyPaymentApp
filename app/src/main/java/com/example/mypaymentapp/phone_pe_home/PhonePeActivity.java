package com.example.mypaymentapp.phone_pe_home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mypaymentapp.R;
import com.example.mypaymentapp.electricity.ElectricityActivity;
import java.util.ArrayList;
import java.util.List;

public class PhonePeActivity extends AppCompatActivity {

    RecyclerView moneyTransferRecyclerView, rechargeBillRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phone_pe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        moneyTransferRecyclerView = findViewById(R.id.moneyTransferRecyclerView);
        rechargeBillRecyclerView = findViewById(R.id.rechargeBillRecyclerView);

        List<RechargeModel> itemList = new ArrayList<>();
        itemList.add(new RechargeModel(R.drawable.to_mobile_svg, "To Mobile\nNumber"));
        itemList.add(new RechargeModel(R.drawable.bank_transfer_svg, "To Bank and\n self A/c"));
        itemList.add(new RechargeModel(R.drawable.refer_svg, "Refer & Earn \nUpto $200"));
        itemList.add(new RechargeModel(R.drawable.check_balance_svg, "Check\nBalance"));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        moneyTransferRecyclerView.setLayoutManager(layoutManager);
        moneyTransferRecyclerView.setNestedScrollingEnabled(false);


        MoneyTransferAdapter adapter = new MoneyTransferAdapter(this, itemList, item -> {
            Toast.makeText(PhonePeActivity.this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        });

        moneyTransferRecyclerView.setAdapter(adapter);


//        ========================


        List<RechargeBillModel> list = new ArrayList<>();
        list.add(new RechargeBillModel(R.drawable.recharge, "Recharge"));
        list.add(new RechargeBillModel(R.drawable.rent, "Rent"));
        list.add(new RechargeBillModel(R.drawable.electricity, "Electricity"));
        list.add(new RechargeBillModel(R.drawable.loan, "Loan EMI"));

        // Setup horizontal layout with 4 fixed items (no scrolling)
        GridLayoutManager layoutManager2 = new GridLayoutManager(this, 4);
        rechargeBillRecyclerView.setLayoutManager(layoutManager2);
        rechargeBillRecyclerView.setNestedScrollingEnabled(false);

        RechargeBillAdapter adapter2 = new RechargeBillAdapter(this, list, model -> {
            Toast.makeText(PhonePeActivity.this, "Clicked: " + model.getTitle(), Toast.LENGTH_SHORT).show();
            if (model.getTitle() == "Electricity") {
                Intent intent = new Intent(PhonePeActivity.this, ElectricityActivity.class);
                startActivity(intent);
            }
        });

        rechargeBillRecyclerView.setAdapter(adapter2);

    }
}