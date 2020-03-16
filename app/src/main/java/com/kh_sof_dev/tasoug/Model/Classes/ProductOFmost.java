package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductOFmost {
    private  String name,_id,date=" ",unity;
   private Double price;
private List<ProductOFmost>productOFmostList;
    private  int quantity,status;

    public ProductOFmost() {
    }

    public ProductOFmost(JSONObject jsonObject)  {
        if(jsonObject == null){
            return;
        }
productOFmostList=new ArrayList<>();
        try {
            JSONArray jsonArray=jsonObject.getJSONArray("orders");
            for (int i=0;i<jsonArray.length();i++){
                ProductOFmost most=new ProductOFmost();
                most. name=jsonArray.getJSONObject(i).getString("name");
                most. quantity=jsonArray.getJSONObject(i).getInt("quantity");
                most. status=jsonArray.getJSONObject(i).getInt("status");
                most.  date=jsonArray.getJSONObject(i).getString("create_at");
                most. _id=jsonArray.getJSONObject(i).getString("_id");
                most.  price=jsonArray.getJSONObject(i).getDouble("price");
                productOFmostList.add(most);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ProductOFmost> getProductOFmostList() {
        return productOFmostList;
    }

    public void setProductOFmostList(List<ProductOFmost> productOFmostList) {
        this.productOFmostList = productOFmostList;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
