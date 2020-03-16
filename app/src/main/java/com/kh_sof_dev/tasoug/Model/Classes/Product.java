package com.kh_sof_dev.tasoug.Model.Classes;

import com.kh_sof_dev.tasoug.Controule.Info.Store_info;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private  String name,discription,create_at,providerID,id_,categorieID,storeID,QRcode;
    private List<String> images;
    private List<Unity> unity;
private int nb_status;
    private  int status,quantity,quantity_sold;
    public List<Product> productList;
    public Product(JSONObject jsonObject_)  {
        if(jsonObject_ == null){
            return;
        }
      try{
          nb_status=jsonObject_.getInt("status");
          JSONArray jsonObject=jsonObject_.getJSONArray("products");
          productList=new ArrayList<>();
          for(int i = 0; i < jsonObject.length(); i++){
              Product p =new Product();
              p.setId_(jsonObject.getJSONObject(i).getString("_id"));

              p.setName(jsonObject.getJSONObject(i).getString("name"));
              p.setCategorieID(jsonObject.getJSONObject(i).getString("categorieID"));
              p.setQuantity(jsonObject.getJSONObject(i).getInt("quantity"));
              p.setQuantity_sold(jsonObject.getJSONObject(i).getInt("quantity_sold"));
              p.setDiscription(jsonObject.getJSONObject(i).getString("description"));
              p.setCreate_at(jsonObject.getJSONObject(i).getString("create_at"));

              p.setStatus(jsonObject.getJSONObject(i).getInt("status"));


              JSONArray images_list = jsonObject.getJSONObject(i).optJSONArray("images");
              images=new ArrayList<>();
              if(images_list != null){

                  for(int k = 0; k < images_list.length(); k++){
                      images.add(images_list.getString(k));
                  }
              }
              p.setImages(images);
              JSONArray unity_list = jsonObject.getJSONObject(i).optJSONArray("prices");
              unity=new ArrayList<>();
              if(unity_list != null){

                  for(int j = 0; j < unity_list.length();j++){
                      unity.add(new Unity(unity_list.getJSONObject(j)));
                  }
              }
              p.setUnity(unity);
              productList.add(p);

              try {
                  p.setQRcode(jsonObject.getJSONObject(i).getString("QRcode"));
              }catch (Exception e){
                  e.printStackTrace();
              }
          }
      }catch (Exception e){
          e.printStackTrace();
      }


    }

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("_id", id_);
            jsonObject.put("name", name);
            jsonObject.put("description", discription);
            jsonObject.put("supplierID", providerID);
            jsonObject.put("quantity", quantity);
            jsonObject.put("quantity_sold", quantity_sold);
            jsonObject.put("storeID", storeID);
            jsonObject.put("categorieID", categorieID);
            jsonObject.put("QRcode", QRcode);
           ///////////////images
            if(images != null && images.size() > 0){
                JSONArray itemsJsonArray = new JSONArray();
                for(String itemsElement : images){
                    itemsJsonArray.put(itemsElement);
                }
                jsonObject.put("images", itemsJsonArray);
            }

//////////unity
            if(unity != null && unity.size() > 0){
                JSONArray itemsJsonArray = new JSONArray();
                for(Unity itemsElement : unity){
                    itemsJsonArray.put(itemsElement.toJsonObject());
                }
                jsonObject.put("prices", itemsJsonArray);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    public Product() {
      storeID= Store_info.storeID;
      providerID=Store_info.storeID;
    }

    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getCategorieID() {
        return categorieID;
    }

    public void setCategorieID(String categorieID) {
        this.categorieID = categorieID;
    }

    public int getNb_status() {
        return nb_status;
    }

    public void setNb_status(int nb_status) {
        this.nb_status = nb_status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity_sold() {
        return quantity_sold;
    }

    public void setQuantity_sold(int quantity_sold) {
        this.quantity_sold = quantity_sold;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getId_() {
        return id_;
    }

    public void setId_(String id_) {
        this.id_ = id_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Unity> getUnity() {
        return unity;
    }

    public void setUnity(List<Unity> unity) {
        this.unity = unity;
    }

    public JSONObject JSONObject_forOrder(int i) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("_id", id_);
        jsonObject.put("name", name);
        jsonObject.put("discription", discription);
        jsonObject.put("image", images.get(0));
        jsonObject.put("unity", unity.get(i).getUnity());
        jsonObject.put("price", unity.get(i).getPrice());
        return jsonObject;
    }
}
