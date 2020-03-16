package com.kh_sof_dev.tasoug.Model.Classes;

import com.kh_sof_dev.tasoug.Controule.Info.Store_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private  String date,ID,StoreID;
    private Double price;
private List<Box> boxList;
    public Box(String date, Double price) {
        this.date = date;
        StoreID = Store_info.storeID;
        this.price = price;
    }

    public Box() {
    }

    public Box(JSONObject box)  {
        if (box==null){
            return;
        }
        boxList=new ArrayList<>();
        try{
            JSONArray jsonArray=box.getJSONArray("boxes");
            for (int i=0;i<jsonArray.length();i++){
                Box box1=new Box();
                box1.date=jsonArray.getJSONObject(i).getString("date_time");
                box1.price= jsonArray.getJSONObject(i).getDouble("price");
                boxList.add(box1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public List<Box> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<Box> boxList) {
        this.boxList = boxList;
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
