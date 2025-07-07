package com.example.mypaymentapp.phone_pe_home;

public class RechargeBillModel {
    private int iconResId;
    private String title;

    public RechargeBillModel(int iconResId, String title) {
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
