package com.example.mypaymentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterGridView2 extends ArrayAdapter<ModelGridView2> {

    // Constructor to pass context and data list to parent class
    public AdapterGridView2(Context context, ArrayList<ModelGridView2> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        ModelGridView2 model = getItem(position);

        TextView textView = itemView.findViewById(R.id.text_view);
        ImageView imageView = itemView.findViewById(R.id.image_view);

        if (model != null) {
            textView.setText(model.getName());
            imageView.setImageResource(model.getImage());
        }

        return itemView;
    }
}
