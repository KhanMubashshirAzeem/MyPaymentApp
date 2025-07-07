package com.example.mypaymentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.payment_sdk.PaymentProcessor;

public class MainFragment extends Fragment {

    EditText editAmount, editBiller;
    Button btnPay;
    int expectedAmount, expectedId;
    ProgressBar progressBar;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editAmount = view.findViewById(R.id.etAmount);
        editBiller = view.findViewById(R.id.etBiller);
        btnPay = view.findViewById(R.id.btnPay);
        progressBar = view.findViewById(R.id.progress_bar);

        // Retrieve values from arguments
        Bundle args = getArguments();
        if (args != null) {
            expectedAmount = args.getInt("amount", -1);
            expectedId = args.getInt("id", -1);
        }

        btnPay.setOnClickListener(v -> {
            String enteredAmountStr = editAmount.getText().toString().trim();
            String enteredIdStr = editBiller.getText().toString().trim();

            if (enteredAmountStr.isEmpty() || enteredIdStr.isEmpty()) {
                Toast.makeText(getContext(), "Please fill both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int enteredAmount = Integer.parseInt(enteredAmountStr);
                int enteredId = Integer.parseInt(enteredIdStr);

                if (enteredAmount == expectedAmount && enteredId == expectedId) {
                    progressBar.setVisibility(View.VISIBLE);

                    // Call PaymentProcessor directly instead of opening another Activity
                    PaymentProcessor.process(enteredAmountStr, enteredIdStr, status -> {
                        progressBar.setVisibility(View.GONE);

                        Intent i = new Intent(getActivity(), ReceiptActivity.class);
                        i.putExtra("status", status);
                        startActivity(i);
                        requireActivity().finish();
                    });

                } else {
                    if (enteredAmount != expectedAmount) {
                        editAmount.setError("Amount mismatch");
                    }
                    if (enteredId != expectedId) {
                        editBiller.setError("Service number mismatch");
                    }
                    Toast.makeText(getContext(), "Payment Failed", Toast.LENGTH_SHORT).show();
                }

            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid input format", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
