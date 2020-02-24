package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Product {
    private  String name,discription,create_at,providerID,id_;
    private List<String> images;
    private List<Unity> unity;

    private  int status;
    public Product(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null){
            return;
        }

        name=jsonObject.getString("name");
        discription=jsonObject.getString("discription");
        create_at=jsonObject.getString("password");
        status=jsonObject.getInt("status");

        JSONArray images_list = jsonObject.optJSONArray("images");
        if(images_list != null){

            for(int i = 0; i < images_list.length(); i++){
               images.add(images_list.getString(i));
            }
        }

        JSONArray unity_list = jsonObject.optJSONArray("unity");
        if(unity_list != null){

            for(int i = 0; i < unity_list.length(); i++){
                unity.add(new Unity(unity_list.getJSONObject(i)));
            }
        }
    }
    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id_", id_);
            jsonObject.put("name", name);
            jsonObject.put("discription", discription);
            jsonObject.put("providerID", providerID);
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
                jsonObject.put("unity", itemsJsonArray);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    public Product() {
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

}
