package com.kh_sof_dev.tasoug.Controule.Info;


import android.content.Context;
import android.content.SharedPreferences;

import com.kh_sof_dev.tasoug.Model.Classes.Store;

import static android.content.Context.MODE_PRIVATE;

public class Store_info {
    public static String storeID="";
    public static String api="https://tsouag.herokuapp.com/";
    public static String token="";
    public static String phone="";
    public static String user_name="";
    public static String name_ar="";
    public static String name_en="";
    public static String address="";
    public static String email="";
    public static String commerceID="";

    public Store_info(Context mcontext) {
        final SharedPreferences sp=mcontext.getSharedPreferences("Login", MODE_PRIVATE);
        storeID=sp.getString("id","");
        token=sp.getString("token","");
        phone=sp.getString("phone","");
        user_name=sp.getString("user_name","");
        name_ar=sp.getString("store_name_ar","");
        name_en=sp.getString("store_name_en","");
        address=sp.getString("address","");
        email=sp.getString("email","");
        commerceID=sp.getString("commerceID","");

    }
    public Store_info(Store store, Context context) {
        SharedPreferences sp=context.getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor Ed=sp.edit();
        Ed.putString("phone",store.getPhone() );
        Ed.putString("id",store.get_id());
        Ed.putString("token",store.getToken());
        Ed.putString("user_name",store.getUser_name());
        Ed.putString("image",store.getImage());
        Ed.putString("email",store.getEmail());
        Ed.putString("address",store.getAddress());
        Ed.putString("commerceID",store.getCommerceID());
        Ed.putString("store_name_ar",store.getStore_name_ar());
        Ed.putString("store_name_en",store.getStore_name_en());
        Ed.putBoolean("status",store.getStatus());
        Ed.putLong("lat",Double.doubleToRawLongBits(store.getLat()));
        Ed.putLong("lng",Double.doubleToRawLongBits(store.getLng()));
        Ed.commit();
        new Store_info(context);
    }
}
