package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONException;
import org.json.JSONObject;

public class Unity {
    private String unity,ID;
    private Double price;

    public Unity(String unity, Double price) {
        this.unity = unity;
        this.price = price;
    }

    public Unity() {
    }

    public Unity(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null){
            return;
        }

        unity=jsonObject.getString("unity");
        price=jsonObject.getDouble("price");

    }
    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("unity", unity);
            jsonObject.put("price", price);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
