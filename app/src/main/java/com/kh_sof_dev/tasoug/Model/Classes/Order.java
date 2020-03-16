package com.kh_sof_dev.tasoug.Model.Classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Order {

 private String   _id;
    private int bayment_type;
    private String sub_total;
    private String QRcode;
    private String userID,user_name,user_phone,user_address;
    private String storeID;
    private String create_at;
   List<ProductOForder> products;
   List<Order> orderList;
    private  int status;//1:current   2:complete   3:cancel
    public Order(JSONObject jsonObject)  {
        if(jsonObject == null){
            return;
        }
        orderList=new ArrayList<>();
        try {
            JSONArray jsonArray=jsonObject.getJSONArray("orders");

            for (int i=0;i<jsonArray.length();i++){
                Order order=new Order();
               order. _id=jsonArray.getJSONObject(i).getString("_id");
                order.userID=jsonArray.getJSONObject(i).getString("userID");
                order.create_at=jsonArray.getJSONObject(i).getString("create_at");
                order.  status=jsonArray.getJSONObject(i).getInt("status");
                order. QRcode=jsonArray.getJSONObject(i).getString("QRcode");
                order.sub_total=jsonArray.getJSONObject(i).getString("sub_total");
                try{
                    order.user_phone=jsonArray.getJSONObject(i).getString("user_phone");
                    order.user_name=jsonArray.getJSONObject(i).getString("user_name");
                    order.user_address=jsonArray.getJSONObject(i).getString("user_address");

                }catch (Exception e){
                    e.printStackTrace();
                }
              try {
                  order. bayment_type=jsonArray.getJSONObject(i).getInt("bayment_type");
              }catch (Exception e){

              }

                JSONArray product_list = jsonArray.getJSONObject(i).optJSONArray("products");
              order.products=new ArrayList<>();
                if(product_list != null){

                    for(int j = 0; j < product_list.length(); j++){
                        order.  products.add(new ProductOForder(product_list.getJSONObject(j)));
                    }
                }
                orderList.add(order);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




    }

    public Order() {

    }

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userID", userID);
            jsonObject.put("sub_total", sub_total);
            jsonObject.put("bayment_type", bayment_type);
            jsonObject.put("storeID",storeID);
            jsonObject.put("user_address",user_address);
            jsonObject.put("user_name",user_name);
            jsonObject.put("user_phone",user_phone);
            ///////////////images
            if(products != null && products.size() > 0){
                JSONArray itemsJsonArray = new JSONArray();
                for(ProductOForder itemsElement : products){
                    itemsJsonArray.put(itemsElement.JSONObject_forOrder());
                }
                jsonObject.put("products", itemsJsonArray);
            }



        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getBayment_type() {
        return bayment_type;
    }

    public void setBayment_type(int bayment_type) {
        this.bayment_type = bayment_type;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getQRcode() {
        return QRcode;
    }

    public void setQRcode(String QRcode) {
        this.QRcode = QRcode;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public List<ProductOForder> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOForder> products) {
        this.products = products;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

