package com.kh_sof_dev.tasoug.Model.Classes;

import com.kh_sof_dev.tasoug.Controule.Routes.Electronique_bayment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Electronique_payment {



    private String _id,orderID,storeID,date_time;
    private Double price,toutal=0.0;
private List<Electronique_payment> electronique_paymentList;
    public Electronique_payment(JSONObject elc) {
        if (elc==null){
            return;
        }
       try {
           electronique_paymentList=new ArrayList<>();
           JSONArray jsonArray=elc.getJSONArray("doc");
           for (int i =0;i<jsonArray.length();i++){
               Electronique_payment electronique_bayment=new Electronique_payment();
            electronique_bayment. price=jsonArray.getJSONObject(i).getDouble("price");
               electronique_bayment.date_time=jsonArray.getJSONObject(i).getString("date_time");
               electronique_bayment. _id=jsonArray.getJSONObject(i).getString("_id");
               electronique_bayment.orderID=jsonArray.getJSONObject(i).getString("orderID");
               electronique_bayment. storeID=jsonArray.getJSONObject(i).getString("storeID");
               toutal+=electronique_bayment.price;
               electronique_paymentList.add(electronique_bayment);
           }
       } catch (JSONException e) {
           e.printStackTrace();
       }
    }

    public Double getToutal() {
        return toutal;
    }

    public void setToutal(Double toutal) {
        this.toutal = toutal;
    }

    public List<Electronique_payment> getElectronique_paymentList() {
        return electronique_paymentList;
    }

    public void setElectronique_paymentList(List<Electronique_payment> electronique_paymentList) {
        this.electronique_paymentList = electronique_paymentList;
    }

    public Electronique_payment() {

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
