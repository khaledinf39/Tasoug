package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private  String name,phone,password,email,ID,image,coumerce_name,coumerceID,name_en,create_at,address;
    private  Double lat,lng,scoure=0.0;
private  Boolean status;

private List<Supplier> suppliers;
    public Supplier(JSONObject jsonObject_) throws JSONException {
        if(jsonObject_ == null){
            return;
        }

        try{
            JSONArray jsonArray=jsonObject_.getJSONArray("suppliers");
            suppliers=new ArrayList<>();
            for(int i=0;i<jsonArray.length(); i++){
                Supplier user=new Supplier();
                user. ID=jsonArray.getJSONObject(i).getString("_id");

                user. phone=jsonArray.getJSONObject(i).getString("phone");
                user.password=jsonArray.getJSONObject(i).getString("password");
                user.  lat=jsonArray.getJSONObject(i).getDouble("lat");
                user.  lng=jsonArray.getJSONObject(i).getDouble("lng");

                user. status=jsonArray.getJSONObject(i).getBoolean("status");
                user. create_at=jsonArray.getJSONObject(i).getString("create_at");
                user. coumerce_name=jsonArray.getJSONObject(i).getString("commerce_name");
                user. scoure=jsonArray.getJSONObject(i).getDouble("scour");

                try {
                    user. address=jsonArray.getJSONObject(i).getString("address");
                    user.name=jsonArray.getJSONObject(i).getString("name");

                    user. email=jsonArray.getJSONObject(i).getString("email");
                    user. image=jsonArray.getJSONObject(i).getString("image");
                }catch (Exception e){
                    e.printStackTrace();
                }



                suppliers.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Supplier() {

    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
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
