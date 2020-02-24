package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private  String name,phone,password,email,ID,image;
    private  Double lat,lng;
private  Boolean status;
    public User(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null){
            return;
        }

        name=jsonObject.getString("name");
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
