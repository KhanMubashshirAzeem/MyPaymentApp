package com.example.mypaymentapp.phone_pe_home;

public class RechargeModel {
    int iconResId;
    String title;

    public RechargeModel(int iconResId, String title) {
        this.iconResId = iconResId;
        this.title = title;
    }

    public int getIconResId() {
        return iconResId;
    }

    public String getTitle() {
        return title;
    }
}
