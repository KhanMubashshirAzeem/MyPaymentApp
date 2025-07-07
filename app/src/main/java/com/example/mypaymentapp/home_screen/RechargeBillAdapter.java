package com.example.mypaymentapp.home_screen;

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

public class RechargeBillAdapter extends RecyclerView.Adapter<RechargeBillAdapter.ViewHolder> {

    private final List<RechargeBillModel> itemList;
    private final Context context;
    private final OnItemClickListener listener;


    public interface OnItemClickListener {
        void onClick(RechargeBillModel model);
    }


    public RechargeBillAdapter(Context context, List<RechargeBillModel> itemList, OnItemClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView titleText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImage);
            titleText = itemView.findViewById(R.id.titleText);
        }

        public void bind(RechargeBillModel model, OnItemClickListener listener) {
            iconImage.setImageResource(model.getIconResId());
            titleText.setText(model.getTitle());
            itemView.setOnClickListener(v -> listener.onClick(model));
        }
    }

    @NonNull
    @Override
    public RechargeBillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RechargeBillAdapter.ViewHolder holder, int position) {
        holder.bind(itemList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

