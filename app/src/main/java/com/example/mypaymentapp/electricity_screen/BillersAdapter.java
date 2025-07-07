package com.example.mypaymentapp.electricity_screen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypaymentapp.R;

import java.util.List;

public class BillersAdapter extends RecyclerView.Adapter<BillersAdapter.ViewHolder> {

    private final List<BillersModel> itemList;
    private final Context context;
    private final OnItemClickListener listener;



    public interface OnItemClickListener {
        void onClick(BillersModel model);
    }

    public BillersAdapter(List<BillersModel> itemList, Context context, OnItemClickListener listener) {
        this.itemList = itemList;
        this.context = context;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView titleText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.icon_biller);
            titleText = itemView.findViewById(R.id.title_biller);
        }

        public void bind(BillersModel model, OnItemClickListener listener){
            iconImage.setImageResource(model.getImage());
            titleText.setText(model.getTitle());
            itemView.setOnClickListener(view -> listener.onClick(model));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.billers_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(itemList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
