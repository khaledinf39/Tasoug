package com.kh_sof_dev.tasoug.Model.Classes;

import com.kh_sof_dev.tasoug.Controule.Info.Store_info;

public class Box {
    private  String date,ID,StoreID;
    private Double price;

    public Box(String date, Double price) {
        this.date = date;
        StoreID = Store_info.storeID;
        this.price = price;
    }

    public Box() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
