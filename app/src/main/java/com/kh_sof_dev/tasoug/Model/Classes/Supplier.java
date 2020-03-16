package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Supplier {
    private  String name,phone,password,email,ID,image,coumerce_name,coumerceID,name_en;
    private  Double lat,lng,scoure=0.0;
private  Boolean status;
    public Supplier(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null){
            return;
        }

        name=jsonObject.getString("name");
        name_en=jsonObject.getString("name_en");
        coumerce_name=jsonObject.getString("coumerce_name");
        coumerceID=jsonObject.getString("coumerceID");
        phone=jsonObject.getString("phone");
        password=jsonObject.getString("password");
        email=jsonObject.getString("email");
        image=jsonObject.getString("image");
        lat=jsonObject.getDouble("lat");
        lng=jsonObject.getDouble("lng");
        status=jsonObject.getBoolean("status");

    }

    public Boolean getStatus() {
        return status;
    }

    public String getCoumerce_name() {
        return coumerce_name;
    }

    public void setCoumerce_name(String coumerce_name) {
        this.coumerce_name = coumerce_name;
    }

    public String getCoumerceID() {
        return coumerceID;
    }

    public void setCoumerceID(String coumerceID) {
        this.coumerceID = coumerceID;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public Double getScoure() {
        return scoure;
    }

    public void setScoure(Double scoure) {
        this.scoure = scoure;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
