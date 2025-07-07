package com.example.mypaymentapp.phone_pe_home;

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

public class MoneyTransferAdapter extends RecyclerView.Adapter<MoneyTransferAdapter.ViewHolder> {

    private Context context;
    private List<RechargeModel> itemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(RechargeModel item);
    }

    public MoneyTransferAdapter(Context context, List<RechargeModel> itemList, OnItemClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView titleText;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImage);
            titleText = itemView.findViewById(R.id.titleText);
        }

        public void bind(RechargeModel item, OnItemClickListener listener) {
            iconImage.setImageResource(item.getIconResId());
            titleText.setText(item.getTitle());
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    @NonNull
    @Override
    public MoneyTransferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyTransferAdapter.ViewHolder holder, int position) {
        holder.bind(itemList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

