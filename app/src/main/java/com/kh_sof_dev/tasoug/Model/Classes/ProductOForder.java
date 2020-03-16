package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProductOForder {
    private  String name,_id,image=" ",unity;
   private Double price;

    private  int quantity;

    public ProductOForder() {
    }

    public ProductOForder(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null){
            return;
        }

        name=jsonObject.getString("name");
        quantity=jsonObject.getInt("quantity");
        image=jsonObject.getString("image");
        _id=jsonObject.getString("_id");
        price=jsonObject.getDouble("price");

    }


    public JSONObject JSONObject_forOrder() throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("_id", _id);

        jsonObject.put("name", name);
        jsonObject.put("image", image);
        jsonObject.put("unity", unity);
        jsonObject.put("price",price);
        jsonObject.put("quantity",quantity);
        return jsonObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
