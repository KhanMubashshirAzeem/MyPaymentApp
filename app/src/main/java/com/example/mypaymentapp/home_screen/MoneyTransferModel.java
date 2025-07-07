package com.example.mypaymentapp.home_screen;

public class MoneyTransferModel {
    int iconResId;
    String title;

    public MoneyTransferModel(int iconResId, String title) {
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
