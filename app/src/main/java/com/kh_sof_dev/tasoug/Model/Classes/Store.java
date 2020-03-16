package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Store {

    private String _id,phone,email,FCMtoken,store_name_ar,store_name_en,user_name,commerceID,website,date_ins,password,image
            ,address;
    private  Boolean status;
    private Double lat,lng;
    private String token;

    public Store() {
    }

    public Store(JSONObject jsonObject_)  {

        if(jsonObject_ == null){
            return;
        }
        try {
            token=jsonObject_.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {

           JSONObject jsonObject= jsonObject_.getJSONObject("store");


           _id=jsonObject.getString("_id");
           phone=jsonObject.getString("phone");
           email=jsonObject.getString("email");
           FCMtoken=jsonObject.getString("FCMtoken");
        store_name_ar=jsonObject.getString("store_name_ar");
        store_name_en=jsonObject.getString("store_name_en");
        user_name=jsonObject.getString("user_name");
        commerceID=jsonObject.getString("commerceID");
//        website=jsonObject.getString("website");
//        date_ins=jsonObject.getString("date_ins");
        password=jsonObject.getString("password");
//        image=jsonObject.getString("image");
           lat=jsonObject.getDouble("lat");
           lng=jsonObject.getDouble("lng");
           status=jsonObject.getBoolean("status");
       } catch (JSONException e) {
           e.printStackTrace();
       }


    }

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id", _id);
            jsonObject.put("user_name", user_name);
            jsonObject.put("store_name_ar", store_name_ar);
            jsonObject.put("store_name_en", store_name_en);
            jsonObject.put("phone", phone);
            jsonObject.put("email", email);
            jsonObject.put("commerceID", commerceID);
            jsonObject.put("image", image);
            jsonObject.put("address", address);


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String get_id() {
        return _id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFCMtoken() {
        return FCMtoken;
    }

    public void setFCMtoken(String FCMtoken) {
        this.FCMtoken = FCMtoken;
    }

    public String getStore_name_ar() {
        return store_name_ar;
    }

    public void setStore_name_ar(String store_name_ar) {
        this.store_name_ar = store_name_ar;
    }

    public String getStore_name_en() {
        return store_name_en;
    }

    public void setStore_name_en(String store_name_en) {
        this.store_name_en = store_name_en;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCommerceID() {
        return commerceID;
    }

    public void setCommerceID(String commerceID) {
        this.commerceID = commerceID;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDate_ins() {
        return date_ins;
    }

    public void setDate_ins(String date_ins) {
        this.date_ins = date_ins;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
