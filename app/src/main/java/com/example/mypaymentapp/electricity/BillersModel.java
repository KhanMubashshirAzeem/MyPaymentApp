package com.example.mypaymentapp.electricity;

public class BillersModel {
    int image;
    String title;

    public BillersModel(int biller_logo, String biller_title) {
        this.image = biller_logo;
        this.title = biller_title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
